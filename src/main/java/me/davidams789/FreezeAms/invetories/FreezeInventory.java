package me.davidams789.FreezeAms.invetories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FreezeInventory implements Listener{

    public static HashMap<Player, String> freeze = new HashMap<Player, String>(); // () Lugar de almacenamiento del jugador (freeze) y staff

    @SuppressWarnings("deprecation")
    public void createInventory(final Player p, String staff){
        // ! Creacion del inventario
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&cHas sido freezeado"));

        /* Item Aceptar */

        ItemStack accept = new ItemStack(35,1, (short) 5);
        ItemMeta aAccept = accept.getItemMeta();

        // * Nombre al item Aceptar
        aAccept.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lAceptar Hacks"));

        // * Lore al item Aceptar
        List<String> ac = new ArrayList<String>();
        ac.add("");
        ac.add(ChatColor.translateAlternateColorCodes('&', "&7Al aceptar el uso de hacks recibiras un baneo de 30 dias"));
        aAccept.setLore(ac);

        // * Seteo del item Aceptar
        accept.setItemMeta(aAccept);
        inv.setItem(3, accept);

        /* Item SS */

        ItemStack ss = new ItemStack(35,1,(short) 14);
        ItemMeta sSS = ss.getItemMeta();

        // * Nombre al item SS
        sSS.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lHacer SS"));

        // * Lore al item SS
        List<String> s = new ArrayList<String>();
        s.add("");
        s.add(ChatColor.translateAlternateColorCodes('&', "&7Al realizar la SS y encontar hacks recibiras un baneo de 60 dias"));
        sSS.setLore(s);

        // * Seteo del item SS
        ss.setItemMeta(sSS);
        inv.setItem(5, ss);

        p.openInventory(inv);
    }
}
