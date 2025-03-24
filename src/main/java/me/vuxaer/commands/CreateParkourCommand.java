package me.vuxaer.commands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class CreateParkourCommand {
	
	private Parkour parkour;
	
	public CreateParkourCommand(Parkour parkour) {
        this.parkour = parkour;
    }
	
	public void addToConfig(Player p, String name) {
		ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");
		
		if (parkoursSection == null) {
	        parkoursSection = parkour.getConfig().createSection("parkours");
	    }
		
		if (parkoursSection.contains(name)) {
			p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' already exists.");
			return;
		}
		p.sendMessage(ChatColor.GREEN + "Successfully created a new parkour '" + ChatColor.WHITE + name + ChatColor.GREEN + "'.");
		parkour.getConfig().createSection("parkours." + name);
		parkour.saveConfig();
	}
}
