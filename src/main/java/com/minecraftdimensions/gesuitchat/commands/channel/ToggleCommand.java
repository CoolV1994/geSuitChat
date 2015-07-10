package com.minecraftdimensions.gesuitchat.commands.channel;

import com.minecraftdimensions.gesuitchat.managers.ChannelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {
		if (args.length == 0) {
			ChannelManager.togglePlayersChannel(sender);
		} else {
			ChannelManager.togglePlayerToChannel(sender, args[0]);
		}
		return true;
	}

}
