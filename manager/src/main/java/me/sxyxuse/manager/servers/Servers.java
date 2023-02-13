package me.sxyxuse.manager.servers;

import java.util.Arrays;
import java.util.List;

public enum Servers {
    MINAGE("mining", "§cServeur minage", "Serveur minage", Arrays.asList("Connectez-vous sur le serveur minage")),
    BUILD("build", "§cServeur de build", "Serveur build", Arrays.asList("Connectez-vous sur le serveur de build"));

    private final String bungeeServerName;
    private final String displayName;
    private final String queueName;
    private final List<String> description;

    Servers(String bungeeServerName, String displayName, String queueName, List<String> description) {
        this.bungeeServerName = bungeeServerName;
        this.displayName = displayName;
        this.queueName = queueName;
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

    public String getQueueName() {
        return queueName;
    }
}
