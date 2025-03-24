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

public class StartEndParkourCommand implements TabCompleter {
	private Parkour parkour;
	
	public StartEndParkourCommand(Parkour parkour) {
		this.parkour = parkour;
	}
	
	public void setStart(Player p, String name) {
		ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");
		
		if (parkoursSection == null || !parkoursSection.contains(name)) {
			p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
			return;
		}
		String path = "parkours." + name + ".start";
		if (parkour.getConfig().contains(path)) {
	        parkour.getConfig().getLocation(path).getBlock().setType(Material.AIR);
	    }
		
		Location playerLoc = p.getLocation();
		parkour.getConfig().set("parkours." + name + ".start", playerLoc);
		parkour.saveConfig();
		playerLoc.getBlock().setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		p.sendMessage(ChatColor.GREEN + "Successfully set the start location for '" + ChatColor.WHITE + name + ChatColor.GREEN + "' to your current location.");
		
	}
	
	public void setEnd(Player p, String name) {
		ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");
		
		if (parkoursSection == null || !parkoursSection.contains(name)) {
			p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
			return;
		}
		String path = "parkours." + name + ".end";
		if (parkour.getConfig().contains(path)) {
	        parkour.getConfig().getLocation(path).getBlock().setType(Material.AIR);
	    }

		Location playerLoc = p.getLocation();
		parkour.getConfig().set("parkours." + name + ".end", playerLoc);
		parkour.saveConfig();
		playerLoc.getBlock().setType(Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		p.sendMessage(ChatColor.GREEN + "Successfully set the end location for '" + ChatColor.WHITE + name + ChatColor.GREEN + "' to your current location.");
		
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
