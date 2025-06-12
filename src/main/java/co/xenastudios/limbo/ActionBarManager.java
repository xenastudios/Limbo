package co.xenastudios.limbo;

import co.xenastudios.limbo.utilities.MsgUtility;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class ActionBarManager {
    public ActionBarManager(LimboPlugin plugin) {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        scheduler.runTaskTimer(plugin, () -> {
            for (Player player :  plugin.getServer().getOnlinePlayers()) {
                player.sendActionBar(
                        MsgUtility.format(plugin.getConfig().getString("action-bar.message"))
                );
            }
        }, 0, 20L);
    }
}
