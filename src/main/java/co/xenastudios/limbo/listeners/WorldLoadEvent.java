package co.xenastudios.limbo.listeners;

import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Listener for world load events.
 * Sets specific game rules when a world is loaded.
 */
public class WorldLoadEvent implements Listener {

	/**
	 * Handles the WorldLoadEvent.
	 * Sets various game rules for the loaded world.
	 *
	 * @param event The world load event.
	 */
	@EventHandler
	public void onWorldLoad(org.bukkit.event.world.WorldLoadEvent event) {
		event.getWorld().setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
		event.getWorld().setGameRule(GameRule.DISABLE_RAIDS, true);
		event.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		event.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		event.getWorld().setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
	}
}