package com.thepepeyt.geocraft.gui;

import com.thepepeyt.geocraft.Main;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class ChatInspectorGUI extends FastInv {

    private Main plugin;



    public ChatInspectorGUI(Main plugin) {
        super(9, plugin.getMessageManager().getString("<#8968cd>Inspektor Czatu"));

        Material wool = plugin.getChatManager().isChat() ? Material.GREEN_WOOL : Material.RED_WOOL;
        String wool_name = plugin.getChatManager().isChat() ? plugin.getMessageManager().getString("<red>Wy\u0142\u0105cz czat") : plugin.getMessageManager().getString("<green>W\u0142\u0105cz czat");

        for (int border : getBorders()) {
            setItem(border, new ItemBuilder(Material.BLACK_STAINED_GLASS)
                    .name(plugin.getMessageManager().getString("<gold>Inspektor Czatu")).build());

        }


        setItem(0, new ItemBuilder(wool)
                .name(wool_name)
                .build(), gui -> {
            plugin.getChatManager().toggleChat();
            String isworking = !plugin.getChatManager().isChat() ? "wy\u0142\u0105czony" : "w\u0142\u0105czony";
            Bukkit.getOnlinePlayers().forEach(player -> plugin.getMessageManager().sendFancyMessage("HalfWorld",
                    "Czat",
                    "  Czat zosta\u0142\n       " + isworking + " przez " + gui.getWhoClicked().getName(), player));
            gui.getWhoClicked().closeInventory();
        });

        setItem(1, new ItemBuilder(Material.FEATHER)
                .name(plugin.getMessageManager().getString("<blue>Wyczy\u015B\u0107 czat")).build(), gui -> {

            plugin.getChatManager().clearChat();
            Bukkit.getOnlinePlayers().forEach(player -> plugin.getMessageManager().sendFancyMessage("HalfWorld",
                    "Czat", "  Czat zosta\u0142\n      wyczyszczony przez " + gui.getWhoClicked().getName(), player));
            gui.getWhoClicked().closeInventory();

        });


    }
}
