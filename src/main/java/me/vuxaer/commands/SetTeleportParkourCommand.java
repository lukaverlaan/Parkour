package me.vuxaer.commands;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class SetTeleportParkourCommand {
	private Parkour parkour;
	
	public SetTeleportParkourCommand(Parkour parkour) {
		this.parkour = parkour;
	}
	
	public void setTeleport(Player p, String name) {
		ConfigurationSection parkoursSection = parkour.getConfig().getConfigurationSection("parkours");
		
		if (parkoursSection == null || !parkoursSection.contains(name)) {
			p.sendMessage(ChatColor.RED + "The parkour '" + ChatColor.WHITE + name + ChatColor.RED + "' does not exist.");
			return;
		}
		
		parkour.getConfig().set("parkours." + name + ".spawn", p.getLocation());
		parkour.saveConfig();
		p.sendMessage(ChatColor.GREEN + "Successfully set the parkour spawn location for '" + ChatColor.WHITE + name + ChatColor.GREEN + "'.");
	}
}
