package co.xenastudios.limbo.utilities;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MsgUtility {
	public static Component format(String message) {
		return MiniMessage.miniMessage().deserialize(message);
	}

	public static Component format(String message, TagResolver... placeholders) {
		return MiniMessage.miniMessage().deserialize(message, placeholders);
	}

	public static void send(CommandSender sender, String message) {
		sender.sendMessage(format(message));
	}

	public static void send(CommandSender sender, String message, TagResolver... placeholders) {
		sender.sendMessage(format(message, placeholders));
	}

	public static void broadcast(Server server, String message) {
		server.broadcast(format(message));
	}

	public static void broadcast(Server server, String message, TagResolver... placeholders) {
		server.broadcast(format(message, placeholders));
	}

	public static void format(@NotNull CommandSender sender, @Nullable String string) {
	}
}