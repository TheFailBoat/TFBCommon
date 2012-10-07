package uk.co.thefailboat.TFBCommon.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.co.thefailboat.TFBCommon.Main;

public class General implements CommandExecutor{
	private Main instance;
	
	public General(Main _instance){
		this.instance = _instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) return false;
		Player player = (Player)sender;
		if(cmd.getName().toLowerCase().equals("motd")){
			sender.sendMessage(ChatColor.AQUA + instance.motd);
			return true;
		}
		else if(cmd.getName().toLowerCase().equals("setmotd")){
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "You must provide a message!");
				return false;
			}
			String message = "";
			for(String arg : args){
				message += arg + " ";
			}
			message = message.substring(0, message.length()-1); //remove trailing " "
			instance.motd = message;
			instance.SaveConfiguration();
			
			player.sendMessage(ChatColor.AQUA + "New MOTD set.");
			return true;
		}
		else if(cmd.getName().toLowerCase().equals("toggleproperty")){
			
			if(args.length == 0){
				player.sendMessage(ChatColor.RED + "You must provide a property to toggle!");
			}
			
			else if(args[0].toLowerCase().equals("allowrain")){
				if(instance.AllowRain){
					instance.AllowRain = false;
					player.sendMessage(ChatColor.AQUA + "Rain is now globally disallowed.");
				}else{
					instance.AllowRain = true;
					player.sendMessage(ChatColor.AQUA + "Rain is now globally allowed.");
				}
				instance.SaveConfiguration();
				return true;
			}
			
			else{
				player.sendMessage(ChatColor.RED + "That property does not exist.");
			}
			
			player.sendMessage(ChatColor.GRAY + "Available Properties: AllowRain");			
			return true;
		}
		return false;
	}
}
