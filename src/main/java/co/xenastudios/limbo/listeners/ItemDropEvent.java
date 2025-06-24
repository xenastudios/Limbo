package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listener for item drop events.
 * Prevents players from dropping items.
 */
public class ItemDropEvent implements Listener {

	/**
	 * Handles the PlayerDropItemEvent.
	 * Cancels the event to prevent item drops.
	 *
	 * @param event The player drop item event.
	 */
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
}