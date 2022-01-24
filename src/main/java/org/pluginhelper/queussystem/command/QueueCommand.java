package org.pluginhelper.queussystem.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.pluginhelper.queussystem.QueueSystem;

public class QueueCommand implements CommandExecutor {

    private final QueueSystem queueSystem;

    public QueueCommand(QueueSystem queueState) {
        this.queueSystem = queueState;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, args);
        return false;
    }

    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player) || !sender.isOp()) {
            sender.sendMessage("권한이 없습니다.");
            return;
        }
        if(args.length < 1) {
            sender.sendMessage("/queue help");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "help": {
                sender.sendMessage("/queue [On/Off]");
                sender.sendMessage("/queue reload");
            }
            case "on": {
                queueSystem.setQueueState(true);
                sender.sendMessage("QueueSystem On");
            }
            case "off": {
                queueSystem.setQueueState(false);
                sender.sendMessage("QueueSystem Off");
            }
            case "reload": {
                queueSystem.reload();
                sender.sendMessage("QueueSystem reload");
            }
        }
    }
}
