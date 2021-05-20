package me.Blume.Manhunt.Listeners;


import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
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
	CompassItem items = new CompassItem();
	Location portalloc;
	@EventHandler
	public void trackerClick(PlayerInteractEvent event) {
		if (plugin.gethunt().containsKey(event.getPlayer().getUniqueId())) {
			Player hunter = event.getPlayer();
			ItemStack item = event.getItem();
			Action action = event.getAction();
			if(plugin.gethunt().containsKey(hunter.getUniqueId())) {
				if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
					if (item != null && item.getEnchantmentLevel(Enchantment.DURABILITY)==2001) {
						Player closestVictim = getClosestVictim(hunter);
						if (closestVictim != null) {    
							CompassMeta meta = (CompassMeta) item.getItemMeta();
							if (meta == null) {
								meta = (CompassMeta) (items.Tracker().getItemMeta());
							}
							meta.setLodestoneTracked(false);
							meta.setLodestone(closestVictim.getLocation());
							item.setItemMeta(meta);

							hunter.setCompassTarget(closestVictim.getLocation());

							hunter.sendMessage(ChatColor.AQUA + "Now tracking " + closestVictim.getName() + ".");
						} else {
							hunter.sendMessage(ChatColor.AQUA + "Tracking last location ");
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

	private Player getClosestVictim(Player hunter) {
		Location hunterLocation = hunter.getLocation();
		Player closestPlayer = null;
		double closestDistanceSquared = Double.MAX_VALUE;

		List<Player> candidates = hunter.getWorld().getPlayers();
		for (Player p : candidates) {
			if (plugin.gethunt().containsValue(p.getUniqueId())) {
				double distanceSquared = p.getLocation().distanceSquared(hunterLocation);
				if (distanceSquared <= closestDistanceSquared) {
					closestDistanceSquared = distanceSquared;
					closestPlayer = p;
				}
			}
		}
		return closestPlayer;
	}
}
