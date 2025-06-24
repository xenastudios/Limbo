package co.xenastudios.limbo.listeners;

import co.xenastudios.limbo.LimboPlugin;
import co.xenastudios.limbo.utilities.MsgUtility;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * Listener for player join events.
 * Handles random teleportation, auto-join, and join messages.
 */
public class JoinEvent implements Listener {

	private final LimboPlugin plugin;

	public JoinEvent(LimboPlugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Handles the PlayerJoinEvent.
	 * Teleports the player to a random location, optionally auto-connects to the main server,
	 * and manages join messages and chat clearing.
	 *
	 * @param event The player join event.
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		// Get the world from config, defaulting to "world"
		String worldName = plugin.getConfig().getString("world-name", "world");
		World world = Bukkit.getWorld(worldName);

		if (world == null) {
			plugin.getLogger().warning("World '" + worldName + "' not found! Player will not be teleported.");
		} else {
			// Generate a random X and Z within the allowed range
			int min = -9999999;
			int max = 9999999;
			int x = min + (int) (Math.random() * (max - min + 1));
			int z = min + (int) (Math.random() * (max - min + 1));

			Location randomLocation = new Location(world, x, 1, z);
			player.teleport(randomLocation);
		}

		// Auto-join main server if enabled
		if (plugin.getConfig().getBoolean("auto-join.enabled")) {
			long interval = plugin.getConfig().getLong("auto-join.interval", 300L);
			BukkitScheduler scheduler = plugin.getServer().getScheduler();

			// Schedule a one-time task to auto-connect the player
			scheduler.runTaskLater(plugin, () -> {
				ByteArrayDataOutput out = ByteStreams.newDataOutput();
				out.writeUTF("Connect");
				out.writeUTF(plugin.getConfig().getString("main-server", "main"));

				player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
				MsgUtility.send(player, plugin.getConfig().getString("auto-join.messages.attempt-join"));
			}, 20L * interval);
		}

		// Suppress the default join message
		event.joinMessage(Component.empty());

		// Custom join message and optional chat clear
		if (plugin.getConfig().getBoolean("join-message.enabled")) {
			if (plugin.getConfig().getBoolean("join-message.clear-chat")) {
				for (int i = 0; i <= 100; i++) {
					player.sendMessage(Component.space());
				}
			}
			MsgUtility.send(player, plugin.getConfig().getString("join-message.message"));
		}
	}
}