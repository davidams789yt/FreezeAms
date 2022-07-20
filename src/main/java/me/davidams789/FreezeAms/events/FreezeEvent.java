package me.davidams789.FreezeAms.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.davidams789.FreezeAms.Main;
import me.davidams789.FreezeAms.invetories.FreezeInventory;

public class FreezeEvent implements Listener {

    private Main plugin;

    public FreezeEvent(Main plugin){
        this.plugin = plugin;
    }

    /* Cuando el freezeado se intenta mover */

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location from = e.getFrom();
        Location to = e.getTo();

        String s = FreezeInventory.freeze.get(p);

        if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
            if (FreezeInventory.freeze.containsKey(p)) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEstas freezeado. No te puedes mover"));
                FreezeInventory inv = new FreezeInventory();
                inv.createInventory(p, s);
            } else if (plugin.ss.contains(p)){
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHas sido añadido a la lista de SS. No te puedes mover"));
            }
        }
    }

    /* Cuando el freezeado intenta poner un bloque */

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();

        if (FreezeInventory.freeze.containsKey(p)){
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEstas freezeado. No puedes poner bloques"));
        }
    }

    /* Cuando el freezeado intenta romper un bloque */

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();

        if (FreezeInventory.freeze.containsKey(p)){
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEstas freezeado. No puedes romper bloques"));
        }
    }

    /* Cuando el freezeado intenta dropear o agarrar un item */

    @EventHandler
    public void onPickUpItem (PlayerPickupItemEvent e){
        Player p = e.getPlayer();

        if (FreezeInventory.freeze.containsKey(p)){
            e.setCancelled(true);
        }
    }

    /* Cuando el freezeado le intentan hacer daño */

    @EventHandler
    public void damage(EntityDamageEvent e){
        if (FreezeInventory.freeze.containsKey((Player) e.getEntity())){
            e.setCancelled(true);
        }
    }

    /* Cuando el freezeado intenta hacer daño */

    @EventHandler
    public void toDamage(EntityDamageByEntityEvent e){
        if (FreezeInventory.freeze.containsKey((Player) e.getDamager())){
            e.setCancelled(true);
        }
    }

    /* Cuando a el freezeado le intenta cambiar la barra de hambre */

    @EventHandler
    public void foodChange(FoodLevelChangeEvent e){
        Player p = (Player) e.getEntity();

        if (FreezeInventory.freeze.containsKey(p)){
            e.setCancelled(true);
        }
    }

    /* Cuando el freezeado intenta interactuar */

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();

        if (FreezeInventory.freeze.containsKey(p)){
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cEstas freezeado. No puedes interactuar con objetos"));
        }
    }

    /* Interaccion con el inventario */

    // ! InventoryClickEvent
    @EventHandler
    public void onInteractInventory(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        String s = FreezeInventory.freeze.get(p);
        Player staff = Bukkit.getPlayer(s);

        if (e.getInventory().getName().equals("§cHas sido freezeado")){
            if(e.getCurrentItem() != null){
                if (e.getSlot() == 3){
                    if(!staff.isOnline()){
                        p.closeInventory();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHas sido desfreezeado ya que el staff se desconecto"));
                        return;
                    }
    
                    p.closeInventory();
                    FreezeInventory.freeze.remove(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHas admitido el uso de hacks. Seras baneado por 30 dias"));
                    staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&9&lFreeze&8&l] &aEl usuario &e" + p.getName() + " &aa admitido el uso de hacks"));
                    return;
                } else if (e.getSlot() == 5){
                    if(!staff.isOnline()){
                        p.closeInventory();
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHas sido desfreezeado ya que el staff se desconecto"));
                        return;
                    }
    
                    p.closeInventory();
                    FreezeInventory.freeze.remove(p);
                    plugin.ss.add(p);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHas decidio hacer una revision"));
                    staff.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&9&lFreeze&8&l] &cEl usuario &e" + p.getName() + " &aa decidio realizar una SS"));
                    return;
                }
                e.setCancelled(true);
            }
        }
    }
}
