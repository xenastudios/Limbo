package co.xenastudios.limbo.listeners;

import org.bukkit.GameRule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WorldLoadEvent implements Listener {
    @EventHandler
    public void onWorldLoad(org.bukkit.event.world.WorldLoadEvent event) {
        event.getWorld().setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
        event.getWorld().setGameRule(GameRule.DISABLE_RAIDS, true);
        event.getWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        event.getWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        event.getWorld().setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
    }
}
