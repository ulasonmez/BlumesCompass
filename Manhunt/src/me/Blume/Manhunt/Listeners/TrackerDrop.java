package me.Blume.Manhunt.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

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
				return;
			}
		}
		else if(event.getItemDrop().getItemStack().isSimilar(CompassItem.compass)) {
			event.getItemDrop().remove();
			return;
		}
	}
	@EventHandler
	public void trackerDeath(PlayerDeathEvent event) {
		if(plugin.getHunter().contains(event.getEntity().getUniqueId())) {
			for(ItemStack drop : event.getDrops()) {
				if(drop.equals(CompassItem.compass)) {
					event.getDrops().remove(event.getDrops().indexOf(drop));
				}
			}
		}
	}
	@EventHandler
	public void trackerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(plugin.getHunter().contains(player.getUniqueId())) {
			player.getInventory().addItem(CompassItem.compass);
			return;
		}
	}
}
