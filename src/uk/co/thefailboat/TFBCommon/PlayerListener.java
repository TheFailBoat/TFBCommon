package uk.co.thefailboat.TFBCommon;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	
	JavaPlugin plugin;
	Main instance;

	public PlayerListener(Main _instance, JavaPlugin _plugin) {
		plugin = _plugin;
		instance = _instance;
	}
	
	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent event){
		event.getPlayer().sendMessage(ChatColor.AQUA + instance.motd);
	}

}
