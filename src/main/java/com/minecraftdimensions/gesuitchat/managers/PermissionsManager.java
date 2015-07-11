package com.minecraftdimensions.gesuitchat.managers;

import com.minecraftdimensions.gesuitchat.geSuitChat;
import org.bukkit.entity.Player;

public class PermissionsManager {

	public static void addAllPermissions(Player player) {
		player.addAttachment(geSuitChat.instance, "gesuit.chat.*", true);
	}

	public static void addAdminPermissions(Player player) {
		player.addAttachment(geSuitChat.instance, "gesuit.chat.admin", true);
	}

	public static void addModPermissions(Player player) {
		player.addAttachment(geSuitChat.instance, "gesuit.chat.mod", true);
	}

	public static void addUserPermissions(Player player) {
		player.addAttachment(geSuitChat.instance, "gesuit.chat.user", true);
	}

	public static void addVIPPermissions(Player player) {
		player.addAttachment(geSuitChat.instance, "gesuit.chat.vip", true);
	}
}
