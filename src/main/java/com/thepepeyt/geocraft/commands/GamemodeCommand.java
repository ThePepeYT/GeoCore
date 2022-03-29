package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.gui.GamemodeGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GamemodeCommand {

    private Main plugin;

    public GamemodeCommand(Main plugin){
        this.plugin = plugin;
    }


    @Command(
            name = "gm",
            permission = "GeoCore.gamemode",
            senderType = Command.SenderType.PLAYER,
            aliases = {"gamemode"},
            max = 1
    )
    public void execute(CommandArguments arguments){
        Player player = arguments.isArgumentsEmpty() ? arguments.getSender() : Bukkit.getPlayer(Objects.requireNonNull(arguments.getArgument(0)));
        new GamemodeGUI(plugin, player).open(player);



    }
}
