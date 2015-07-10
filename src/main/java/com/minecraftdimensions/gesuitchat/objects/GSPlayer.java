package com.minecraftdimensions.gesuitchat.objects;

import com.minecraftdimensions.gesuitchat.geSuitChat;
import com.minecraftdimensions.gesuitchat.managers.ChannelManager;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;


public class GSPlayer {
	private UUID uuid;
	private String playername;
	private String channel;
	private boolean muted;
	private String nickname = null;
	private String tempname = null;
	private boolean chatspying;
	private boolean dnd;
	private boolean afk;
	private ArrayList<String> ignores = new ArrayList<String>();

	public GSPlayer(UUID uuid, String name, String nickname, String channel, boolean muted, boolean chatspying, boolean dnd, boolean tps) {
		this.uuid = uuid;
		this.playername = name;
		this.nickname = nickname;
		this.channel = channel;
		this.muted = muted;
		this.chatspying = chatspying;
		this.dnd = dnd;
	}


	public GSPlayer(UUID uuid, String name, String channel, boolean isMuted, String nickname, String tempName, boolean isSpying, boolean isDND, boolean isAFK) {
		this.uuid = uuid;
		playername = name;
		this.channel = channel;
		muted = isMuted;
		if (nickname.equals("")) {
			this.nickname = null;
		} else {
			this.nickname = nickname;
		}
		if (tempName.equals("")) {
			this.tempname = null;
		} else {
			this.tempname = tempName;
		}
		this.chatspying = isSpying;
		this.dnd = isDND;
		this.afk = isAFK;
		if (getPlayer() != null) {
			getPlayer().setDisplayName(getDisplayingName());
		} else {
			Bukkit.getScheduler().runTaskLaterAsynchronously(geSuitChat.instance, new Runnable() {

				@Override
				public void run() {
					if (getPlayer() != null) {
						getPlayer().setDisplayName(getDisplayingName());
					}
				}

			}, 20);
		}
	}


	public String getName() {
		return playername;
	}

	public void setPlayerName(String name) {
		this.playername = name;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public void sendMessage(String message) {
		for (String line : message.split("\n")) {
			getPlayer().sendMessage(line);
		}
	}

	public String getChannelName() {
		return channel;
	}

	public Channel getChannel() {
		return ChannelManager.getChannel(channel);
	}

	public String getChannelFormat() {
		return getChannel().getFormat();
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public boolean isMuted() {
		return muted;
	}

	public void setMute(boolean mute) {
		this.muted = mute;
	}

	public boolean hasNickname() {
		return nickname != null;
	}

	public String getNickname() {
		if (nickname == null) {
			return "";
		}
		return nickname;
	}

	public void setNickname(String nick) {
		this.nickname = nick;
	}

	public boolean isChatSpying() {
		return chatspying;
	}

	public void setChatSpying(boolean spy) {
		this.chatspying = spy;
	}

	public boolean isDND() {
		return dnd;
	}

	public void setDND(boolean dnd) {
		this.dnd = dnd;
	}

	public void addIgnore(String player) {
		this.ignores.add(player);
	}

	public void removeIgnore(String player) {
		this.ignores.remove(player);
	}

	public boolean ignoringPlayer(String player) {
		return ignores.contains(player);
	}

	public boolean isOnline() {
		return PlayerManager.isPlayerOnline(getName());
	}

	public boolean isAFK() {
		return afk;
	}

	public void setAFK(boolean afk) {
		this.afk = afk;
	}

	public String getDisplayingName() {
		if (tempname != null) {
			return tempname;
		} else if (nickname != null) {
			return nickname;
		} else {
			return playername;
		}
	}

	public void updateDisplayName() {
		if (getPlayer() != null) {
			getPlayer().setDisplayName(getDisplayingName());
		} else {
			Bukkit.getScheduler().runTaskLater(geSuitChat.instance, new Runnable() {
				@Override
				public void run() {
					if (getPlayer() != null) {
						getPlayer().setDisplayName(getDisplayingName());
					} else {
						PlayerManager.unloadPlayer(getName());
					}
				}
			}, 20);
		}
	}

	public ArrayList<String> getIgnores() {
		return ignores;
	}

	public void setIgnores(ArrayList<String> ignores) {
		this.ignores = ignores;
	}
}
