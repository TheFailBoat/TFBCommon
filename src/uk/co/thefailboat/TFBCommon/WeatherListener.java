package uk.co.thefailboat.TFBCommon;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class WeatherListener implements Listener{
	JavaPlugin plugin;
	Main instance;

	public WeatherListener(Main _instance, JavaPlugin _plugin) {
		plugin = _plugin;
		instance = _instance;
	}
	
	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent event){
		if(event.toWeatherState()){
			//Trying to rain
			if(!instance.AllowRain) event.setCancelled(true);
		}
	}
}
