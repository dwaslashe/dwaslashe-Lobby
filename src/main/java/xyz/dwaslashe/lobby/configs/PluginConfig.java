package xyz.dwaslashe.lobby.configs;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
@Header("#")
@Header("#By dwaslashe")
@Header("#Contact discord dwaslashe v2#5620")
@Header("#")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfig extends OkaeriConfig {

    private Messages messages = new Messages();
    private Cooldown cooldown = new Cooldown();
    private Chat chat = new Chat();
    private Join join = new Join();
    private Auto auto = new Auto();
    private Core core = new Core();

    //Core
    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Core extends OkaeriConfig {

        @Comment("Your license:")
        private String license = "CPDI-CL22-PY79-US5D";
    
    }

    //Messages
    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Messages extends OkaeriConfig {

        @Comment("Messages")
        private String ip = " &f&lHUB";
        private String prefix = " &8>> &7";
        private String discord = "dc.wywrotkamc.pl";
        private String website = "www.wywrotkamc.pl";
        private String server = "LOBBY";
    }

    //Auto Tasks

    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Auto extends OkaeriConfig {

        private BossBarAuto bossbar = new BossBarAuto();

        @Getter @Setter
        public static class BossBarAuto extends OkaeriConfig {

            private int time = 1;
            private List<String> messages = Arrays.asList("Zapraszająć znajomych na serwer wspierasz nas! &c❤ color:green");

        }

        private MessagesAuto messages = new MessagesAuto();

        @Getter @Setter
        public static class MessagesAuto extends OkaeriConfig {

            private int time = 1;
            private List<String> messages = Arrays.asList("&a✉ &8>> &7Sprawdź nasz discord &bhttps://discord.gg/6eacnBS");

        }
    }

    //Join
    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Join extends OkaeriConfig {

        @Comment("Join message, permission core.join.vip")
        private String message = "";

    }

    //Chat
    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Chat extends OkaeriConfig {

        @Comment("Chat")
        private String format = "{PREFIX}&7{PLAYER} &8>>{SUFFIX} {MESSAGE}";
        private String on = "&a&lCZAT ZOSTAŁ WŁĄCZONY";
        private String off = "&c&LCZAT ZOSTAŁ WYŁĄCZONY";
        private String clear = "&b&lCZAT ZOSTAŁ WYCZYSZCZONY";

        private List<String> blocktabcommands = Arrays.asList("/br",
                "/brush",
                "/desel",
                "/deselect",
                "/sel",
                "/toggleplace",
                ";",
                "?",
                "alonsotags",
                "artp",
                "aspawner",
                "axerrnicknamer",
                "beastwithdraw",
                "xpbottle",
                "bwithdraw",
                "bellyflop",
                "bpl",
                "bplugins",
                "br",
                "brush",
                "bserverutils",
                "bsu",
                "cc",
                "ccreate",
                "cmil",
                "cmilib",
                "command",
                "insaneshops",
                "core",
                "crate",
                "crates",
                "crazycrate",
                "crazycrates",
                "gbellyflop",
                "gcrawl",
                "glay",
                "gsit",
                "gspin",
                "hd",
                "hdv",
                "holo",
                "hologram",
                "holograms",
                "holographicdisplays",
                "icanhasbukkit",
                "jday",
                "key",
                "keys",
                "lbans",
                "listwarn",
                "listwarnings",
                "litebans",
                "lbans",
                "warninglist",
                "warnlist",
                "lwarning",
                "mineeconomy",
                "minemarriages",
                "minephysics",
                "mineplots",
                "minerandomtp",
                "none",
                "papi",
                "placeholderapi",
                "playerkits",
                "reg",
                "regions",
                "region",
                "rg",
                "tab:tab",
                "tab",
                "shopgui",
                "shopguiplus",
                "viaver",
                "viaversion",
                "vulcan",
                "vvbukkit",
                "worldedit",
                "zauction",
                "about");

        private BlockWordsChat blockwords = new BlockWordsChat();

        @Getter @Setter
        public static class BlockWordsChat extends OkaeriConfig {

            private List<String> words = Arrays.asList("kutas", "kurwa", "chuj");
            private String command = "mute {PLAYER} 15m Słowa";

        }

    }

    //Cooldown
    @Getter @Setter
    @Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
    public static class Cooldown extends OkaeriConfig {

        @Comment("Cooldown")
        private String message = " &8>> &cNastepna wiadomosc mozesz wyslac za &e{TIME}";
        private String time = "3s";

    }
}
