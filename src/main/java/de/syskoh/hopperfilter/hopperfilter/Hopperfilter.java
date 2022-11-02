package de.syskoh.hopperfilter.hopperfilter;

import de.syskoh.hopperfilter.hopperfilter.events.HopperHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hopperfilter extends JavaPlugin {

    private static Hopperfilter instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new HopperHandler(), this);

    }

    public static Hopperfilter getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
