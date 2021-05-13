package me.Blume.Manhunt;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import me.Blume.Manhunt.Commands.AddHunters;
import me.Blume.Manhunt.Commands.AddSpeedrunner;
import me.Blume.Manhunt.Commands.RemoveHunters;
import me.Blume.Manhunt.Commands.RemoveSpeedrunner;
import me.Blume.Manhunt.Listeners.TrackerDrop;

public class Main extends JavaPlugin{
	public HashSet<UUID> hunterIds = new HashSet<UUID>();
	public HashSet<UUID> speedrunnerIds = new HashSet<UUID>();
	@Override
	public void onEnable() {
		getCommand("addhunter").setExecutor(new AddHunters(this));
		getCommand("removehunter").setExecutor(new RemoveHunters(this));
		getCommand("addspeedrunner").setExecutor(new AddSpeedrunner(this));
		getCommand("removespeedrunner").setExecutor(new RemoveSpeedrunner(this));
		getServer().getPluginManager().registerEvents(new TrackerDrop(this), this);
	}
	@Override
	public void onDisable() {
		
	}
	public void addHunter(Player player) {
		hunterIds.add(player.getUniqueId());
	}
	public HashSet<UUID> getHunter() {
		return this.hunterIds;
	}
	public void removeHunter(Player player) {
		hunterIds.remove(player.getUniqueId());
	}
	public void addSpeedrunner(Player player) {
		hunterIds.add(player.getUniqueId());
	}
	public HashSet<UUID> getSpeedrunner() {
		return this.hunterIds;
	}
	public void removeSpeedrunner(Player player) {
		hunterIds.remove(player.getUniqueId());
	}
}