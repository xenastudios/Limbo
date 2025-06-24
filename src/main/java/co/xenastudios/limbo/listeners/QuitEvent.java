package co.xenastudios.limbo.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener for player quit events.
 * Suppresses the quit message by setting it to an empty component.
 */
public class QuitEvent implements Listener {

	/**
	 * Handles the PlayerQuitEvent.
	 * Suppresses the quit message for all player quits.
	 *
	 * @param event The player quit event.
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		event.quitMessage(Component.empty());
	}
}