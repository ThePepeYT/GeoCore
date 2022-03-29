package com.thepepeyt.geocraft.commands.api;
import com.thepepeyt.geocraft.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Main class of the framework to register commands, add tab
 * completions and implement a consumer to run if there is no
 * matched commands related this framework.
 *
 * @author Despical
 * @since 1.0.0
 */
public class CommandFramework implements CommandExecutor, TabCompleter {

    /**
     * Main instance of framework.
     */
    @NotNull
    private final Plugin plugin;

    @NotNull
    private final Main main;

    /**
     * Map of registered commands by framework.
     */
    @NotNull
    private final Map<Command, Map.Entry<Method, Object>> commands = new HashMap<>();

    /**
     * Map of registered tab completions by framework.
     */
    @NotNull
    private final Map<Completer, Map.Entry<Method, Object>> completions = new HashMap<>();

    /**
     * Map of registered command cooldowns by framework.
     */
    @NotNull
    private final Map<CommandSender, Long> cooldowns = new HashMap<>();

    /**
     * Consumer to accept if there is no matched commands related framework.
     */
    @Nullable
    private Consumer<CommandArguments> anyMatchConsumer;

    /**
     * Default command map of Bukkit.
     */
    @Nullable
    private CommandMap commandMap;

    public CommandFramework(@NotNull Plugin plugin, Main main) {
        this.plugin = plugin;
        this.main = main;

        if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
            SimplePluginManager manager = (SimplePluginManager) plugin.getServer().getPluginManager();

            try {
                Field field = SimplePluginManager.class.getDeclaredField("commandMap");
                field.setAccessible(true);

                commandMap = (CommandMap) field.get(manager);
            } catch (IllegalArgumentException | SecurityException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        ONLY_BY_PLAYERS = main.getMessageManager().getComponent("This command is only executable by players!");
        ONLY_BY_CONSOLE = main.getMessageManager().getComponent("This command is only executable by console!");
        NO_PERMISSION = main.getMessageManager().getComponent("You don't have enough permission to execute this command!");
        SHORT_OR_LONG_ARG_SIZE  = main.getMessageManager().getComponent("Required argument length is less or greater than needed!");
        WAIT_BEFORE_USING_AGAIN = main.getMessageManager().getComponent("You have to wait before using this command again!");


    }

    /**
     * Consumer to accept if there is no matched commands related framework.
     *
     * @param anyMatchConsumer to be accepted if there is no matched commands
     */
    public void setAnyMatch(@NotNull Consumer<CommandArguments> anyMatchConsumer) {
        this.anyMatchConsumer = anyMatchConsumer;
    }

    /**
     * Register command methods in object class.
     *
     * @param instance object class
     */
    public void registerCommands(@NotNull Object instance) {
        for (Method method : instance.getClass().getMethods()) {
            Command command = method.getAnnotation(Command.class);

            if (command != null) {
                if (method.getParameterTypes().length > 0 && method.getParameterTypes()[0] != CommandArguments.class) {
                    continue;
                }

                registerCommand(command, method, instance);
            } else if (method.getAnnotation(Completer.class) != null) {
                completions.put(method.getAnnotation(Completer.class), new AbstractMap.SimpleEntry<>(method, instance));
            }
        }
    }

    /**
     * Register the command with given parameters.
     *
     * @param command of the main object
     * @param method that command will run
     * @param instance of the method above
     */
    private void registerCommand(Command command, Method method, Object instance) {
        commands.put(command, new AbstractMap.SimpleEntry<>(method, instance));

        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            String splittedCommand = command.name().split("\\.")[0];

            PluginCommand pluginCommand = constructor.newInstance(splittedCommand, plugin);
            pluginCommand.setTabCompleter(this);
            pluginCommand.setExecutor(this);
            pluginCommand.setUsage(command.usage());
            pluginCommand.setPermission(command.permission());
            pluginCommand.setDescription(command.desc());
            pluginCommand.setAliases(Arrays.asList(command.aliases()));

            commandMap.register(splittedCommand, pluginCommand);
        } catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    // Error Message Handler

    public Component ONLY_BY_PLAYERS;
    public Component ONLY_BY_CONSOLE;
    public Component NO_PERMISSION;
    public Component SHORT_OR_LONG_ARG_SIZE;
    public Component  WAIT_BEFORE_USING_AGAIN;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command cmd, @NotNull String label, String[] args) {
        for (Map.Entry<Command, Map.Entry<Method, Object>> entry : commands.entrySet()) {
            Command command = entry.getKey();
            String splitted[] = command.name().split("\\."), allArgs = args.length == 0 ? "" : String.join(".", Arrays.copyOfRange(args, 0, splitted.length - 1));
            String cmdName = (command.name().contains(".") ? splitted[0] : cmd.getName()) + (splitted.length == 1 && allArgs.isEmpty() ? "" : "." + allArgs);

            if (command.name().equalsIgnoreCase(cmdName) || Stream.of(command.aliases()).anyMatch(cmdName::equalsIgnoreCase)) {
                if (!sender.hasPermission(command.permission())) {
                    main.getMessageManager().sendMessage("hello", (Player) sender);
                    return true;
                }

                if (command.senderType() == Command.SenderType.PLAYER && !(sender instanceof Player)) {
                    System.out.println(ONLY_BY_PLAYERS);
                    return true;
                }

                if (command.senderType() == Command.SenderType.CONSOLE && sender instanceof Player) {
                    main.getMessageManager().sendMessage(ONLY_BY_CONSOLE, (Player) sender);
                    return true;
                }

                if (cooldowns.containsKey(sender)) {
                    if (command.cooldown() > 0 && ((System.currentTimeMillis() - cooldowns.get(sender)) / 1000) % 60 <= command.cooldown()) {
                        sender.sendMessage(WAIT_BEFORE_USING_AGAIN);
                        return true;
                    } else {
                        cooldowns.remove(sender);
                    }
                } else {
                    cooldowns.put(sender, System.currentTimeMillis());
                }

                String[] newArgs = Arrays.copyOfRange(args, splitted.length - 1, args.length);

                if (args.length >= command.min() + splitted.length - 1 && newArgs.length <= (command.max() == -1 ? newArgs.length + 1 : command.max())) {
                    try {
                        entry.getValue().getKey().invoke(entry.getValue().getValue(), new CommandArguments(sender, cmd, label, newArgs));
                        return true;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                        return true;
                    }
                } else {
                    sender.sendMessage(SHORT_OR_LONG_ARG_SIZE);
                    return true;
                }
            }
        }

        if (anyMatchConsumer != null) {
            anyMatchConsumer.accept(new CommandArguments(sender, cmd, label, args));
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, String[] args) {
        for (Map.Entry<Completer, Map.Entry<Method, Object>> entry : completions.entrySet()) {
            Completer completer = entry.getKey();

            if (command.getName().equalsIgnoreCase(completer.name()) || Stream.of(completer.aliases()).anyMatch(command.getName()::equalsIgnoreCase)) {
                try {
                    Object instance = entry.getValue().getKey().invoke(entry.getValue().getValue(), new CommandArguments(sender, command, label, args));

                    return (List<String>) instance;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * Get the copied list of registered commands.
     *
     * @return list of commands.
     */
    @NotNull
    public List<Command> getCommands() {
        return new ArrayList<>(commands.keySet());
    }
}