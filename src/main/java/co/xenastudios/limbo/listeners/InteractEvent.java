package co.xenastudios.limbo.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

/**
 * Listener for player interaction events.
 * Prevents players from placing Eyes of Ender into End Portal Frames.
 */
public class InteractEvent implements Listener {

	/**
	 * Handles player right-click interactions.
	 * Cancels the event if a player tries to use an Eye of Ender on an End Portal Frame.
	 *
	 * @param event The player interact event.
	 */
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		// Only process main hand right-clicks on blocks
		if (event.getHand() == EquipmentSlot.HAND &&
				event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			ItemStack item = event.getItem();

			// Check if the item is an Eye of Ender and the clicked block is an End Portal Frame
			if (item != null && item.getType() == Material.ENDER_EYE) {
				if (event.getClickedBlock() != null &&
						event.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {
					event.setCancelled(true);
				}
			}
		}
	}
}