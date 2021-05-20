package me.Blume.Manhunt.Commands;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.Blume.Manhunt.Main;
import me.Blume.Manhunt.Compass.CompassItem;

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
			if(sender.isOp()) {
				if(plugin.gethunt().containsKey(((Player) sender).getUniqueId())) {
					if(label.equals("removehunt")) {
						Player hunter = (Player) sender;
						plugin.hunt.remove(hunter.getUniqueId());
						items.removeTracker(hunter.getUniqueId());
						UUID speedrunnerid = plugin.hunt.get(hunter.getUniqueId());
						Player speedrunner = Bukkit.getPlayer(speedrunnerid);
						speedrunner.sendMessage(ChatColor.AQUA+hunter.getName()+" stopped hunting you");
					}
				}
			}
		}
		return false;
	}
}
