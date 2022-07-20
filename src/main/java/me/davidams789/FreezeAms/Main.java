package me.davidams789.FreezeAms;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.davidams789.FreezeAms.commands.Freeze;
import me.davidams789.FreezeAms.events.FreezeEvent;

public class Main extends JavaPlugin {

    public ArrayList<Player> ss = new ArrayList<Player>();

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&9&lFreeze&8&l] &aEl plugin se activo correctamente"));
        registerCommands();
        registerEvents();
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&9&lFreeze&8&l] &cEl plugin ha sido desactivado"));
    }

    public void registerCommands() {
        getCommand("ss").setExecutor(new Freeze());
    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new FreezeEvent(this), this);
    }
}
