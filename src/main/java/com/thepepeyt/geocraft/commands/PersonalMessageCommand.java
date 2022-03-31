package com.thepepeyt.geocraft.commands;

import com.thepepeyt.geocraft.Main;
import com.thepepeyt.geocraft.commands.api.Command;
import com.thepepeyt.geocraft.commands.api.CommandArguments;
import com.thepepeyt.geocraft.commands.api.Completer;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PersonalMessageCommand {


    private Main plugin;

    private HashMap<UUID, UUID> last_dm = new HashMap<>();

    public PersonalMessageCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "msg",
            aliases = {"dm", "r"},
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.ping",
            min = 1,
            max = 200
    )
    public void execute(CommandArguments arguments) {
        Player player = arguments.getSender();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < arguments.getArguments().length; i++) {
            stringBuilder.append(arguments.getArgument(i) + " ");

        }


        Player user = last_dm.containsKey(player.getUniqueId()) ? Bukkit.getPlayer(last_dm.get(player.getUniqueId())) : Bukkit.getPlayer(Objects.requireNonNull(arguments.getArgument(0)));
        plugin.getMessageManager().sendMessage(plugin.getMessageManager().getString("<#d7a220>\u2693</#d7a220><#bab469> | " + user.getName() + " \u21A6 Ty <#d7a220>\u272B <white>" + stringBuilder.toString()), user);
        plugin.getMessageManager().sendMessage(plugin.getMessageManager().getString("<#d7a220>\u2693</#d7a220><#bab469> | Ty \u21A6 " + user.getName() + " <#d7a220>\u272B <white>" + stringBuilder.toString()), player);



    }
    // Aliases don't need to be same with the command above
    @Completer(name = "msg", aliases = {"dm", "r"})
    public List<String> exampleCommandCompletion(CommandArguments arguments) {
        // And you've created a tab completion for the command above
        return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
