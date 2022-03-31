package com.thepepeyt.geocraft.utils;

import lombok.Getter;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    @Getter
    private BukkitAudiences bukkitAudiences;


    @Getter
    private final LegacyComponentSerializer BUKKIT = BukkitComponentSerializer.legacy();

    public MessageManager(BukkitAudiences bukkitAudiences){
        this.bukkitAudiences = bukkitAudiences;
    }

    public Component getComponent(String message){
        return MiniMessage.markdown().parse(message);
    }

    public void sendMessage(String message, Player player){

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', BUKKIT.serialize(getComponent(message))));
    }

    public void sendMessage(Component message, Player player){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', BUKKIT.serialize(message)));
    }

    public void sendActionBar(Player player, Component component){
        bukkitAudiences.player(player).sendActionBar(component);
    }

    public String getString(String message){
        return BUKKIT.serialize(getComponent(message));
    }


    public void sendFancyMessage(String type, String title, String desc, Player player){
        sendMessage("", player);
        sendMessage("", player);
        sendMessage("             <#d7a220>\u2620</#d7a220> <#bab469>|</#bab469> <color:#8968cd>" + type + "</color:#8968cd> <#bab469>|</#bab469> <#d7a220>\u2620", player);
        sendMessage("<#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666           \u2726</strikethrough>| <#d7a220>\u231B</#d7a220> |<#bab469><strikethrough>\u2726           \u2666 <bold>\u02D3</bold></strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("        <#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666              \u2666 \u02D2</strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("", player);
        sendMessage("               <#d7a220>\u2693 <white>" + title + "</white> \u2693", player);
        sendMessage("            "+ desc + "", player);
        sendMessage("", player);
        sendMessage("        <#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666              \u2666 \u02D2</strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("", player);
        sendMessage("", player);
    }

    public void sendFancyMessageList(String type, String title, String desc, Player player){
        sendMessage("", player);
        sendMessage("", player);
        sendMessage("             <#d7a220>\u2620</#d7a220> <#bab469>|</#bab469> <color:#8968cd>" + type + "</color:#8968cd> <#bab469>|</#bab469> <#d7a220>\u2620", player);
        sendMessage("<#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666           \u2726</strikethrough>| <#d7a220>\u231B</#d7a220> |<#bab469><strikethrough>\u2726           \u2666 <bold>\u02D3</bold></strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("        <#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666              \u2666 \u02D2</strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("", player);
        sendMessage("               <#d7a220>\u2693 <white>" + title + "</white> \u2693", player);
        sendMessage("", player);
        sendMessage(desc, player);
        sendMessage("", player);
        sendMessage("        <#d7a220>\u2730</#d7a220> <#bab469><strikethrough>\u02D2 \u2666              \u2666 \u02D2</strikethrough> <#d7a220>\u2730</#d7a220>", player);
        sendMessage("", player);
        sendMessage("", player);
    }

}
