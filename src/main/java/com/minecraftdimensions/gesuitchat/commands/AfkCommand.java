package com.minecraftdimensions.gesuitchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minecraftdimensions.gesuitchat.managers.PlayerManager;

public class AfkCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
			PlayerManager.setPlayerAFK((Player)sender);
		return true;
	}

}
