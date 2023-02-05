package me.sxyxuse.manager.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CommandManager extends Command {
    private final CommandExecutor commandExecutor;

    public CommandManager(String name, String description, CommandExecutor commandExecutor, String... aliases) {
        super(name, description, "", Arrays.asList(aliases));
        this.commandExecutor = commandExecutor;
    }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        return commandExecutor.onCommand(commandSender, this, commandLabel, args);
    }
}
