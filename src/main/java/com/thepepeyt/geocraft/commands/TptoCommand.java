package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.gui.TeleportGUI;

public class TptoCommand {
    private Main plugin;

    public TptoCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "tpto",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.tpto"
    )
    public void execute(CommandArguments arguments) {
        new TeleportGUI(plugin, "to").open(arguments.getSender());
    }

}
