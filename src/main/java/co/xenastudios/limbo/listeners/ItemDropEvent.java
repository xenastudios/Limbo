package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDropEvent implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
