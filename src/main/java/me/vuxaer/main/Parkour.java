package me.vuxaer.main;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import me.vuxaer.commands.ParkourCommand;

public class Parkour extends JavaPlugin {
	
	public void onEnable() {
		this.saveDefaultConfig();
		this.getLogger().log(Level.INFO, "Plugin successfully enabled.");
		this.getCommand("parkour").setExecutor(new ParkourCommand(this));
	}
	
	public void onDisable() {
		this.getLogger().log(Level.INFO, "Plugin successfully disabled");
	}

}
