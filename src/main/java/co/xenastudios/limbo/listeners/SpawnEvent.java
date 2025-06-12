package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnEvent implements Listener {
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}
}
