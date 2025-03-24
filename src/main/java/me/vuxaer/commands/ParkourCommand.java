package me.vuxaer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.vuxaer.main.Parkour;
import net.md_5.bungee.api.ChatColor;

public class ParkourCommand implements CommandExecutor {
	
	private Parkour parkour;
	private CreateParkourCommand createParkour;
	private RemoveParkourCommand removeParkour;
	private SetTeleportParkourCommand teleportParkour;
	
	public ParkourCommand(Parkour parkour) {
		this.parkour = parkour;
		this.createParkour = new CreateParkourCommand(parkour);
		this.removeParkour = new RemoveParkourCommand(parkour);
		this.teleportParkour = new SetTeleportParkourCommand(parkour);
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
			p.sendMessage("/parkour settp <name>" + ChatColor.GRAY + " - " + ChatColor.WHITE + "Set the default parkour tp to your current location");
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
		
		if (args[0].equals("create")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour create <name>");
				return false;
			}
	        String name = args[1];
	        createParkour.addToConfig(p, name);
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
		
		if (args[0].equals("settp")) {
			if (args.length != 2) {
				p.sendMessage(ChatColor.RED + "Usage: /parkour settp <name>");
				return false;
			}
			
			String name = args[1];
			teleportParkour.setTeleport(p, name);
			return true;
		}
		
		// Handle unrecognized command
	    p.sendMessage(ChatColor.RED + "Unknown command. Type /parkour help for a list of commands.");
	    return false;
	}

}
