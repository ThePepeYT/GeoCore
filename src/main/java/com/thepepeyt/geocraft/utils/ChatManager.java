package com.thepepeyt.geocraft.utils;

import com.thepepeyt.geocraft.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

public final class ChatManager {

    private Main plugin;

    @Getter
    @Setter
    private boolean chat;





    public ChatManager(Main plugin) {
        this.plugin = plugin;
        this.chat = false;
    }

    public void clearChat() {
        Bukkit.getOnlinePlayers().forEach(player -> {for (int i = 0; i < 100; i++) {player.sendMessage("");}});
    }

    public void toggleChat() {
        setChat(!chat);
        Bukkit.getOnlinePlayers().forEach(player -> {for (int i = 0; i < 10; ++i) {player.sendMessage("");}});
        }


    public boolean canSendMessage(final Player player) {
        return player.hasPermission("geocore.chat.bypass") | this.chat;
    }




}
