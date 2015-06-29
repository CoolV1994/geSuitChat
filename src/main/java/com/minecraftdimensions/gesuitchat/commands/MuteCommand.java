package com.minecraftdimensions.gesuitchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;


public class MuteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(args.length>0){
			PlayerManager.mutePlayer(sender,args[0],true);
			return true;
		}
		return false;
	}

}
