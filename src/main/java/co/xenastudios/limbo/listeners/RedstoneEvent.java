package co.xenastudios.limbo.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class RedstoneEvent implements Listener {
    @EventHandler
    public void onBlockBreak(BlockRedstoneEvent event) {
        event.setNewCurrent(0);
    }
}
