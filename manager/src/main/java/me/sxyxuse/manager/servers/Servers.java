package me.sxyxuse.manager.servers;

import java.util.Arrays;
import java.util.List;

public enum Servers {
    MINAGE("mining", "§cServeur minage", Arrays.asList("Connectez-vous sur le serveur minage")),
    BUILD("build", "§cServeur de build", Arrays.asList("Connectez-vous sur le serveur de build"));

    private final String bungeeServerName;
    private final String displayName;
    private final List<String> description;

    Servers(String bungeeServerName, String displayName, List<String> description) {
        this.bungeeServerName = bungeeServerName;
        this.displayName = displayName;
        this.description = description;
    }

    public String getBungeeServerName() {
        return bungeeServerName;
    }


    public String getDisplayName() {
        return displayName;
    }

    public List<String> getDescription() {
        return description;
    }
}
