package com.minecraftdimensions.gesuitchat.tasks;

import com.google.common.collect.Iterables;
import com.minecraftdimensions.gesuitchat.geSuitChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;


public class PluginMessageTask extends BukkitRunnable {
	private final ByteArrayOutputStream bytes;

	public PluginMessageTask(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
		if (p == null) {
			System.out.println("[geSuitChat] " + ChatColor.RED + "Unable to send Plugin Message - No players online.");
			return;
		}
		p.sendPluginMessage(geSuitChat.instance, "geSuitChat", bytes.toByteArray());
	}

}