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

public class SetSpawnParkourCommand implements TabCompleter {
	private Parkour parkour;
	
	public SetSpawnParkourCommand(Parkour parkour) {
		this.parkour = parkour;
	}
	
	public void setTeleport(Player p, String name) {
	    ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");

	    if (parkoursSection == null || !parkoursSection.contains(name)) {
	        p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
	        return;
	    }

	    Location playerLocation = p.getLocation();

	    // Floor the coordinates before saving
	    Location flooredLocation = new Location(
	        playerLocation.getWorld(),
	        Math.floor(playerLocation.getX()),
	        Math.floor(playerLocation.getY()),
	        Math.floor(playerLocation.getZ()),
	        playerLocation.getYaw(),
	        playerLocation.getPitch()
	    );

	    parkour.getConfig().set("parkours." + name + ".spawn", flooredLocation);
	    parkour.saveConfig();
	    
	    p.sendMessage(ChatColor.GREEN + "Successfully set the parkour spawn location for '" + ChatColor.WHITE + name + ChatColor.GREEN + "'.");
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
	
	public Location getSpawn(String parkourName) {
		return parkour.getConfig().getLocation("parkours." + parkourName + ".spawn");
	}
}
