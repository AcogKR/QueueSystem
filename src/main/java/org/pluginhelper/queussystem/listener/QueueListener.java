package org.pluginhelper.queussystem.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.pluginhelper.queussystem.QueueSystem;

import java.util.*;

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
        queueSystem.getConfigData().getJoinMessage().forEach(event.getPlayer()::sendMessage);
        queueSystem.getQueusMap().put(event.getPlayer().getUniqueId(), queueSystem.queusMap.size());
        event.getPlayer().sendTitle("현재 대기열 시스템이 작동중 입니다.", "당신 앞으로 ~명 있습니다");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            if(Bukkit.getOnlinePlayers().size() < queueSystem.getConfigData().getMaxPlayers()) {
                down();
            }
        } else {
            queueSystem.getQueusMap().remove(event.getPlayer().getUniqueId());
            for (Map.Entry<UUID, Integer> entry : queueSystem.getQueusMap().entrySet()) {
                queueSystem.getQueusMap().put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        Player player = event.getPlayer();
        player.teleport(event.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        if(!queueSystem.queueState) {
            return;
        }
        if(!queueSystem.getQueusMap().containsKey(event.getPlayer().getUniqueId())) {
            return;
        }
        event.setCancelled(true);
    }

    public void down() {
        Map<UUID, Integer> list = new HashMap<>();
        int i = 0;
        for (Map.Entry<UUID, Integer> entry : queueSystem.getQueusMap().entrySet()) {
            list.put(entry.getKey(), i);
            i++;
        }
        queueSystem.getQueusMap().putAll(list);
    }
}
