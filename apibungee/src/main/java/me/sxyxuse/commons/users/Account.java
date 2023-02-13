package me.sxyxuse.commons.users;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.sxyxuse.apibungee.api.Player;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Account {
    private final ProxiedPlayer proxiedPlayer;
    private final UUID uuid;
    private final String pseudo;
    private final int money;
    private final byte permissions;
    private final byte grade;
    private final int grade_time_left;
    private JsonObject jsonAccount;

    public Account(ProxiedPlayer proxiedPlayer) {
        this.proxiedPlayer = proxiedPlayer;
        this.jsonAccount = getFromDb();
        this.uuid = proxiedPlayer.getUniqueId();
        this.pseudo = jsonAccount == null ? proxiedPlayer.getName() : String.valueOf(jsonAccount.get("pseudo"));
        this.money = jsonAccount == null ? 0 : Integer.parseInt(String.valueOf(jsonAccount.get("money")));
        this.permissions = jsonAccount == null ? 0 : Byte.parseByte(String.valueOf(jsonAccount.get("permissions")));
        this.grade = jsonAccount == null ? 0 : Byte.parseByte(String.valueOf(jsonAccount.get("grade")));
        this.grade_time_left = jsonAccount == null ? 0 : Integer.parseInt(String.valueOf(jsonAccount.get("grade_time_left")));
    }

    public void setup() {
        if (this.getFromDb() == null)
            this.jsonAccount = Player.addPlayer(this.getDefaultAccountJson()).getAsJsonObject();
    }

    public JsonObject getFromDb() {
        return Player.getSpecificPlayer("uuid", this.proxiedPlayer.getUniqueId().toString());
    }

    public void updateLastLogin() {
        JsonObject json = new JsonObject();
        json.addProperty("last_login", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
        JsonElement element = Player.updateLastLoginPlayer(json, "uuid", this.proxiedPlayer.getUniqueId().toString());
    }

    public ProxiedPlayer getProxiedPlayer() {
        return proxiedPlayer;
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

    public byte getGrade() {
        return grade;
    }

    public int getGradeTimeLeft() {
        return grade_time_left;
    }

    public JsonObject getJsonAccount() {
        return jsonAccount;
    }

    public JsonObject getDefaultAccountJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", this.getUuid().toString());
        jsonObject.addProperty("pseudo", this.proxiedPlayer.getName());
        jsonObject.addProperty("money", 0);
        jsonObject.addProperty("permissions", 0);
        jsonObject.addProperty("grade", 0);
        jsonObject.addProperty("grade_time_left", 0);
        jsonObject.addProperty("first_login", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
        jsonObject.addProperty("last_login", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
        return jsonObject;
    }
}
