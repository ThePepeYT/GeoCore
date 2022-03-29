package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import com.thepepeyt.geocraft.gui.InvseeGUI;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class InvseeCommand {
    private Main plugin;

    public InvseeCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "invsee",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.invsee"
    )
    public void execute(CommandArguments arguments) {

        new InvseeGUI(plugin).open(arguments.getSender());
        // And here it's all done, you've created command with properties above!

    }

    // Aliases don't need to be same with the command above
}

