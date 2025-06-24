package co.xenastudios.limbo.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Listener for block break events.
 * Prevents breaking blocks at Y=0 and disables item drops for all broken blocks.
 */
public class BreakEvent implements Listener {

	/**
	 * Handles the BlockBreakEvent.
	 * Cancels breaking blocks at Y=0 and disables item drops for all blocks.
	 *
	 * @param event The block break event.
	 */
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		// Disable item drops for all broken blocks
		event.setDropItems(false);

		Block block = event.getBlock();

		// Prevent breaking blocks at Y=0
		if (block.getY() == 0) {
			event.setCancelled(true);
		}
	}
}