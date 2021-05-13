package me.Blume.Manhunt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.Manhunt.Main;
import net.md_5.bungee.api.ChatColor;

public class RemoveSpeedrunner implements CommandExecutor{
	@SuppressWarnings("unused")
	private Main plugin;
	public RemoveSpeedrunner(Main plugin) {
		this.plugin=plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("removespeedrunner")) {
			if(sender instanceof Player) {
				if(args.length==0) {
					Player speedrunner = (Player) sender;
					if(plugin.getSpeedrunner().contains(speedrunner.getUniqueId())) {
						plugin.removeSpeedrunner(speedrunner);
					}
					else {
						sender.sendMessage(ChatColor.AQUA+"You"+ChatColor.RED+" are not a Speed runner");
						return true;
					}
				}
				else if(args.length==1) {
					Player Speedrunner = Bukkit.getServer().getPlayerExact(args[0]);
					if(Speedrunner!=null) {
						if(plugin.getSpeedrunner().contains(Speedrunner.getUniqueId())) {
							plugin.removeSpeedrunner(Speedrunner);
							return true;
						}
						else {
							sender.sendMessage(ChatColor.AQUA+Speedrunner.getName()+ChatColor.RED+" is not a Speed runner");
							return true;
						}
					}
					else {
						sender.sendMessage("Could not find the player");
						return true;
					}
				}
			}
		}



		return false;
	}

}
