package co.xenastudios.limbo;

import co.xenastudios.limbo.utilities.MsgUtility;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JoinCommand {
	public static LiteralCommandNode<CommandSourceStack> createCommand(LimboPlugin plugin) {
		return Commands.literal("join")
				.requires(sender -> sender.getSender().hasPermission(plugin.getConfig().getString("commands.join.permission")))
				.executes(ctx -> {
					CommandSender sender = ctx.getSource().getSender();
					if (!(sender instanceof Player player)) {
						MsgUtility.format(sender, plugin.getConfig().getString("messages.error.player-only"));
						return Command.SINGLE_SUCCESS;
					}

					ByteArrayDataOutput out = ByteStreams.newDataOutput();
					out.writeUTF("Connect");
					out.writeUTF(plugin.getConfig().getString("main-server", "main"));

					player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
					MsgUtility.format(player, plugin.getConfig().getString("commands.join.messages.attempt-join"));
					return Command.SINGLE_SUCCESS;
				})
				.build();
	}
}