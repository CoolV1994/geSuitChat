package com.minecraftdimensions.gesuitchat.commands;

import com.minecraftdimensions.gesuitchat.Utilities;
import com.minecraftdimensions.gesuitchat.managers.MailManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MailCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
							 String label, String[] args) {
		if (args.length == 0) {
			return false;
		}

		if ("send".equalsIgnoreCase(args[0])) {
			if (args.length < 3) {
				sender.sendMessage("/mail send (player) (message)");
			}
			String message = Utilities.toStringFrom(args, 2);
			MailManager.sendPlayerMessage(sender.getName(), args[1], message);
			return true;
		}

		if ("read".equalsIgnoreCase(args[0])) {
			if (args.length == 2) {
				MailManager.getPlayerMail(sender.getName(), Integer.parseInt(args[1]));
			} else {
				MailManager.getPlayerMail(sender.getName(), 0);
			}
			return true;
		}

		if ("clear".equalsIgnoreCase(args[0])) {
			MailManager.clearInbox(sender.getName());
			return true;
		}

		return false;
	}

}
