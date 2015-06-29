package com.minecraftdimensions.gesuitchat.commands;

import com.minecraftdimensions.gesuitchat.FormatUtils;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NameColorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (args.length == 1) {
			String nickname = args[0];
			if (sender.getName().equals(FormatUtils.stripAll(nickname))) {
				if (!sender.hasPermission("bungeesuite.chat.command.namecolor.magic"))
					nickname = FormatUtils.stripMagic(nickname);
				if (!sender.hasPermission("bungeesuite.chat.command.namecolor.format"))
					nickname = FormatUtils.stripFormat(nickname);
				PlayerManager.nicknamePlayer(sender.getName(), sender.getName(), nickname, true);
				return true;
			} else {
				sender.sendMessage("This must be the same as your username. Only color codes allowed.");
				return true;
			}
		}
		if (args.length == 2) {
			if (!sender.hasPermission("bungeesuite.chat.command.namecolor.other")) {
				sender.sendMessage(command.getPermissionMessage());
				return true;
			} else {
				String targetPlayer = args[0];
				String nickname = args[1];
				if (targetPlayer.equals(FormatUtils.stripAll(nickname))) {
					if (!sender.hasPermission("bungeesuite.chat.command.namecolor.other.magic"))
						nickname = FormatUtils.stripMagic(nickname);
					if (!sender.hasPermission("bungeesuite.chat.command.namecolor.other.format"))
						nickname = FormatUtils.stripFormat(nickname);
					PlayerManager.nicknamePlayer(sender.getName(), targetPlayer, nickname, true);
					return true;
				} else {
					sender.sendMessage("This must be the same as the username. Only color codes allowed.");
					return true;
				}
			}
		}
		return false;
	}

}
