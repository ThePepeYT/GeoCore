package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import com.thepepeyt.geocraft.gui.TeleportGUI;
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
            permission = "GeoCore.tphere"
    )
    public void execute(CommandArguments arguments) {
        new TeleportGUI(plugin, "here").open(arguments.getSender());
    }


}
