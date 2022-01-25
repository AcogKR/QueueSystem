package org.pluginhelper.queussystem;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.pluginhelper.queussystem.command.QueueCommand;
import org.pluginhelper.queussystem.listener.QueueListener;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class QueueSystem extends JavaPlugin {

    public Boolean queueState = false;
    public Map<UUID, Integer> queusMap = new HashMap<>();
    public ConfigData configData;

    @Override
    public void onEnable() {
        reload();
        getCommand("queus").setExecutor(new QueueCommand(this));
        getServer().getPluginManager().registerEvents(new QueueListener(this), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void reload() {
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getConfig().set("Objects.QueueSystem.max-players", 100);
            getConfig().set("Objects.QueueSystem.queue-message-second", 10);
            saveConfig();
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.configData = new ConfigData(
                config.getInt("QueueSystem.max-players"),
                config.getInt("QueueSystem.queue-message-second")
        );
    }
}
