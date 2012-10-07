package uk.co.thefailboat.TFBCommon.command;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.thefailboat.TFBCommon.Main;

public class Teleport implements CommandExecutor{
	
	private Main instance;
	
	public Teleport(Main _instance){
		this.instance = _instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		if(cmd.getName().toLowerCase().equals("tp")){

			if(args.length < 1){
				player.sendMessage(ChatColor.RED + "You must specify a player to teleport to!");
				return false;
			}
			String targetName = args[0];
			List<Player >targetList = instance.getServer().matchPlayer(targetName);
			if(targetList.size() == 0){
				player.sendMessage(ChatColor.RED + "That player could not be found.");
				return true;
			}
			Player target = targetList.get(0);
			player.teleport(target.getLocation());
			player.sendMessage(ChatColor.AQUA + "Teleported to " + target.getDisplayName() + ".");
			return true;
		}
		else if(cmd.getName().toLowerCase().equals("tphere")){

			if(args.length < 1){
				player.sendMessage(ChatColor.RED + "You must specify a player to teleport to you!");
				return false;
			}
			String targetName = args[0];
			List<Player >targetList = instance.getServer().matchPlayer(targetName);
			if(targetList.size() == 0){
				player.sendMessage(ChatColor.RED + "That player could not be found.");
				return true;
			}
			Player target = targetList.get(0);
			target.teleport(player.getLocation());
			player.sendMessage(ChatColor.AQUA + "Teleported " + target.getDisplayName() + " to you.");
			target.sendMessage(ChatColor.AQUA + "You have been teleported to " + player.getDisplayName() + ".");
			return true;
		}
		return false;
	}

}
