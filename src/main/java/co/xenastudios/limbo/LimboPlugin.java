package co.xenastudios.limbo;

import co.xenastudios.limbo.listeners.EventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * Main plugin class for Limbo.
 * Handles plugin lifecycle, configuration, and core managers.
 */
public class LimboPlugin extends JavaPlugin {

	// Managers for handling events and action bar messages
	private EventManager eventManager;
	private ActionBarManager actionBarManager;

	/**
	 * Called when the plugin is enabled.
	 * Handles configuration, world deletion, command registration, and manager initialization.
	 */
	@Override
	public void onEnable() {
		// Save default config if not present
		this.saveDefaultConfig();

		// Optionally delete the world folder if enabled in config
		if (this.getConfig().getBoolean("delete-world.enabled", false)) {
			String worldName = this.getConfig().getString("world-name", "world");
			File worldFile = new File("./" + worldName);
			try {
				FileUtils.deleteDirectory(worldFile);
				this.getLogger().info("Deleted world directory: " + worldFile.getPath());
			} catch (IOException e) {
				this.getLogger().severe("Failed to delete world directory: " + e.getMessage());
				throw new RuntimeException("Could not delete world directory", e);
			}
		}

		// Register outgoing plugin channel for BungeeCord communication
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		// Register join command if enabled in config
		this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
			if (this.getConfig().getBoolean("commands.join.enabled", false)) {
				commands.registrar().register(
						JoinCommand.createCommand(this),
						"Attempt to join the server.",
						this.getConfig().getStringList("commands.join.aliases")
				);
			}
		});

		// Initialize event manager
		this.eventManager = new EventManager(this);

		// Initialize action bar manager if enabled
		if (this.getConfig().getBoolean("action-bar.enabled", false)) {
			this.actionBarManager = new ActionBarManager(this);
		}

		this.getLogger().info("LimboPlugin enabled!");
	}

	/**
	 * Called when the plugin is disabled.
	 * Handles cleanup and resource release.
	 */
	@Override
	public void onDisable() {
		// Unregister outgoing plugin channel
		this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);

		// Cancel the action bar task if it was started
		if (this.actionBarManager != null) {
			this.actionBarManager.cancel();
		}

		this.getLogger().info("LimboPlugin disabled!");
	}

	/**
	 * Provides the default world generator for custom world generation.
	 *
	 * @param worldName The name of the world.
	 * @param id        The generator ID (unused).
	 * @return The custom ChunkGenerator.
	 */
	@Override
	public @NotNull ChunkGenerator getDefaultWorldGenerator(
			@NotNull String worldName,
			String id
	) {
		return new WorldGenerator(this);
	}

	/**
	 * Gets the event manager instance.
	 *
	 * @return The EventManager.
	 */
	public EventManager getEventManager() {
		return this.eventManager;
	}

	/**
	 * Gets the action bar manager instance.
	 *
	 * @return The ActionBarManager, or null if not enabled.
	 */
	public ActionBarManager getActionBarManager() {
		return this.actionBarManager;
	}
}