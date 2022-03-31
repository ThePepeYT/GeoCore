package com.thepepeyt.geocraft.utils;

import com.thepepeyt.geocraft.Main;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TeleportManager {
    private Main plugin;


    @Getter
    private HashMap<UUID, Integer> teleports = new HashMap<>();


    public TeleportManager(Main plugin) {
        this.plugin = plugin;
    }

    public void teleport(@NotNull Player player, Location loc, int time) {
        if (this.teleports.containsKey(player.getUniqueId())) {
            //teleportuje sie
            return;
        }

        plugin.getMessageManager().sendFancyMessage("HalfWorld", "Spawn", "  Zostaniesz\n      przeteleportowany za " + time + "sec", player);

        Location base = player.getLocation();
        int id = plugin.getServer().getScheduler().runTaskLater(plugin, () -> {

            if(!player.getLocation().equals(base)){
                plugin.getMessageManager().sendFancyMessage("HalfWorld", "Spawn", "<red>Nie zostaniesz\n    przeteleportowany poniewa\u017C si\u0119 ruszy\u0142e\u015B</red>", player);
                teleports.remove(player.getUniqueId());
            }else {
                player.teleport(loc);
                plugin.getMessageManager().sendFancyMessage("HalfWorld", "Teleportcja", "<green>Pomy\u015Blnie przeteleportowano</green>", player);
                teleports.remove(player.getUniqueId());
            }
        }, time * 20L).getTaskId();

        teleports.put(player.getUniqueId(), id);
    }

    public void cancelTeleport(Player player) {
        if(!teleports.containsKey(player.getUniqueId())) return;
        plugin.getServer().getScheduler().cancelTask(teleports.get(player.getUniqueId()));
        teleports.remove(player.getUniqueId());
    }


}