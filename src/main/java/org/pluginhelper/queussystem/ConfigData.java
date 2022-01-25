package org.pluginhelper.queussystem;

import lombok.Data;

import java.util.List;

@Data
public class ConfigData {
    private final int maxPlayers;
    private final int messageSecond;
    private final List<String> joinMessage;

    public ConfigData(int maxPlayers, int messageSecond, List<String> joinMessage) {
        this.maxPlayers = maxPlayers;
        this.messageSecond = messageSecond;
        this.joinMessage = joinMessage;
    }
}
