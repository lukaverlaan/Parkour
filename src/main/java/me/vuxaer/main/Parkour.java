package me.vuxaer.main;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import me.vuxaer.commands.ParkourCommand;
import me.vuxaer.commands.RemoveParkourCommand;
import me.vuxaer.commands.SetSpawnParkourCommand;
import me.vuxaer.commands.StartEndParkourCommand;
import me.vuxaer.commands.TeleportParkourCommand;

public class Parkour extends JavaPlugin {
	
	public void onEnable() {
		this.saveDefaultConfig();
		this.getLogger().log(Level.INFO, "Plugin successfully enabled.");
		this.getCommand("parkour").setExecutor(new ParkourCommand(this));
		
		// Tab completers
		this.getCommand("parkour").setTabCompleter(new TeleportParkourCommand(this));
		this.getCommand("parkour").setTabCompleter(new SetSpawnParkourCommand(this));
		this.getCommand("parkour").setTabCompleter(new RemoveParkourCommand(this));
		this.getCommand("parkour").setTabCompleter(new StartEndParkourCommand(this));
	}
	
	public void onDisable() {
		this.getLogger().log(Level.INFO, "Plugin successfully disabled");
	}

}
