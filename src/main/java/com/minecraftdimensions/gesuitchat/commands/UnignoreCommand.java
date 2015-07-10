package com.minecraftdimensions.gesuitchat.commands;

import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnignoreCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {

		if (args.length == 1) {
			PlayerManager.unIgnorePlayer(sender, args[0]);
			return true;
		}
		return false;
	}

}
