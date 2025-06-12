package co.xenastudios.limbo.listeners;

import co.xenastudios.limbo.LimboPlugin;
import org.bukkit.plugin.PluginManager;

import java.util.logging.Level;

public class EventManager {
    public EventManager(LimboPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new BreakEvent(), plugin);
        pluginManager.registerEvents(new DeathEvent(), plugin);
        pluginManager.registerEvents(new ExplosionEvent(), plugin);
        pluginManager.registerEvents(new InteractEvent(), plugin);
        pluginManager.registerEvents(new ItemDropEvent(), plugin);
        pluginManager.registerEvents(new JoinEvent(plugin), plugin);
        pluginManager.registerEvents(new QuitEvent(), plugin);
        pluginManager.registerEvents(new RedstoneEvent(), plugin);
        pluginManager.registerEvents(new SpawnEvent(), plugin);
        pluginManager.registerEvents(new WorldLoadEvent(), plugin);

        plugin.getLogger().log(Level.INFO, "Events registered!");
    }
}
