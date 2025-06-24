package co.xenastudios.limbo;

import co.xenastudios.limbo.utilities.MsgUtility;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

/**
 * Manages the scheduling of a repeating task that sends an action bar message
 * to all online players.
 */
public class ActionBarManager {

	private final BukkitTask task;

	/**
	 * Starts the repeating action bar task.
	 *
	 * @param plugin The main plugin instance.
	 */
	public ActionBarManager(LimboPlugin plugin) {
		this.task = plugin.getServer().getScheduler().runTaskTimer(
				plugin,
				new ActionBarRunnable(plugin),
				0L,
				20L
		);
	}

	/**
	 * Cancels the repeating action bar task.
	 */
	public void cancel() {
		if (task != null && !task.isCancelled()) {
			task.cancel();
		}
	}

	/**
	 * Runnable that sends the configured action bar message to all online players.
	 */
	private static class ActionBarRunnable implements Runnable {
		private final LimboPlugin plugin;

		public ActionBarRunnable(LimboPlugin plugin) {
			this.plugin = plugin;
		}

		@Override
		public void run() {
			String message = plugin.getConfig().getString("action-bar.message");
			for (Player player : plugin.getServer().getOnlinePlayers()) {
				player.sendActionBar(MsgUtility.format(message));
			}
		}
	}
}