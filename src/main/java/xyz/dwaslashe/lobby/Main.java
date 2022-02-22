package xyz.dwaslashe.lobby;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dwaslashe.lobby.commands.*;
import xyz.dwaslashe.lobby.commands.managers.CommandManager;
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

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player ps : Bukkit.getOnlinePlayers()) {
                    PlayerAfkListener.checkPlayer(ps);
                }
            }
        }, 0, 20 * 60 * 5);
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
    }
    public void loadEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerAfkListener(), this);
        getServer().getPluginManager().registerEvents(new SwordPvPListener(), this);
        getServer().getPluginManager().registerEvents(new OthersListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        getServer().getPluginManager().registerEvents(new MediaCommand(), this);
        InventoryHelper.implement(this);
    }
}

