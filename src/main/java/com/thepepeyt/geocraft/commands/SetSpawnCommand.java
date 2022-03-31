package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import org.bukkit.entity.Player;

public class SetSpawnCommand {

    private Main plugin;

    public SetSpawnCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Command(
            name = "setspawn",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.setspawn"
    )
    public void execute(CommandArguments arguments){
        Player player = arguments.getSender();
        var spawnSection = plugin.getConfig().getConfigurationSection("spawn");
        spawnSection.set("x", player.getLocation().getX());
        spawnSection.set("y", player.getLocation().getY());
        spawnSection.set("z", player.getLocation().getZ());

        plugin.getConfig().set("spawn", spawnSection);
        plugin.saveConfig();





        plugin.getMessageManager().sendFancyMessage("HalfWorld", "Spawn",
                "Ustawiono spawn na\n      koordynatach {x},{y},{z}"
                        .replace("{x}", String.valueOf(Math.round(player.getLocation().getX())))
                        .replace("{y}", String.valueOf(Math.round(player.getLocation().getY())))
                        .replace("{z}", String.valueOf(Math.round(player.getLocation().getZ()))), player);



    }
}
