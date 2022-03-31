package com.thepepeyt.geocraft.listeners;


import com.thepepeyt.geocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishListener implements Listener {


    private Main plugin;


    public VanishListener(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player joined = event.getPlayer();
        if (plugin.getVanishManager().getVanishes().contains(joined.getUniqueId())) {
            event.setJoinMessage("");
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (plugin.getVanishManager().getVanishes().contains(player.getUniqueId())) {
                if (!joined.hasPermission("geocore.vanish.bypass") | !plugin.getVanishManager().getVanishes().contains(joined.getUniqueId())) {
                    joined.hidePlayer(plugin, player);
                }
                return;
            }
            if (plugin.getVanishManager().getVanishes().contains(joined.getUniqueId())) {
                if (!player.hasPermission("geocore.vanish.bypass") | !plugin.getVanishManager().getVanishes().contains(player.getUniqueId())) {
                    player.hidePlayer(plugin, joined);
                }
            }
        });
    }

    @EventHandler
    public void PlayerPickupItem(PlayerPickupItemEvent event) {
        if (plugin.getVanishManager().getVanishes().contains(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void DamageEvent(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player & plugin.getVanishManager().getVanishes().contains(event.getEntity().getUniqueId()))
            event.setCancelled(true);
    }

    @EventHandler
    public void LeaveEvent(PlayerQuitEvent event) {
        if (plugin.getVanishManager().getVanishes().contains(event.getPlayer().getUniqueId())) event.setQuitMessage("");
    }

    @EventHandler
    public void target(EntityTargetEvent event) {
        if(!(event.getTarget() instanceof Player)) return;
        if(plugin.getVanishManager().getVanishes().contains(event.getTarget().getUniqueId())){
            event.setCancelled(true);
        }

    }
}
