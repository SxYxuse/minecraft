package me.sxyxuse.commons.users;

import com.google.gson.JsonObject;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Account {
    private final Player player;
    private final UUID uuid;
    private String pseudo;
    private int money;
    private byte permissions;
    private byte grade;
    private int grade_time_left;

    public Account(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.pseudo = this.getPseudo();
        this.money = this.getMoney();
        this.permissions = this.getPermissions();
        this.grade = this.getGrade();
        this.grade_time_left = getGradeTimeLeft();
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

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public byte getPermissions() {
        return permissions;
    }

    public void setPermissions(byte permissions) {
        this.permissions = permissions;
    }

    public byte getGrade() {
        return grade;
    }

    public void setGrade(byte grade) {
        this.grade = grade;
    }

    public int getGradeTimeLeft() {
        return grade_time_left;
    }

    public void setGrade_time_left(int time) {
        this.grade_time_left = time;
    }

    public JsonObject getJsonAccount() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uuid", this.getUuid().toString());
        jsonObject.addProperty("pseudo", this.getPseudo());
        jsonObject.addProperty("money", this.getMoney());
        jsonObject.addProperty("permissions", this.getPermissions());
        jsonObject.addProperty("grade", this.getGrade());
        jsonObject.addProperty("grade_time_left", this.getGradeTimeLeft());
        return jsonObject;
    }
}
