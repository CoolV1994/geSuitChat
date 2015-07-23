package com.minecraftdimensions.gesuitchat.managers;

import com.minecraftdimensions.gesuitchat.geSuitChat;
import com.minecraftdimensions.gesuitchat.tasks.PluginMessageTask;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Vinnie on 7/23/2015.
 */
public class MailManager {
	public static void sendPlayerMessage(String sender, String reciever, String message) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendMail");
			out.writeUTF(sender);
			out.writeUTF(reciever);
			out.writeUTF(message);
		} catch (IOException s) {
			s.printStackTrace();
		}
		new PluginMessageTask(b).runTaskAsynchronously(geSuitChat.instance);
	}

	public static void getPlayerMail(String sender, int page) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ReadMail");
			out.writeUTF(sender);
			out.writeInt(page);
		} catch (IOException s) {
			s.printStackTrace();
		}
		new PluginMessageTask(b).runTaskAsynchronously(geSuitChat.instance);
	}

	public static void clearInbox(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ClearMail");
			out.writeUTF(sender);
		} catch (IOException s) {
			s.printStackTrace();
		}
		new PluginMessageTask(b).runTaskAsynchronously(geSuitChat.instance);
	}
}
