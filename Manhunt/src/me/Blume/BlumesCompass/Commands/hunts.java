package me.Blume.BlumesCompass.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.BlumesCompass.Main;
import me.Blume.BlumesCompass.Compass.CompassItem;
public class hunts implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	public hunts(Main plugin) {
		this.plugin=plugin;
	}
	CompassItem items = new CompassItem();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player hunter = (Player) sender;
			if(label.equals("hunt")) {
				if(args.length==0) {
					sender.sendMessage(ChatColor.RED+"You need to choose a player to hunt!");
				}
				else if(args.length==1) {
					Player speedrunner = Bukkit.getPlayer(args[0]);
					if(speedrunner!=null) {
						if(!plugin.gethunt().containsKey(hunter.getUniqueId()) && !plugin.gethunt().containsValue(hunter.getUniqueId())) {
							plugin.addhunt(hunter, speedrunner);
							hunter.getInventory().addItem(items.Tracker());
							speedrunner.sendMessage(ChatColor.AQUA+hunter.getName()+" is hunting you");
						}
						else hunter.sendMessage(ChatColor.RED+"You already have a role");
					}
				}
			}
		}
		return false;
	}
}
