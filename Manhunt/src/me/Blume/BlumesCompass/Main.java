package me.Blume.BlumesCompass;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Blume.BlumesCompass.Commands.hunts;
import me.Blume.BlumesCompass.Commands.removehunts;
import me.Blume.BlumesCompass.Listeners.TrackerClick;
import me.Blume.BlumesCompass.Listeners.TrackerDrop;

public class Main extends JavaPlugin{
	public HashMap<UUID,UUID> hunt = new HashMap<UUID,UUID>();
	@Override
	public void onEnable() {
		getCommand("hunt").setExecutor(new hunts(this));
		getCommand("removehunt").setExecutor(new removehunts(this));
		getServer().getPluginManager().registerEvents(new TrackerDrop(this), this);
		getServer().getPluginManager().registerEvents(new TrackerClick(this), this);
	}
	@Override
	public void onDisable() {
		
	}
	public HashMap<UUID,UUID> gethunt(){
		return this.hunt;
	}
	public void addhunt(Player hunter,Player speedrunner) {
		hunt.put(hunter.getUniqueId(), speedrunner.getUniqueId());
	}
	public void removehunt(Player hunter) {
		hunt.remove(hunter.getUniqueId());
	}
}