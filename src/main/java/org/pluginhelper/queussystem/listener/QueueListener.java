package org.pluginhelper.queussystem.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.pluginhelper.queussystem.QueueSystem;

public class QueueListener implements Listener {
    private final QueueSystem queueSystem;

    public QueueListener(QueueSystem plugin) {
        this.queueSystem = plugin;
    }

    public void onJoin(PlayerJoinEvent event) {
        int players = Bukkit.getOnlinePlayers().size();
        if(queueSystem.configData.getMaxPlayers() > players) {
            return;
        }

        Player player = event.getPlayer();
        player.setGameMode(GameMode.SPECTATOR);
        player.sendTitle("대기열", "~님 앞에 ~명의 대기열이 존재합니다.", 100, 100, 100);
        queueSystem.getQueusMap().put(queueSystem.queusMap.size(), player.getUniqueId());
    }
}
