package me.sxyxuse.manager.permissions;

import java.util.Arrays;

public enum Permissions {
    PLAYER(0, "§7Joueur", "§7Joueur", "§7"),
    HELPER(1, "§2Helper", "§2[Helper]", "§a"),
    MODERATOR(2, "§6Modérateur", "§6[Modérateur]", "§e"),
    ADMINISTRATOR(3, "§cAdmin", "§c[Admin]", "§c"),
    OWNER(4, "§4Admin", "§4[Admin]", "§c");

    private final int power;
    private final String name;
    private final String prefix;
    private final String messageColor;

    Permissions(int power, String name, String prefix, String messageColor) {
        this.power = power;
        this.name = name;
        this.prefix = prefix;
        this.messageColor = messageColor;
    }

    public static Permissions getByPower(int power) {
        return Arrays.stream(values()).filter(r -> r.getPower() == power).findAny().orElse(Permissions.PLAYER);
    }

    public static Permissions getByName(String name) {
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(Permissions.PLAYER);
    }

    public String getMessageColor() {
        return messageColor;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
