package uk.co.thefailboat.TFBCommon;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EntityListener implements Listener {
	
	JavaPlugin plugin;
	Main instance;

	public EntityListener(Main _instance, JavaPlugin _plugin) {
		plugin = _plugin;
		instance = _instance;
	}

}
