package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import com.thepepeyt.geocraft.gui.TeleportGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TphereCommand {

    private Main plugin;

    public TphereCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "tphere",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.tphere",
            max = 1
    )
    public void execute(CommandArguments arguments) {
        if(arguments.isArgumentsEmpty()){
            new TeleportGUI(plugin, "here").open(arguments.getSender());
        }
        else{
            Bukkit.getOnlinePlayers().forEach(player -> player.teleport((Player) arguments.getSender()));
            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Teleportacja", "<green>Pomy\u015Blnie przeteleportowano\n            wszystkich graczy do ciebie", (Player) arguments.getSender());
        }

    }


}
