package com.minecraftdimensions.gesuitchat.commands.channel;

import com.minecraftdimensions.gesuitchat.managers.ChannelManager;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import com.minecraftdimensions.gesuitchat.objects.GSPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GlobalCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {
		if (args.length > 0) {
			String message = "";
			for (String data : args) {
				message += data + " ";
			}
			if (message.charAt(0) == '/') {
				message = " " + message;
			}
			GSPlayer p = PlayerManager.getPlayer(sender);
			String channel = p.getChannelName();
			p.setChannel("Global");
			p.getPlayer().chat(message);
			p.setChannel(channel);
		} else {
			ChannelManager.togglePlayerToChannel(sender, "Global");
		}
		return true;
	}
}
