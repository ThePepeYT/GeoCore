package com.thepepeyt.geocraft.gui;

import com.thepepeyt.geocraft.Main;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class TeleportGUI extends FastInv {

    private Main plugin;

    public TeleportGUI(Main plugin, String type) {
        super(54, plugin.getMessageManager().getString("<#8968cd>Teleportacja"));
        this.plugin =plugin;

        for (int i=0;i<super.getInventory().getSize();i++) {
            setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS)
                    .name(plugin.getMessageManager().getString("<gold>Teleportacja")).build());

        }

        var players = Bukkit.getOnlinePlayers().stream().toList();

        for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
            Player player = players.get(i);
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD); // Create a new ItemStack of the Player Head type.
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta(); // Get the created item's ItemMeta and cast it to SkullMeta so we can access the skull properties
            skullMeta.setOwningPlayer(player); // Set the skull's owner so it will adapt the skin of the provided username (case sensitive).
            skull.setItemMeta(skullMeta);



            setItem(i, new ItemBuilder(skull).name(plugin.getMessageManager().getString("<green>" + players.get(i).getName())).build(), gui ->{
                if(type.equalsIgnoreCase("to")){
                    gui.getWhoClicked().teleport(player);
                    plugin.getMessageManager().sendFancyMessage("System", "Teleportacja", "<green>Pomy\u015Blnie przeteleportowano\n               do " + player.getName(), (Player) gui.getWhoClicked());
                }
                else{
                    player.teleport(gui.getWhoClicked());
                    plugin.getMessageManager().sendFancyMessage("System", "Teleportacja", "<green>Pomy\u015Blnie przeteleportowano\n               " + player.getName() + " do ciebie", (Player) gui.getWhoClicked());
                }

            });
        }


    }
}
