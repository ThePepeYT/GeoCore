package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCommand {




    private Main plugin;

    public HealCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "heal",
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.heal",
            max = 1
    )
    public void execute(CommandArguments arguments) {
        Player player = arguments.getSender();

        if(arguments.isArgumentsEmpty()){
            healPlayer(player);
            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Heal", "<green>Zosta\u0142e\u015B pomy\u015Blnie wyleczony", player);
        }
        else {
            Player target = Bukkit.getPlayer(arguments.getArgument(0));
            healPlayer(target);

            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Heal", "<green>Zosta\u0142e\u015B pomy\u015Blnie wyleczony", target);
            plugin.getMessageManager().sendFancyMessage("HalfWorld", "Heal", "<green>" + target.getName() + " zosta\u0142\n            pomy\u015Blnie wyleczony", player);
        }


    }


    private void healPlayer(Player player) {
        player.setFoodLevel(20);
        player.setHealth(20);
        player.setFireTicks(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
    }
}
