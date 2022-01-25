package org.pluginhelper.queussystem.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.pluginhelper.queussystem.QueueSystem;

import java.util.Map;
import java.util.UUID;

public class QueueListener implements Listener {
    private final QueueSystem queueSystem;

    public QueueListener(QueueSystem plugin) {
        this.queueSystem = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }

        event.setJoinMessage("");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {

        } else {
            queueSystem.getQueusMap().remove(event.getPlayer().getUniqueId());
            for (Map.Entry<UUID, Integer> entry : queueSystem.getQueusMap().entrySet()) {
                queueSystem.getQueusMap().put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        Player player = event.getPlayer();
        player.teleport(event.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }
}
