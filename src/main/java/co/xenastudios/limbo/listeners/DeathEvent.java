package co.xenastudios.limbo.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerDeathEvent event) {
        event.deathMessage(Component.empty());
    }
}
