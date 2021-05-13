package me.Blume.Manhunt.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.Blume.Manhunt.Main;

public class TrackerClick implements Listener {
	@SuppressWarnings("unused")
	private Main plugin;
	public TrackerClick(Main plugin) {
		this.plugin=plugin;
	}
	@EventHandler
	public void trackerClick(PlayerInteractEvent event) {
		if(plugin.getHunter().contains(event.getPlayer().getUniqueId())) {

			
		}


	}


}
