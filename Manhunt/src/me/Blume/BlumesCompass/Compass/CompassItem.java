package me.Blume.BlumesCompass.Compass;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompassItem {
	public ItemStack tracker;
	public ItemStack Tracker() {
		tracker = new ItemStack(Material.COMPASS);
		ItemMeta meta = tracker.getItemMeta();
		meta.setDisplayName("Tracker");
		meta.addEnchant(Enchantment.DURABILITY, 2001, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		tracker.setItemMeta(meta);
		return tracker;
	}
	public void removeTracker(UUID hunter) {
		ItemStack[] inv = Bukkit.getPlayer(hunter).getInventory().getContents();
		for(ItemStack items : inv){
			if(items!=null) {
				if(items.getEnchantmentLevel(Enchantment.DURABILITY)==2001) {
					Bukkit.getPlayer(hunter).getInventory().remove(items);;
				}
			}
		}
	}
}
