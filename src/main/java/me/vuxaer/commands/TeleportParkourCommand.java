package me.vuxaer.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class TeleportParkourCommand implements TabCompleter {
	private Parkour parkour;
	
	public TeleportParkourCommand(Parkour parkour) {
		this.parkour = parkour;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
	    if (args.length == 2) {
	        ConfigurationSection section = parkour.getConfig().getConfigurationSection("parkours");
	        if (section != null) {
	            return new ArrayList<>(section.getKeys(false)); // Return list of parkour names
	        }
	    }
	    return Collections.emptyList(); // Return an empty list to prevent default behavior (player names)
	}
	
	public void teleport(Player p, String name) {
	    ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");

	    if (parkoursSection == null || !parkoursSection.contains(name)) {
	        p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
	        return;
	    }

	    if (parkour.getConfig().get("parkours." + name + ".spawn") == null) {
	        p.sendMessage(ChatColor.RED + "A spawn location for '" + ChatColor.WHITE + name + ChatColor.RED + "' has not yet been set.");
	        p.sendMessage(ChatColor.RED + "Use the following command first: /parkour setspawn " + name);
	        return;
	    }

	    // Retrieve location properly
	    Location loc = parkour.getConfig().getLocation("parkours." + name + ".spawn");

	    p.teleport(loc);
	    p.sendMessage(ChatColor.GREEN + "Successfully teleported you to '" + ChatColor.WHITE + name + ChatColor.GREEN + "'.");
	}

}
