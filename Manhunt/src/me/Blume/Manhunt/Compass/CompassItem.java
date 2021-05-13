package me.Blume.Manhunt.Compass;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassItem {
	public static ItemStack compass = new ItemStack(Material.COMPASS);
		public ItemStack giveCompass() {
			ItemMeta meta = compass.getItemMeta();
			meta.setDisplayName("Tracker");
			compass.setItemMeta(meta);
			return compass;
		}
		public void removeCompass(Player player) {
			if(player.getInventory().contains(CompassItem.compass)) {
				player.getInventory().remove(CompassItem.compass);
			}
		}
}
