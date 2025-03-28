package me.vuxaer.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class RemoveParkourCommand implements TabCompleter {
	
	private Parkour parkour;
	
	public RemoveParkourCommand(Parkour parkour) {
        this.parkour = parkour;
    }
	
	public void removeFromConfig(Player p, String name) {
		ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");
		
		if (parkoursSection == null || !parkoursSection.contains(name)) {
			p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
			return;
		}
		p.sendMessage(ChatColor.RED + "Successfully removed the parkour '" + ChatColor.WHITE + name + ChatColor.RED + "'.");
		
		// Get the current set start/end pressure plates and remove them
		Location startPlate = parkour.getConfig().getLocation("parkours." + name + ".start");
		Location endPlate = parkour.getConfig().getLocation("parkours." + name + ".end");
		if (startPlate != null) {
			startPlate.getBlock().setType(Material.AIR);
		}
		
		if (endPlate != null) {
			endPlate.getBlock().setType(Material.AIR);
		}
		
		// Remove parkour from the config
		parkour.getConfig().set("parkours." + name, null);
		parkour.saveConfig();
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
}
