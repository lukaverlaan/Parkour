package me.vuxaer.commands;

import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class ListParkourCommand {
	private Parkour parkour;
	
	public ListParkourCommand(Parkour parkour) {
		this.parkour = parkour;
	}
	
	public void list(Player p) {
		Set<String> parkours = parkour.getConfig().getConfigurationSection("parkours").getKeys(false);
		if (!parkour.getConfig().contains("parkours") || parkours.isEmpty()) {
			p.sendMessage(ChatColor.RED + "No parkours have been created.");
			return;
		}
		
		// Format and send the list of parkours
	    p.sendMessage(ChatColor.GREEN + "Available parkours:");
	    p.sendMessage(ChatColor.WHITE + String.join(", ", parkours));
	}

}
