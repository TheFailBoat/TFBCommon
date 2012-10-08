package uk.co.thefailboat.TFBCommon.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.co.thefailboat.TFBCommon.Main;

public class Fun implements CommandExecutor{
	private Main instance;
	
	public Fun(Main _instance){
		this.instance = _instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		if(cmd.getName().toLowerCase().equals("ignite")){
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "You must provide a target!");
				return false;
			}
			String targetName = args[0];
			List<Player >targetList = instance.getServer().matchPlayer(targetName);
			if(targetList.size() == 0){
				player.sendMessage(ChatColor.RED + "That player could not be found.");
				return true;
			}
			Player target = targetList.get(0);
			
			target.setFireTicks(target.getMaxFireTicks());
			
			instance.getServer().broadcastMessage(ChatColor.GOLD + player.getDisplayName() + " ignited " + target.getDisplayName() + "!");
			return true;
		}
		
		if(cmd.getName().toLowerCase().equals("incinerate")){
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "You must provide a target!");
				return false;
			}
			String targetName = args[0];
			List<Player >targetList = instance.getServer().matchPlayer(targetName);
			if(targetList.size() == 0){
				player.sendMessage(ChatColor.RED + "That player could not be found.");
				return true;
			}
			Player target = targetList.get(0);
			
			World targetWorld = target.getWorld();
			
			targetWorld.strikeLightning(target.getLocation());
			target.setHealth(0);
			
			instance.getServer().broadcastMessage(ChatColor.GOLD + player.getDisplayName() + " incinerated " + target.getDisplayName() + "!");
			return true;
		}

		return false;
	}
}
