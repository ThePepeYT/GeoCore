package com.thepepeyt.geocraft.commands.api;

import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An utility class to use command arguments without external
 * Bukkit parameters and includes some useful methods to improve
 * user's code.
 *
 * @author Despical
 * @since 1.0.0
 */
public class CommandArguments {

    private final CommandSender commandSender;
    private final Command command;
    private final String label, arguments[];

    public CommandArguments(CommandSender commandSender, Command command, String label, String... arguments) {
        this.commandSender = commandSender;
        this.command = command;
        this.label = label;
        this.arguments = arguments;
    }

    /**
     * Do not try to cast objects except subclasses of {@link CommandSender}
     * otherwise {@link ClassCastException} will occur. Also casting for {@link Player}
     * or {@link org.bukkit.command.ConsoleCommandSender} isn't needed.
     *
     * @return sender of command as Player or CommandSender
     */
    @NotNull
    public <T extends CommandSender> T getSender() {
        return (T) commandSender;
    }

    /**
     * @return base command.
     */
    @NotNull
    public Command getCommand() {
        return command;
    }

    /**
     * @return label of the command.
     */
    @NotNull
    public String getLabel() {
        return label;
    }

    /**
     * @return arguments of the command.
     */
    @NotNull
    public String[] getArguments() {
        return arguments;
    }

    /**
     * @param i index
     * @return indexed element or null if index out of bounds
     */
    @Nullable
    public String getArgument(int i) {
        return arguments.length > i && i >= 0 ? arguments[i] : null;
    }

    /**
     * @return true if command arguments are empty otherwise false
     */
    public boolean isArgumentsEmpty() {
        return arguments.length == 0;
    }

    /**
     * Sends message to sender without receiving command
     * sender.
     *
     * @param message to send
     */
    public void sendMessage(String message) {
        if (message == null) return;
        commandSender.sendMessage(message);
    }

    /**
     * Checks if command sender is console.
     *
     * @return true if sender is console otherwise false
     */
    public boolean isSenderConsole() {
        return !isSenderPlayer();
    }

    /**
     * Checks if command sender is player.
     *
     * @return true if sender is player otherwise false
     */
    public boolean isSenderPlayer() {
        return commandSender instanceof Player;
    }

    /**
     * Checks if command sender has specified permission.
     *
     * @param permission to check
     * @return true if sender has permission otherwise false
     */
    public boolean hasPermission(String permission) {
        return commandSender.hasPermission(permission);
    }

    /**
     * @return length of the arguments
     */
    public int getArgumentsLength() {
        return arguments.length;
    }
}