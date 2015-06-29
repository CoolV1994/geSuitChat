package com.minecraftdimensions.gesuitchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;

public class ChatspyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		PlayerManager.setChatSpyPlayer(sender.getName());
		return true;
	}

}
