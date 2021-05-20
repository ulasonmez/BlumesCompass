package me.Blume.Manhunt.Listeners;

import java.util.ArrayList;
import org.bukkit.enchantments.Enchantment;
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
		if(plugin.gethunt().containsKey(event.getPlayer().getUniqueId())) {
			if(event.getItemDrop().getItemStack().isSimilar(items.tracker) || event.getItemDrop().getItemStack().getEnchantments().containsValue(2001)) {
				event.setCancelled(true);
				return;
			}
		}
	}
	@EventHandler
	public void trackerDeath(PlayerDeathEvent event) {
		if(plugin.gethunt().containsKey(event.getEntity().getUniqueId())) {
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>(event.getDrops());
			for(ItemStack drop : drops) {
				if(drop!=null) {
					if(drop.getEnchantmentLevel(Enchantment.DURABILITY)==2001) {
						event.getDrops().remove(event.getDrops().indexOf(drop));
					}
				}
			}
		}
	}
	@EventHandler
	public void trackerRespawn(PlayerRespawnEvent event) {
		if(plugin.gethunt().containsKey(event.getPlayer().getUniqueId())) {
			event.getPlayer().getInventory().addItem(items.Tracker());
			return;
		}
		else return;
	}
}
