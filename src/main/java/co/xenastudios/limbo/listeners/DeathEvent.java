package co.xenastudios.limbo.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Listener for player death events.
 * Suppresses the death message by setting it to an empty component.
 */
public class DeathEvent implements Listener {

	/**
	 * Handles the PlayerDeathEvent.
	 * Suppresses the death message for all player deaths.
	 *
	 * @param event The player death event.
	 */
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.deathMessage(Component.empty());
	}
}