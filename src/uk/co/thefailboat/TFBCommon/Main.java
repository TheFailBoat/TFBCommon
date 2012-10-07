package uk.co.thefailboat.TFBCommon;

import java.util.HashMap;

import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.thefailboat.TFBCommon.Objects.SerializableLocation;
import uk.co.thefailboat.TFBCommon.command.*;

public class Main extends JavaPlugin{
	//LISTENERS
    private final PlayerListener playerListener = new PlayerListener(this, this);
    private final EntityListener entityListener = new EntityListener(this, this);
	
    //Hashmaps for warps and locations
    public HashMap<String, SerializableLocation> Homes = new HashMap<String, SerializableLocation>();
    public HashMap<String, SerializableLocation> Warps = new HashMap<String, SerializableLocation>();
    
    public String motd;

	public void onEnable(){
		ConfigurationSerialization.registerClass(uk.co.thefailboat.TFBCommon.Objects.SerializableLocation.class);
		//Register listeners
		getServer().getPluginManager().registerEvents(entityListener, this);
		getServer().getPluginManager().registerEvents(playerListener, this);
		
		//Register command executors
		getCommand("Home").setExecutor(new Home(this));
		getCommand("Sethome").setExecutor(new Home(this));
		getCommand("Warp").setExecutor(new Warp(this));
		getCommand("Setwarp").setExecutor(new Warp(this));
		getCommand("Listwarps").setExecutor(new Warp(this));
		
		for(String key : this.getConfig().getConfigurationSection("teleportation.homes").getKeys(false)){
			Homes.put(key, (SerializableLocation) this.getConfig().get("teleportation.homes."+key));
		}
		for(String key : this.getConfig().getConfigurationSection("teleportation.warps").getKeys(false)){
			Warps.put(key, (SerializableLocation) this.getConfig().get("teleportation.warps."+key));
		}
		
		motd = this.getConfig().getString("motd");
		
		Statics.log.info(Statics.prefix+"Loaded " + Warps.size() + " Warps from config.");
		Statics.log.info(Statics.prefix+"Loaded " + Homes.size() + " Player Homes from config.");
	}
	
	public void SaveConfiguration(){
		this.getConfig().set("teleportation.homes", Homes);
		this.getConfig().set("teleportation.warps", Warps);
		this.getConfig().set("motd", motd);
		this.saveConfig();
	}
	
	public void onDisable(){
		SaveConfiguration();
		Statics.log.info(Statics.prefix+"Plugin version "+Statics.Version+" has been disabled.");
	}
}
