package com.thepepeyt.geocraft.listeners;

import com.thepepeyt.geocraft.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatInspectorListener implements Listener {

    private Main plugin;

    public ChatInspectorListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void execute(AsyncChatEvent event){
        if(!plugin.getChatManager().canSendMessage(event.getPlayer())){
            event.setCancelled(true);
            event.getPlayer().sendMessage("lol nie mozesz pisa?");
        }

    }
}
