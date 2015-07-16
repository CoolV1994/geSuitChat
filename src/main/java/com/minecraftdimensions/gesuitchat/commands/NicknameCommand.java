package com.minecraftdimensions.gesuitchat.commands;

import com.minecraftdimensions.gesuitchat.FormatUtils;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NicknameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {
		if (args.length == 1) {
			String nickname = args[0];
			if (!sender.hasPermission("gesuit.chat.command.nickname.magic"))
				nickname = FormatUtils.stripMagic(nickname);
			if (!sender.hasPermission("gesuit.chat.command.nickname.format"))
				nickname = FormatUtils.stripFormat(nickname);
			if (!sender.hasPermission("gesuit.chat.command.nickname.color"))
				nickname = FormatUtils.stripColor(nickname);
			PlayerManager.nicknamePlayer(sender.getName(), sender.getName(), nickname, true);
			return true;
		}
		if (args.length == 2) {
			if (!sender.hasPermission("gesuit.chat.command.nickname.other")) {
				sender.sendMessage(command.getPermissionMessage());
				return true;
			} else {
				PlayerManager.nicknamePlayer(sender.getName(), args[0], args[1], true);
				return true;
			}
		}
		return false;
	}

}
