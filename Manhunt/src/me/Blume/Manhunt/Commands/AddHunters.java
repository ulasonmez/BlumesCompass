package me.Blume.Manhunt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.Manhunt.Main;
import me.Blume.Manhunt.Compass.CompassItem;

public class AddHunters implements CommandExecutor {
	private CompassItem items = new CompassItem();
	@SuppressWarnings("unused")
	private Main plugin;
	public AddHunters(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("addhunter")) {
			if(sender instanceof Player) {
				if(args.length==0) {
					Player hunter = (Player) sender;
					if(!plugin.getHunter().contains(hunter.getUniqueId())) {
						plugin.addHunter(hunter);
						hunter.getInventory().addItem(items.giveCompass());
						return true;
					}
					else {
						sender.sendMessage(ChatColor.AQUA+"You"+ChatColor.RED+" are already a hunter!");
					}
				}
				else if(args.length==1) {
					Player hunter = Bukkit.getServer().getPlayerExact(args[0]);
					if(hunter!=null) {
						if(!plugin.getHunter().contains(hunter.getUniqueId())) {
							plugin.addHunter(hunter);
							hunter.getInventory().addItem(items.giveCompass());
							return true;
						}
						else {
							sender.sendMessage(ChatColor.AQUA+""+hunter.getName()+ChatColor.RED+" is already a hunter!");
							return true;
						}
					}
					else {
						sender.sendMessage(ChatColor.RED+"Could not find the player");
						return true;
					}
				}
			}
		}


		return false;
	}
}
