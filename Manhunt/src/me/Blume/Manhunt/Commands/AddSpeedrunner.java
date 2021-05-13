package me.Blume.Manhunt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.Manhunt.Main;
import net.md_5.bungee.api.ChatColor;

public class AddSpeedrunner implements CommandExecutor{

	private Main plugin;
	public AddSpeedrunner(Main plugin) {
		this.plugin=plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("addspeedrunner")) {
			if(sender instanceof Player) {
				if(args.length==0) {
					Player speedrunner = (Player) sender;
					if(!plugin.getSpeedrunner().contains(speedrunner.getUniqueId()) && !plugin.getHunter().contains(speedrunner.getUniqueId())) {
						plugin.addSpeedrunner(speedrunner);
						return true;
					}
					else {
						sender.sendMessage(ChatColor.RED+"You can't add"+ChatColor.AQUA+"yourself"+ ChatColor.RED+" as a speedrunner");
						return true;
					}
				}
				else if(args.length==1) {
					Player speedrunner = Bukkit.getServer().getPlayerExact(args[0]);
					if(speedrunner!=null) {
						if(!plugin.getSpeedrunner().contains(speedrunner.getUniqueId()) && !plugin.getHunter().contains(speedrunner.getUniqueId())) {
							plugin.addSpeedrunner(speedrunner);
							return true;
						}
						else {
							sender.sendMessage(ChatColor.RED+"You can't add"+ChatColor.AQUA+""+speedrunner.getName()+ ChatColor.RED+" as a speedrunner");
							return true;
						}
					}
				}
			}



		}
		return false;
	}
}
