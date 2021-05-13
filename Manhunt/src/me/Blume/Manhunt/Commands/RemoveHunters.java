package me.Blume.Manhunt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.Manhunt.Main;
import me.Blume.Manhunt.Compass.CompassItem;
import net.md_5.bungee.api.ChatColor;

public class RemoveHunters implements CommandExecutor{
	CompassItem items = new CompassItem();
	@SuppressWarnings("unused")
	private Main plugin;
	public RemoveHunters(Main plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			if(args.length==0) {
				Player hunter = (Player) sender;
				if(plugin.getHunter().contains(hunter.getUniqueId())) {
					items.removeCompass(hunter);
					plugin.removeHunter(hunter);
					return true;
				}
				else {
					sender.sendMessage(ChatColor.AQUA+"You"+ChatColor.RED+" are not a hunter");
					return true;
				}
			}
			else if(args.length==1) {
				Player hunter = Bukkit.getServer().getPlayerExact(args[0]);
				if(hunter!=null) {
					if(plugin.getHunter().contains(hunter.getUniqueId())) {
						items.removeCompass(hunter);
						plugin.removeHunter(hunter);
						return true;
					}
					else {
						sender.sendMessage(ChatColor.AQUA+""+hunter.getName()+ChatColor.RED+" is not a hunter");
						return true;
					}
				}
				else {
					sender.sendMessage(ChatColor.RED+"Could not find the player");
					return true;
				}
			}
		}




		return false;
	}

}
