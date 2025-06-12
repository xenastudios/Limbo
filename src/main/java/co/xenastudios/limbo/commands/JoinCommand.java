package co.xenastudios.limbo.commands;

import co.xenastudios.limbo.LimboPlugin;
import co.xenastudios.limbo.utilities.MsgUtility;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class JoinCommand implements CommandExecutor {
    private final LimboPlugin plugin;

    public JoinCommand(LimboPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MsgUtility.format(sender, this.plugin.getConfig().getString("messages.error.player-only"));
            return true;
        }

		ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(this.plugin.getConfig().getString("main-server", "main"));

        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        MsgUtility.format(player, this.plugin.getConfig().getString("commands.join.messages.attempt-join"));

        return true;
    }
}
