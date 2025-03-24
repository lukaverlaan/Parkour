package me.vuxaer.commands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class RemoveParkourCommand {
	
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
		parkour.getConfig().set("parkours." + name, null);
		parkour.saveConfig();
	}
}
