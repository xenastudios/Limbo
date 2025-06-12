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

public class JoinEvent implements Listener {
    private final LimboPlugin plugin;

    public JoinEvent(LimboPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        World world = Bukkit.getWorld(plugin.getConfig().getString("world-name", "world"));
        int x = -9999999 + (int)(Math.random() * ((9999999 + 9999999) + 1));
        int y = -9999999 + (int)(Math.random() * ((9999999 + 9999999) + 1));

        Location randomLocation = new Location(world, x, 1, y);
        player.teleport(randomLocation);

        if (plugin.getConfig().getBoolean("auto-join.enabled")) {
            BukkitScheduler scheduler = plugin.getServer().getScheduler();
            scheduler.runTaskTimer(plugin, () -> {
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Connect");
                out.writeUTF(plugin.getConfig().getString("main-server", "main"));

                player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
                MsgUtility.send(player, plugin.getConfig().getString("auto-join.messages.attempt-join"));
            }, 20L * plugin.getConfig().getLong("auto-join-interval", 300L), 20L * plugin.getConfig().getLong("auto-join-interval", 300L));
        }

        event.joinMessage(Component.empty());

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
