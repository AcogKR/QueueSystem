package org.pluginhelper.queussystem;

import lombok.Data;

@Data
public class ConfigData {
    private final int maxPlayers;
    private final int messageSecond;

    public ConfigData(int maxPlayers, int messageSecond) {
        this.maxPlayers = maxPlayers;
        this.messageSecond = messageSecond;
    }
}
