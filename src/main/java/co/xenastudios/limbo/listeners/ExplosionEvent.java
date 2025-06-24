package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

/**
 * Listener for explosion events.
 * Cancels all block and entity explosions in the world.
 */
public class ExplosionEvent implements Listener {

	/**
	 * Handles entity explosion events (e.g., creepers, TNT).
	 * Cancels the explosion.
	 *
	 * @param event The entity explosion event.
	 */
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		event.setCancelled(true);
	}

	/**
	 * Handles block explosion events (e.g., beds in the Nether).
	 * Cancels the explosion.
	 *
	 * @param event The block explosion event.
	 */
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent event) {
		event.setCancelled(true);
	}
}