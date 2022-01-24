package org.pluginhelper.queussystem;

import org.bukkit.plugin.java.JavaPlugin;
import org.pluginhelper.queussystem.command.QueueCommand;
import org.pluginhelper.queussystem.listener.QueueListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QueusSystem extends JavaPlugin {

    private final Boolean queueState = false;
    private final Map<Integer, UUID> queusMap = new HashMap<>();

    @Override
    public void onEnable() {
        getCommand("queus").setExecutor(new QueueCommand(queueState));
        getServer().getPluginManager().registerEvents(new QueueListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
