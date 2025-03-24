package me.vuxaer.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class ParkourCommand implements CommandExecutor {
	
	private Parkour parkour;
	private CreateParkourCommand createParkour;
	private StartEndParkourCommand startEndParkour;
	private RemoveParkourCommand removeParkour;
	private SetSpawnParkourCommand setTeleportParkour;
	
	private TeleportParkourCommand teleportParkour;
	private ListParkourCommand listParkour;
	
	public ParkourCommand(Parkour parkour) {
		this.parkour = parkour;
		this.createParkour = new CreateParkourCommand(parkour);
		this.startEndParkour = new StartEndParkourCommand(parkour);
		this.removeParkour = new RemoveParkourCommand(parkour);
		this.setTeleportParkour = new SetSpawnParkourCommand(parkour);
		
		this.teleportParkour = new TeleportParkourCommand(parkour);
		this.listParkour = new ListParkourCommand(parkour);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This is a player only command.");
		}
		
		Player p = (Player) sender;
		
		if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("help"))) {
			p.sendMessage("");
			p.sendMessage("--------------------");
			p.sendMessage("" + ChatColor.AQUA + ChatColor.BOLD + "SimpleParkour v1.0");
			p.sendMessage(ChatColor.GRAY + "Made by Vuxaer");
			p.sendMessage("--------------------");
			p.sendMessage("(Page 1)");
			p.sendMessage("" + ChatColor.AQUA + ChatColor.BOLD + "Setup Commands:");
			p.sendMessage("/parkour create <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Create a new parkour");
			p.sendMessage("/parkour setstart <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Set the parkour start to your current location");
			p.sendMessage("/parkour setend <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Set the parkour end to your current location");
			p.sendMessage("/parkour setspawn <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Set the default parkour spawn to your current location");
			p.sendMessage("/parkour remove <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Remove an existing parkour");
			p.sendMessage("");
			p.sendMessage(ChatColor.AQUA + "Use" + ChatColor.WHITE + " /parkour help 2" + ChatColor.AQUA + " for " + ChatColor.WHITE + ChatColor.BOLD + "In-game commands" + ChatColor.RESET + ChatColor.AQUA + "!");
			p.sendMessage("");
			return true;
		}
		
		if (args[0].equals("help") && args[1].equals("2")) {
			p.sendMessage("");
			p.sendMessage("--------------------");
			p.sendMessage("" + ChatColor.AQUA + ChatColor.BOLD + "SimpleParkour v1.0");
			p.sendMessage(ChatColor.GRAY + "Made by Vuxaer");
			p.sendMessage("--------------------");
			p.sendMessage("(Page 2)");
			p.sendMessage("" + ChatColor.AQUA + ChatColor.BOLD + "In-game Commands:");
			p.sendMessage("/parkour list" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Check out all our existing parkours");
			p.sendMessage("/parkour tp <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Teleport to a specific parkour");
			p.sendMessage("/parkour score <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Check out your stats for a specific parkour");
			p.sendMessage("");
			return true;
		}
		
		// Setup commands
		
		if (args[0].equals("create")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour create <name>");
				return false;
			}
	        String name = args[1];
	        createParkour.addToConfig(p, name);
	        return true;
	    }
		
		if (args[0].equals("setstart")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour setstart <name>");
				return false;
			}
			
			String name = args[1];
			startEndParkour.setStart(p, name);
			return true;
		}
		
		if (args[0].equals("setend")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour setend <name>");
				return false;
			}
			
			String name = args[1];
			startEndParkour.setEnd(p, name);
			return true;
		}
		
		if (args[0].equals("remove")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour remove <name>");
				return false;
			}
			
			String name = args[1];
			removeParkour.removeFromConfig(p, name);
			return true;
		}
		
		if (args[0].equals("setspawn")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour setspawn <name>");
				return false;
			}
			
			String name = args[1];
			setTeleportParkour.setTeleport(p, name);
			return true;
		}
		
		// In-game commands
		
		if (args[0].equals("tp")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour tp <name>");
				return false;
			}
			
			String name = args[1];
			teleportParkour.teleport(p, name);
			return true;
		}
		
		if (args[0].equals("list")) {
			listParkour.list(p);
			return true;
		}
		
		if (args[0].equals("auto")) {
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			double z = p.getLocation().getZ();
			
			Location loc2 = new Location(p.getWorld(), x + 6, y, z-2);
			Location loc3 = new Location(p.getWorld(), x + 6, y, z-1);
			Location loc4 = new Location(p.getWorld(), x + 6, y, z);
			Location loc5 = new Location(p.getWorld(), x + 6, y, z + 1);
			Location loc6 = new Location(p.getWorld(), x + 6, y, z + 2);
			Location loc1 = new Location(p.getWorld(), x + 6, y + 1, z - 2);
			Location loc7 = new Location(p.getWorld(), x + 6, y + 1, z + 2);
			Location loc8 = new Location(p.getWorld(), x + 6, y + 3, z - 1);
			Location loc9 = new Location(p.getWorld(), x + 6, y + 3, z + 1);
			List<Location> locs = new ArrayList<>();
			locs.add(loc1);
			locs.add(loc2);
			locs.add(loc3);
			locs.add(loc4);
			locs.add(loc5);
			locs.add(loc6);
			locs.add(loc7);
			locs.add(loc8);
			locs.add(loc9);
			
			
			new BukkitRunnable() {
			    private int index = 0; // To track which block to place

			    @Override
			    public void run() {
			        if (index < locs.size()) {
			            // Get the current location from the list
			            Location currentLoc = locs.get(index);

			            // Place a DIAMOND_BLOCK at the current location
			            currentLoc.getBlock().setType(Material.COBBLESTONE);

			            // Move to the next index
			            index++;
			        } else {
			            // If all blocks are placed, cancel the task
			            cancel();
			        }
			    }
			}.runTaskTimer(parkour, 0L, 4L); // Use the parkour instance here directly
			
			new BukkitRunnable() {
			    private int index = 0; // To track which block to place

			    @Override
			    public void run() {
			        if (index < locs.size()) {
			            // Get the current location from the list
			            Location currentLoc = locs.get(index);

			            // Place a DIAMOND_BLOCK at the current location
			            currentLoc.getBlock().setType(Material.AIR);

			            // Move to the next index
			            index++;
			        } else {
			            // If all blocks are placed, cancel the task
			            cancel();
			        }
			    }
			}.runTaskTimer(parkour, 60L, 4L); // Use the parkour instance here directly
			return true;
	    }
		
		// Handle unrecognized command
	    p.sendMessage(ChatColor.RED + "Unknown command. Type /parkour help for a list of commands.");
	    return false;
	}
}
