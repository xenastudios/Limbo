package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * Listener for redstone events.
 * Disables all redstone activity by setting the new current to 0.
 */
public class RedstoneEvent implements Listener {

	/**
	 * Handles the BlockRedstoneEvent.
	 * Sets the new redstone current to 0, effectively disabling redstone.
	 *
	 * @param event The block redstone event.
	 */
	@EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event) {
		event.setNewCurrent(0);
	}
}