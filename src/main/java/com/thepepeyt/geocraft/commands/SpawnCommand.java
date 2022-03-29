package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnCommand {

    private Main plugin;

    public SpawnCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Command(
            name = "spawn",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.spawn"
    )
    public void execute(CommandArguments arguments) {
        Player player = arguments.getSender();
        var spawnSection = plugin.getConfig().getConfigurationSection("spawn");
        plugin.getTeleportManager().teleport(player,
                new Location(Bukkit.getWorld((String) spawnSection.get("world")), spawnSection.getDouble("x"), spawnSection.getDouble("y"), spawnSection.getDouble("y")),
                spawnSection.getInt("teleportTime"));






    }
}
