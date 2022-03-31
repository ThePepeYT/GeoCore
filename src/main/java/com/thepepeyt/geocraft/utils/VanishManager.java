package com.thepepeyt.geocraft.utils;

import com.thepepeyt.geocraft.Main;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishManager {

    @Getter
    private List<UUID> vanishes = new ArrayList<>();

    private Main plugin;

    public VanishManager(Main plugin){
        this.plugin = plugin;
    }


    public void addVanish(Player vanish){
        vanishes.add(vanish.getUniqueId());
                Bukkit.getOnlinePlayers()
                        .stream().filter(player -> !player.hasPermission("geocore.vanish.bypass") | !getVanishes().contains(player.getUniqueId()))
                        .forEach(player -> {
                            player.hidePlayer(plugin, vanish);
                            plugin.getMessageManager().sendMessage("<yellow>" + vanish + " left the game", player);
                        });
                plugin.getMessageManager().sendMessage("<yellow>" + vanish.getName() + " left the game", vanish);

    }

    public void removeVanish(Player vanish){
        if (getVanishes().contains(vanish.getUniqueId())) {
            vanishes.remove(vanish.getUniqueId());
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.showPlayer(plugin, vanish);
                plugin.getMessageManager().sendMessage("<yellow>" + vanish.getName() + " joined the game", player);
            });
        }


    }
}
