package com.minecraftdimensions.gesuitchat.commands;

import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NameColorOffCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 0) {
			PlayerManager.nicknamePlayer(sender.getName(), sender.getName(),"", false);
			return true;
		}
		if (args.length == 1) {
			if (!sender.hasPermission("bungeesuite.chat.command.namecolor.other")) {
				sender.sendMessage(command.getPermissionMessage());
				return true;
			} else {
				PlayerManager.nicknamePlayer(sender.getName(), args[0], "", false);
				return true;
			}
		}
		return false;
	}

}
