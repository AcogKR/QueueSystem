package org.pluginhelper.queussystem.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class QueueCommand implements CommandExecutor {

    private final Boolean QueueState;

    public QueueCommand(Boolean queueState) {
        this.QueueState = queueState;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, command, label, args);
        return false;
    }

    public void execute(CommandSender sender, Command command, String label, String[] args) {

    }
}
