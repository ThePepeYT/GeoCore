package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class PingCommand {

    private Main plugin;

    public PingCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "ping",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.ping"
    )
    public void execute(CommandArguments arguments) {
        // CommandArguments class contains basic things related Bukkit commands
        Player sender = (Player) arguments.getSender();

        plugin.getMessageManager().sendFancyMessage("HalfWorld", "Tescior", "Testowa wiadomosc", sender);
        // And here it's all done, you've created command with properties above!

    }

    // Aliases don't need to be same with the command above
    @Completer(name = "ping", aliases = {"firstAlias", "secondAlias"})
    public List<String> exampleCommandCompletion(CommandArguments arguments) {
        // And you've created a tab completion for the command above
        return Arrays.asList("first", "second", "third");
    }


}
