package me.vuxaer.listeners;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class StartEndBreakListener implements Listener {

	private Parkour parkour;
	
	public StartEndBreakListener(Parkour parkour)  {
		this.parkour = parkour;
	}
	
	@EventHandler
	public void onPressurePlateBreak(BlockBreakEvent event) {
	    Player player = event.getPlayer();
	    Location blockLocation = event.getBlock().getLocation(); // The block being broken
	    player.sendMessage("A block has been broken");
	    Set<String> parkours = parkour.getConfig().getConfigurationSection("parkours").getKeys(false);
	    for (String parkourName : parkours) {
	        Location startBlockLocation = parkour.getConfig().getLocation("parkours." + parkourName + ".start");
	        Location endBlockLocation = parkour.getConfig().getLocation("parkours." + parkourName + ".end");

	        // Also check the block *under* the pressure plates
	        Location startPlateUnder = startBlockLocation.clone().subtract(0, 1, 0);
	        Location endPlateUnder = endBlockLocation.clone().subtract(0, 1, 0);
	        
	        parkour.getServer().broadcastMessage("Block broken: " + blockLocation);
	        parkour.getServer().broadcastMessage("Start block: " + startBlockLocation);
	        parkour.getServer().broadcastMessage("End block: " + endBlockLocation);  
	        // Prevent breaking the plate and the block under it
	        if (blockLocation.equals(startBlockLocation) || blockLocation.equals(endBlockLocation) ||
	            blockLocation.equals(startPlateUnder) || blockLocation.equals(endPlateUnder)) {
	            event.setCancelled(true);
	            player.sendMessage(ChatColor.RED + "You cannot break a block that belongs to a parkour.");
	            return; // Stop checking once a match is found
	        }
	    }
	}


}
