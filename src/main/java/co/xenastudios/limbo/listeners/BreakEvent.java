package co.xenastudios.limbo.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvent implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setDropItems(false);

        Block block = event.getBlock();
        if (block.getY() == 0) {
            event.setCancelled(true);
        }
    }
}
