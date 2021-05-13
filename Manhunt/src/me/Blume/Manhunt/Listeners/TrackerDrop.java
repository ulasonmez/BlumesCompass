package me.Blume.Manhunt.Listeners;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import me.Blume.Manhunt.Main;
import me.Blume.Manhunt.Compass.CompassItem;

public class TrackerDrop implements Listener{
	CompassItem items = new CompassItem();
	private Main plugin;
	public TrackerDrop(Main plugin) {
		this.plugin=plugin;
	}
	@EventHandler
	public void trackerDrops(PlayerDropItemEvent event) {
		if(plugin.getHunter().contains(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(CompassItem.compass)) {
				event.setCancelled(true);
			}
		}
	}
}
