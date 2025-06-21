package co.xenastudios.limbo;

import co.xenastudios.limbo.listeners.EventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class LimboPlugin extends JavaPlugin {
	private EventManager eventManager;
	private ActionBarManager actionBarManager;

	@Override
	public void onEnable() {
		this.saveDefaultConfig();

		if (this.getConfig().getBoolean("delete-world.enabled", false)) {
			File worldFile = new File("./" + this.getConfig().getString("world-name", "world"));
			try {
				FileUtils.deleteDirectory(worldFile);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
			if (this.getConfig().getBoolean("commands.join.enabled")) {
				commands.registrar().register(JoinCommand.createCommand(this), "Attempt to join the server.", this.getConfig().getStringList("commands.join.aliases"));
			}
		});

		this.eventManager = new EventManager(this);

		if (this.getConfig().getBoolean("action-bar.enabled", false)) {
			this.actionBarManager = new ActionBarManager(this);
		}

		this.getLogger().info("Plugin enabled!");
	}

	@Override
	public void onDisable() {
		this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);

		this.getLogger().info("Plugin disabled!");
	}

	@Override
	public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
		return new WorldGenerator(this);
	}

	public EventManager getEventManager() {
		return this.eventManager;
	}

	public ActionBarManager getActionBarManager() {
		return this.actionBarManager;
	}
}
