package com.thepepeyt.geocraft.gui;

import com.thepepeyt.geocraft.Main;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GamemodeGUI extends FastInv {

    private Main plugin;

    public GamemodeGUI(Main plugin, Player player){
        super(9, plugin.getMessageManager().getString("<#8968cd>Gamemode"));

        for (int border : getBorders()) {
            setItem(border, new ItemBuilder(Material.BLACK_STAINED_GLASS)
                    .name(plugin.getMessageManager().getString("<gold>Gamemode")).build());

        }



        setItem(3, new ItemBuilder(Material.ENCHANTED_GOLDEN_APPLE)
                .name(plugin.getMessageManager().getString("<#d7a220>Kreatywny"))
                .build(), gui -> {
            player.setGameMode(GameMode.CREATIVE);

            if (player.equals(gui.getWhoClicked())) {
                plugin.getMessageManager().sendFancyMessage("System", "Gamemode", plugin.getMessageManager().getString("  Zmieni\u0142e\u015B sw\u00F3j\n           tryb gry na kreatywny"), player);
                gui.getWhoClicked().closeInventory();
                return;
            }
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Tw\u00F3j tryb gry zosta\u0142 zmieniony na kreatywny", player);
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Zmieni\u0142e\u015B tryb gracza " + player + " na kreatywny", (Player) gui.getWhoClicked());
            gui.getWhoClicked().closeInventory();

        });

        setItem(4, new ItemBuilder(Material.DIAMOND_SWORD)
                .name(plugin.getMessageManager().getString("<red>Surwiwal"))
                .build(), gui -> {

            player.setGameMode(GameMode.SURVIVAL);

            if (player.equals(gui.getWhoClicked())) {
                plugin.getMessageManager().sendFancyMessage("System", "Gamemode", plugin.getMessageManager().getString("  Zmieni\u0142e\u015B sw\u00F3j\n          tryb gry na surwiwal"), player);
                gui.getWhoClicked().closeInventory();
                return;
            }
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Tw\u00F3j tryb gry zosta\u0142 zmieniony na surwiwal", player);
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Zmieni\u0142e\u015B tryb gracza " + player + " na surwiwal", (Player) gui.getWhoClicked());
            gui.getWhoClicked().closeInventory();

        });

        setItem(5, new ItemBuilder(Material.BARRIER)
                .name(plugin.getMessageManager().getString("<green>Spektator"))
                .build(), gui -> {
            player.setGameMode(GameMode.SPECTATOR);

            if (player.equals(gui.getWhoClicked())) {
                plugin.getMessageManager().sendFancyMessage("System", "Gamemode", plugin.getMessageManager().getString("  Zmieni\u0142e\u015B sw\u00F3j\n          tryb gry na spektatora"), player);
                gui.getWhoClicked().closeInventory();
                return;
            }
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Tw\u00F3j tryb gry zosta\u0142 zmieniony na spektatora", player);
            plugin.getMessageManager().sendFancyMessage("System", "Gamemode", "Zmieni\u0142e\u015B tryb gracza " + player + " na spektatora", (Player) gui.getWhoClicked());
            gui.getWhoClicked().closeInventory();
        });
        this.plugin = plugin;
    }


}
