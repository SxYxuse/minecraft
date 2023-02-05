package me.sxyxuse.manager.commands;

import me.sxyxuse.manager.managers.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.craftbukkit.v1_19_R1.CraftServer;

public class Command {
    public Command() {
    }

    public void createCommand(String name, String description, CommandExecutor commandExecutor, String... aliases) {
        CraftServer craftServer = (CraftServer) Bukkit.getServer();
        craftServer.getCommandMap().register(Bukkit.getName(), new CommandManager(name, description, commandExecutor, aliases));
    }
}
