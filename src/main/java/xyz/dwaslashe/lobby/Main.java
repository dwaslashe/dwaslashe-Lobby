package xyz.dwaslashe.lobby;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dwaslashe.lobby.commands.*;
import xyz.dwaslashe.lobby.commands.managers.CommandManager;
import xyz.dwaslashe.lobby.commands.servers.BoxPvPCommand;
import xyz.dwaslashe.lobby.commands.servers.Practice2Command;
import xyz.dwaslashe.lobby.commands.servers.PracticeCommand;
import xyz.dwaslashe.lobby.commands.servers.SurvivalCommand;
import xyz.dwaslashe.lobby.configs.PluginConfig;
import xyz.dwaslashe.lobby.helpers.InventoryHelper;
import xyz.dwaslashe.lobby.listeners.*;
import xyz.dwaslashe.lobby.utils.LicenseApi;

import java.io.File;

public class Main extends JavaPlugin {

    public static PluginConfig pluginConfig;

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    public Main() {
        plugin = this;
    }

    @Override
    public void onDisable() {
        //It's nothing to add
    }

    @Override
    public void onEnable() {

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        pluginConfig = ConfigManager.create(PluginConfig.class, it -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.saveDefaults();
            it.load(true);
        });


        pluginConfig.load();

        //if (!new LicenseApi(pluginConfig.getCore().getLicense(), "https://buybrain.pl/license/verify.php", this).register()) return;

        loadCommands();
        loadEvents();
    }

    public void loadCommands() {
        CommandManager.register(new BcCommand(), true);
        CommandManager.register(new FlyCommand(), true);
        CommandManager.register(new CoreCommand(), true);
        CommandManager.register(new DiscordCommand(), true);
        CommandManager.register(new GmCommand(), true);
        CommandManager.register(new ListCommand(), true);
        CommandManager.register(new MediaCommand(), true);
        CommandManager.register(new MsgCommand(), true);
        CommandManager.register(new ReplyCommand(), true);
        CommandManager.register(new WebsiteCommand(), true);
        CommandManager.register(new SidebarCommand(), true);
        CommandManager.register(new ChatCommand(), true);
        CommandManager.register(new PingCommand(), true);
        CommandManager.register(new UpTimeCommand(), true);
        CommandManager.register(new DayCommand(), true);
        CommandManager.register(new NightCommand(), true);
        CommandManager.register(new SunCommand(), true);
        CommandManager.register(new StormCommand(), true);
        CommandManager.register(new InvseeComand(), true);

        CommandManager.register(new BoxPvPCommand(), true);
        CommandManager.register(new SurvivalCommand(), true);
        CommandManager.register(new Practice2Command(), true);
        CommandManager.register(new PracticeCommand(), true);
    }
    public void loadEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new SwordPvPListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerHubListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleportBowListener(), this);
        getServer().getPluginManager().registerEvents(new OthersListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new MediaCommand(), this);
        getServer().getPluginManager().registerEvents(new LaunchPadListener(), this);
        InventoryHelper.implement(this);
    }
}

