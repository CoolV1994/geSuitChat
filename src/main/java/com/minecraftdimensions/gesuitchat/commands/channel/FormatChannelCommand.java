package com.minecraftdimensions.gesuitchat.commands.channel;

import com.minecraftdimensions.gesuitchat.managers.ChannelManager;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import com.minecraftdimensions.gesuitchat.objects.GSPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FormatChannelCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {
		GSPlayer p = PlayerManager.getPlayer(sender);
		if (args.length == 0) {
			ChannelManager.setChannelFormat(sender, p.getChannelName());
			return true;
		} else {
			return false;
		}
	}

}
