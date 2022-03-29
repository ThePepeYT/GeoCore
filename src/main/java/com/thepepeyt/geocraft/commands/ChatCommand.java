package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.gui.ChatInspectorGUI;
import org.bukkit.entity.Player;

public class ChatCommand {
    private Main plugin;

    public ChatCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Command(
            name = "chatinspector",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.chatinspector"
    )
    public void execute(CommandArguments arguments) {
        new ChatInspectorGUI(plugin).open(arguments.getSender());
    }
}
