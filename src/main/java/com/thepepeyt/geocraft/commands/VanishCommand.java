package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class VanishCommand {

    private Main plugin;

    public VanishCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "vanish",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.vanish"
    )
    public void execute(CommandArguments arguments) {
        Player sender = (Player) arguments.getSender();

        if(plugin.getVanishManager().getVanishes().contains(sender.getUniqueId())){
            plugin.getVanishManager().removeVanish(sender);
            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Vanish", "<green>Vanish zosta\u0142\n         pomy\u015Blnie wy\u0142\u0105czony", sender);

        }
        else{
            plugin.getVanishManager().addVanish(sender);
            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Vanish", "<green>Vanish zosta\u0142\n         pomy\u015Blnie w\u0142\u0105czony", sender);

        }


    }



}
