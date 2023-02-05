package me.sxyxuse.commons.users;

import org.bukkit.entity.Player;
import org.json.JSONObject;

import java.util.UUID;

public class Account {
    private final Player player;
    private final UUID uuid;
    private final String pseudo;
    private final long money;
    private final byte permissions;

    public Account(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.pseudo = this.getPseudo();
        this.money = this.getMoney();
        this.permissions = this.getPermissions();
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getPseudo() {
        return pseudo;
    }

    public long getMoney() {
        return money;
    }

    public byte getPermissions() {
        return permissions;
    }

    public JSONObject getJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", this.getUuid().toString());
        jsonObject.put("pseudo", this.getPseudo());
        jsonObject.put("money", this.getMoney());
        jsonObject.put("permissions", this.getPermissions());

        return jsonObject;
    }
}
