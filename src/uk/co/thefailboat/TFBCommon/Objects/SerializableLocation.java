package uk.co.thefailboat.TFBCommon.Objects;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class SerializableLocation implements ConfigurationSerializable{
	public double x, y, z;
	public float yaw;
	public float pitch;
	public World world;
	private Location loc;
	
	public SerializableLocation(Location l){
		x = l.getX();
		y = l.getY();
		z = l.getZ();
		pitch = l.getPitch();
		yaw = l.getYaw();
		world = l.getWorld();
		loc = l;
	}
	
	public SerializableLocation(Map<String, Object> map){
		x = new Double(map.get("x").toString());
		y = new Double(map.get("y").toString());
		z = new Double(map.get("z").toString());
		pitch = Float.intBitsToFloat(new Integer(map.get("pitch").toString()));
		yaw = Float.intBitsToFloat(new Integer(map.get("yaw").toString()));
		world = Bukkit.getWorld(map.get("world").toString());
		loc = new Location(world, x, y, z, yaw, pitch);
	}
	
	public Location getLocation(){
		return loc;
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> serializedMap = new HashMap<String, Object>();
		serializedMap.put("world", world.getName());
		serializedMap.put("x", x);
		serializedMap.put("y", y);
		serializedMap.put("z", z);
		serializedMap.put("pitch", Float.floatToIntBits(pitch));
		serializedMap.put("yaw", Float.floatToIntBits(yaw));
		return serializedMap;
	}

}
