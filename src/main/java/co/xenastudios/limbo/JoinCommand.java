package co.xenastudios.limbo;

import co.xenastudios.limbo.utilities.MsgUtility;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handles the /join command for connecting players to the main server via BungeeCord.
 */
public class JoinCommand {

	/**
	 * Creates the /join command node for registration.
	 *
	 * @param plugin The main plugin instance.
	 * @return The constructed command node for registration.
	 */
	public static LiteralCommandNode<CommandSourceStack> createCommand(
			final LimboPlugin plugin
	) {
		// Build the base /join command
		LiteralArgumentBuilder<CommandSourceStack> joinCommand =
				Commands.literal("join");

		// Retrieve the required permission from the config
		String permission =
				plugin.getConfig().getString("commands.join.permission");

		// Restrict command usage to those with the permission, if set
		if (permission != null && !permission.isEmpty()) {
			joinCommand.requires(
					sender -> sender.getSender().hasPermission(permission)
			);
		}

		// Main /join command execution: connect the player to the main server
		joinCommand.executes(ctx -> {
			CommandSender sender = ctx.getSource().getSender();

			// Only players can use this command
			if (!(sender instanceof Player player)) {
				MsgUtility.send(
						sender,
						plugin.getConfig().getString(
								"messages.error.player-only",
								"This command can only be used by players."
						)
				);
				return Command.SINGLE_SUCCESS;
			}

			// Prepare the BungeeCord plugin message to connect the player
			String targetServer =
					plugin.getConfig().getString("main-server", "main");
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF(targetServer);

			player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());

			// Send feedback to the player
			MsgUtility.send(
					player,
					plugin.getConfig().getString(
							"commands.join.messages.attempt-join",
							"Attempting to connect you to the main server..."
					)
			);
			return Command.SINGLE_SUCCESS;
		});

		// Build and return the complete command node
		return joinCommand.build();
	}
}