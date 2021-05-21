package me.Blume.BlumesCompass.Listeners;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import me.Blume.BlumesCompass.Main;
import me.Blume.BlumesCompass.Compass.CompassItem;

public class TrackerClick implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public TrackerClick(Main plugin) {
		this.plugin=plugin;
	}
	CompassItem items = new CompassItem();
	Location portalloc=null;
	@EventHandler
	public void trackerClick(PlayerInteractEvent event) {
		if (plugin.gethunt().containsKey(event.getPlayer().getUniqueId())) {
			Player hunter = event.getPlayer();
			ItemStack item = event.getItem();
			Action action = event.getAction();
			if(plugin.gethunt().containsKey(hunter.getUniqueId())) {
				if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
					if (item != null && item.getEnchantmentLevel(Enchantment.DURABILITY)==2001) {
						Player speedRunner = speedRunner(hunter);
						if (speedRunner != null) {    
							CompassMeta meta = (CompassMeta) item.getItemMeta();
							if (meta == null) {
								meta = (CompassMeta) (items.Tracker().getItemMeta());
							}
							meta.setLodestoneTracked(false);
							meta.setLodestone(speedRunner.getLocation());
							item.setItemMeta(meta);

							hunter.setCompassTarget(speedRunner.getLocation());

							hunter.sendMessage(ChatColor.AQUA + "Now tracking " + speedRunner.getName() + ".");
						} else {
							if(portalloc==null) {
								hunter.sendMessage(ChatColor.AQUA+"Could not find a player to track.");	
								return;
							}
							hunter.sendMessage(ChatColor.AQUA + "Tracking the last location of "+Bukkit.getPlayer(plugin.hunt.get(hunter.getUniqueId())).getName());
							CompassMeta meta1 = (CompassMeta) item.getItemMeta();
							if (meta1 == null) {
								meta1 = (CompassMeta) (items.Tracker().getItemMeta());
							}
							meta1.setLodestoneTracked(false);
							meta1.setLodestone(portalloc);
							item.setItemMeta(meta1);
							hunter.setCompassTarget(portalloc);
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void notworld(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		if(plugin.gethunt().containsValue(player.getUniqueId())) {
			portalloc = event.getFrom();
		}
	}
	private Player speedRunner(Player hunter) {
		Player speedrunner = Bukkit.getPlayer(plugin.hunt.get(hunter.getUniqueId()));
		World sr = speedrunner.getWorld();
		if(hunter.getWorld().equals(sr)) {
			return speedrunner;
		}
		return null;
	}
}
