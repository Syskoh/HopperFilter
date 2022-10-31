package de.syskoh.hopperfilter.hopperfilter;

import de.syskoh.hopperfilter.hopperfilter.events.HopperHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class Hopperfilter extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new HopperHandler(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
