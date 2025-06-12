package co.xenastudios.limbo.commands;

import co.xenastudios.limbo.LimboPlugin;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private final LimboPlugin plugin;

    private final HashSet<Command> pluginCommands;

    public CommandManager(LimboPlugin plugin) {
        this.plugin = plugin;
        this.pluginCommands = new HashSet<>();

        FileConfiguration config = this.plugin.getConfig();
        if (config.getBoolean("commands.join.enabled", false)) this.register("join", "Attempt to join the server.", config.getStringList("commands.join.aliases"), "/join", config.getString("commands.join.permission"), new JoinCommand(this.plugin), null);

        this.plugin.getLogger().info("Commands registered!");
    }

    public HashSet<Command> getPluginCommands() {
        return this.pluginCommands;
    }

    public void register(String commandName, String description, List<String> aliases, String usage, String permission, CommandExecutor executor, TabCompleter tabCompleter) {
        try {
            Constructor<PluginCommand> constructor =
                    PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            Field commandMapField =
                    this.plugin.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(this.plugin.getServer());

            PluginCommand command = constructor.newInstance(commandName, this.plugin);
            command.setDescription(description);
            command.setUsage(usage);
            command.setPermission(permission);
            command.setExecutor(executor);
            command.setTabCompleter(tabCompleter);
            command.setAliases(aliases);

            commandMap.register(this.plugin.getName(), command);
            this.pluginCommands.add(command);
        } catch (Exception exception) {
            this.plugin.getLogger().severe("Failed to register command '" + commandName + "': " + exception.getMessage());
        }
    }
}