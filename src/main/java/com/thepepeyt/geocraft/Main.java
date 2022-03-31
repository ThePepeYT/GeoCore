package com.thepepeyt.geocraft;


import com.thepepeyt.geocraft.commands.*;
import com.thepepeyt.geocraft.commands.api.CommandFramework;
import com.thepepeyt.geocraft.commands.api.StringMatcher;
import com.thepepeyt.geocraft.listeners.ChatInspectorListener;
import com.thepepeyt.geocraft.listeners.VanishListener;
import com.thepepeyt.geocraft.utils.ChatManager;
import com.thepepeyt.geocraft.utils.MessageManager;
import com.thepepeyt.geocraft.utils.TeleportManager;
import com.thepepeyt.geocraft.utils.VanishManager;
import fr.mrmicky.fastinv.FastInvManager;
import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main extends JavaPlugin {



    private CommandFramework commandFramework;
    
    @Getter
    private final TeleportManager teleportManager = new TeleportManager(this);

    @Getter
    private final ChatManager chatManager = new ChatManager(this);

    @Getter
    private final VanishManager vanishManager = new VanishManager(this);


    @Getter
    private MessageManager messageManager;


    @Override
    public void onEnable() {

        messageManager = new MessageManager(BukkitAudiences.create(this));
        saveDefaultConfig();





        commandFramework = new CommandFramework(this, this);
        commandFramework.registerCommands(new PingCommand(this));
        commandFramework.registerCommands(new GamemodeCommand(this));
        commandFramework.registerCommands(new ChatCommand(this));
        commandFramework.registerCommands(new SetSpawnCommand(this));
        commandFramework.registerCommands(new SpawnCommand(this));
        commandFramework.registerCommands(new InvseeCommand(this));
        commandFramework.registerCommands(new TphereCommand(this));
        commandFramework.registerCommands(new TptoCommand(this));
        commandFramework.registerCommands(new VanishCommand(this));
        commandFramework.registerCommands(new HealCommand(this));
        commandFramework.registerCommands(new EnderChestCommand(this));
        commandFramework.registerCommands(new WhoISCommand(this));
        commandFramework.registerCommands(new HatCommand(this));
        commandFramework.registerCommands(new DisposeCommand(this));
        this.getServer().getPluginManager().registerEvents(new ChatInspectorListener(this), this);
        this.getServer().getPluginManager().registerEvents(new VanishListener(this), this);
        FastInvManager.register(this);

        commandFramework.setAnyMatch(arguments -> {
            if (arguments.isArgumentsEmpty()) return;

            String label = arguments.getLabel(), arg = arguments.getArgument(0);

            List<StringMatcher.Match> matches = StringMatcher.match(arg, commandFramework.getCommands().stream().map(cmd -> cmd.name().replace(label + ".", "")).collect(Collectors.toList()));

            if (!matches.isEmpty()) {
                final String message = "Did you mean %command%?".replace("%command%", label + " " + matches.get(0).getMatch());
                this.getMessageManager().sendMessage(message, arguments.getSender());
            }
        });
    }






    @Override
    public void onDisable(){

    }
}
