package me.sxyxuse.commons.users;

import com.google.gson.JsonObject;
import me.sxyxuse.apibungee.ApiBungee;
import me.sxyxuse.apibungee.api.Player;
import me.sxyxuse.apibungee.api.Request;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Account {
    private final ProxiedPlayer proxiedPlayer;
    private final UUID uuid;
    private final String pseudo;
    private final long money;
    private final byte permissions;
    private final byte grade;
    private final int grade_time_left;

    public Account(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
        this.uuid = proxiedPlayer.getUniqueId();
        this.pseudo = this.getPseudo();
        this.money = this.getMoney();
        this.permissions = this.getPermissions();
        this.grade = this.getGrade();
        this.grade_time_left = this.getGradeTimeLeft();
    }

    public void setup() {
//        JsonObject json;
//        try {
//            json = new Request("/player").getWithHeader("uuid", this.proxiedPlayer.getUniqueId().toString());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        if (Player.getSpecificPlayer("uuid", this.proxiedPlayer.getUniqueId().toString()) == null)
            try {
                JsonObject obj = new Request("/aplayer").addPlayer(this.getInitJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

//        ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
//            try {
//                if (!rs.next()) {
//                    ApiBungee.getInstance().databaseManager.update("INSERT INTO players (uuid, pseudo, money, permissions, grade, grade_time_left, first_login, last_login) VALUES ('" + uuid + "', '" + proxiedPlayer.getName() + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + 0 + "', '" + System.currentTimeMillis() + "', '" + System.currentTimeMillis() + "')");
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });
    }

    public void updateLastLogin() {
        ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    ApiBungee.getInstance().databaseManager.update("UPDATE players SET last_login ='" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")) + "' WHERE uuid ='" + uuid + "'");
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

    private byte getGrade() {
        return (byte) ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    return (byte) rs.getInt("grade");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return (byte) 0;
        });
    }

    private int getGradeTimeLeft() {
        return (int) ApiBungee.getInstance().databaseManager.query("SELECT * FROM players WHERE uuid ='" + uuid + "'", rs -> {
            try {
                if (rs.next()) {
                    return rs.getInt("grade_time_left");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return 0;
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

    public byte getPropsGrade() {
        return grade;
    }

    public int getPropsGradeTimeLeft() {
        return grade_time_left;
    }

    public JSONObject getJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", this.getUuid().toString());
        jsonObject.put("pseudo", this.getPropsPseudo());
        jsonObject.put("money", this.getPropsMoney());
        jsonObject.put("permissions", this.getPropsPermissions());
        jsonObject.put("grade", this.getPropsGrade());
        jsonObject.put("grade_time_left", this.getPropsGradeTimeLeft());

        return jsonObject;
    }

    public JSONObject getInitJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", this.getUuid().toString());
        jsonObject.put("pseudo", this.proxiedPlayer.getName());
        jsonObject.put("money", 0);
        jsonObject.put("permissions", 0);
        jsonObject.put("grade", 0);
        jsonObject.put("grade_time_left", 0);
        jsonObject.put("first_login", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
        jsonObject.put("last_login", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));

        return jsonObject;
    }
}
