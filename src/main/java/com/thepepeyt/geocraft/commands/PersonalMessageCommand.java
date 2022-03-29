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

    public static final Player[] players = (Player[]) Bukkit.getOnlinePlayers().toArray();

    private Main plugin;

    private HashMap<Player, Player> last_dm = new HashMap<>();

    public PersonalMessageCommand(Main plugin){
        this.plugin = plugin;
    }

    @Command(
            name = "msg",
            aliases = {"dm", "r"},
            senderType = Command.SenderType.PLAYER,
            permission = "GeoCore.ping",
            min = 1,
            max = 2
    )
    public void execute(CommandArguments arguments) {
        String message = arguments.getArgument(0);
        Player user = last_dm.containsKey((Player) arguments.getSender()) ? last_dm.get((Player) arguments.getSender()) : Bukkit.getPlayer(Objects.requireNonNull(arguments.getArgument(1)));




    }
    // Aliases don't need to be same with the command above
    @Completer(name = "msg", aliases = {"dm", "r"})
    public List<String> exampleCommandCompletion(CommandArguments arguments) {
        // And you've created a tab completion for the command above
        return Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
    }
}
