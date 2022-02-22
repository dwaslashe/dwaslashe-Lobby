package xyz.dwaslashe.lobby.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import xyz.dwaslashe.lobby.utils.Api;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerAfkListener implements Listener {
    public PlayerAfkListener() {
    }

    public static Map<UUID, Double> LocYaw = new HashMap<UUID, Double>();

    public static void checkPlayer(Player p) {
        UUID uuid = p.getUniqueId();

        if (LocYaw.get(uuid) != null) {
            if ((double) p.getLocation().getYaw() == LocYaw.get(uuid)) {
                if (p.isOp()) {
                    return;
                } else
                    p.kickPlayer(Api.fixColor("&#F23D07&lANTY-AFK \n \n &fZostałeś wyrzucony za \nnie ruszanie się przez &e5 minut!"));
            } else {
                LocYaw.put(uuid, (double) p.getLocation().getYaw());
            }
        } else {
            LocYaw.put(uuid, (double) p.getLocation().getYaw());
        }
    }
}
