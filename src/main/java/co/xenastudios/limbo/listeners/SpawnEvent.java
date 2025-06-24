package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Listener for creature spawn events.
 * Cancels all creature spawns in the world.
 */
public class SpawnEvent implements Listener {

	/**
	 * Handles the CreatureSpawnEvent.
	 * Cancels the event to prevent any creature from spawning.
	 *
	 * @param event The creature spawn event.
	 */
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		event.setCancelled(true);
	}
}