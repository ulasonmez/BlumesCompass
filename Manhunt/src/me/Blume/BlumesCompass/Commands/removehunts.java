package me.Blume.BlumesCompass.Commands;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Blume.BlumesCompass.Main;
import me.Blume.BlumesCompass.Compass.CompassItem;

public class removehunts implements CommandExecutor{

	@SuppressWarnings("unused")
	private Main plugin;
	public removehunts(Main plugin) {
		this.plugin=plugin;
	}
	CompassItem items = new CompassItem();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player hunter = (Player) sender;
			if(plugin.gethunt().containsKey(hunter.getUniqueId())) {
				if(label.equals("removehunt")) {
					UUID speedrunnerid = plugin.hunt.get(hunter.getUniqueId());
					Player speedrunner = Bukkit.getPlayer(speedrunnerid);
					speedrunner.sendMessage(ChatColor.AQUA+hunter.getName()+" stopped hunting you");
					plugin.hunt.remove(hunter.getUniqueId());
					items.removeTracker(hunter.getUniqueId());
				}
			}
		}
		return false;
	}
}
