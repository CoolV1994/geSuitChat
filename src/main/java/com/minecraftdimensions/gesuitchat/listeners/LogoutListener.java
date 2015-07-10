package com.minecraftdimensions.gesuitchat.listeners;


import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import com.minecraftdimensions.gesuitchat.objects.ServerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class LogoutListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void setFormatChat(PlayerQuitEvent e) {
		PlayerManager.unloadPlayer(e.getPlayer().getName());
		if (ServerData.usingConnectionMessages()) {
			e.setQuitMessage(null);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void setFormatChat(PlayerKickEvent e) {
		PlayerManager.unloadPlayer(e.getPlayer().getName());
		if (ServerData.usingConnectionMessages()) {
			e.setLeaveMessage(null);
		}
	}


}
