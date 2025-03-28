package me.vuxaer.listeners;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class StartEndInteractionListener implements Listener {
	private Parkour parkour;
	
	public StartEndInteractionListener(Parkour parkour) {
		this.parkour = parkour;
	}
	
	@EventHandler
	public void walkOverStartEnd(PlayerInteractEvent event) {
	    if (event.getAction().equals(Action.PHYSICAL)) {
	        Player player = event.getPlayer();
	        Location playerLocation = player.getLocation(); // Player's location (contains floating points)

	        Set<String> parkours = parkour.getConfig().getConfigurationSection("parkours").getKeys(false);
	        for (String parkourName : parkours) {
	            Location startBlockLocation = parkour.getConfig().getLocation("parkours." + parkourName + ".start");
	            Location endBlockLocation = parkour.getConfig().getLocation("parkours." + parkourName + ".end");

	            // Compare block-based locations
	            if (playerLocation.equals(startBlockLocation)) {
	                player.sendMessage(ChatColor.GREEN + "Successfully started parkouring.");
	            } else if (playerLocation.equals(endBlockLocation)) {
	                player.sendMessage(ChatColor.GREEN + "Successfully ended parkouring.");
	            }
	        }
	    }
	}

}
