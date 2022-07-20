package me.davidams789.FreezeAms.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.davidams789.FreezeAms.invetories.FreezeInventory;

public class Freeze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("freezeams.use") || !p.isOp()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cNo tienes suficientes permisos para ejecutar ese comando."));
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&9&lFreeze&8&l] &cUso: /ss <Jugador>"));
        } else if (args.length > 1) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l[&9&lFreeze&8&l] &cUso: /ss <Jugador>"));
        } else {
            Player t = Bukkit.getPlayer(args[0]);

            if (t == null) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&9&lFreeze&8&l] &cEse jugador no existe o no se encuentra conectado."));
            } else {
                if (FreezeInventory.freeze.containsKey(t)) {
                    FreezeInventory.freeze.remove(t);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&9&lFreeze&8&l] &cHas desfreezeado al jugador &e" + t.getName()));
                } else {
                    FreezeInventory.freeze.put(t, p.getName());
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8&l[&9&lFreeze&8&l] &aHas freezeado al jugador &e" + t.getName()));
                    t.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cHas sido freezeado. Por favor no te desconectes"));
                    FreezeInventory inv = new FreezeInventory();
                    inv.createInventory(t, p.getName());
                }
            }
        }

        return true;
    }

}
