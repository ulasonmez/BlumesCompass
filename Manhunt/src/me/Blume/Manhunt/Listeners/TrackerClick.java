package me.Blume.Manhunt.Listeners;


import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import me.Blume.Manhunt.Main;
import me.Blume.Manhunt.Compass.CompassItem;
public class TrackerClick implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public TrackerClick(Main plugin) {
		this.plugin=plugin;
	}
	@EventHandler
	public void trackerClick(PlayerInteractEvent event) {
		Player hunter = event.getPlayer();


		if(plugin.getHunter().contains(hunter.getUniqueId())) {
			ItemStack item = event.getItem();
			if(item.isSimilar(CompassItem.compass)) {
				UUID SpeedrunnerID = getSpeedrunner();
				Player Speedrunner = Bukkit.getPlayer(SpeedrunnerID);


				if(Speedrunner!=null) {
					if(sameWorld(hunter)) {
						hunter.setCompassTarget(Speedrunner.getLocation());
						return;
					}
					else {
						hunter.sendMessage("SpeedRunner is not in your world");
						return;
					}

				}
				else {
					hunter.sendMessage("Could not find a player to track.");
					return;
				}
			}

		}
	}

	public UUID getSpeedrunner() {
		HashSet<UUID> Speedrunners = plugin.getSpeedrunner();
		for(UUID p : Speedrunners) {
			return p;
		}
		return null;
	}
	public boolean sameWorld(Player hunter) {
		HashSet<UUID> Speedrunners = plugin.getSpeedrunner();
		for(UUID p : Speedrunners) {
			if(hunter.getWorld().getEnvironment()==Bukkit.getPlayer(p).getWorld().getEnvironment()) {
				return true;
			}
		}
		return false;
	}
}
