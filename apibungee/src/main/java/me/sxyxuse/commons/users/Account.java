package me.sxyxuse.commons.users;

import me.sxyxuse.apibungee.ApiBungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class Account {
    private final ProxiedPlayer proxiedPlayer;
    private final UUID uuid;
    private final String pseudo;
    private final long money;
    private final byte permissions;

    public Account(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
        this.uuid = proxiedPlayer.getUniqueId();
        this.pseudo = this.getPseudo();
        this.money = this.getMoney();
        this.permissions = this.getPermissions();
    }

    public void setup() {
        ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (!rs.next()) {
                    ApiBungee.getInstance().databaseManager.update("INSERT INTO players (uuid, pseudo, money, permissions, grade, grade_time_left, first_login, last_login) VALUES ('" + uuid + "', '" + proxiedPlayer.getName() + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + LocalDateTime.now() + "', '" + LocalDateTime.now() + "')");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void updateLastLogin() {
        ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    ApiBungee.getInstance().databaseManager.update("UPDATE players SET last_login ='" + LocalDateTime.now() + "' WHERE uuid ='" + uuid + "'");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String getPseudo() {
        return (String) ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    return rs.getString("pseudo");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }

    private long getMoney() {
        return (long) ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    return rs.getLong("money");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return 0L;
        });
    }

    private byte getPermissions() {
        return (byte) ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    return (byte) rs.getInt("permissions");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return (byte) 0;
        });
    }

    public ProxiedPlayer getProxiedPlayer() {
        return proxiedPlayer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPropsPseudo() {
        return pseudo;
    }

    public long getPropsMoney() {
        return money;
    }

    public byte getPropsPermissions() {
        return permissions;
    }
}
