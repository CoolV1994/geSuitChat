package com.minecraftdimensions.gesuitchat.tasks;

import com.google.common.collect.Iterables;
import com.minecraftdimensions.gesuitchat.geSuitChat;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;


public class PluginMessageTask extends BukkitRunnable {
    private final ByteArrayOutputStream bytes;

    public PluginMessageTask( ByteArrayOutputStream bytes ) {
        this.bytes = bytes;
    }

    public void run() {
        if ( Bukkit.getOnlinePlayers().size() > 0 ) {
            Iterables.get(Bukkit.getOnlinePlayers(), 0).sendPluginMessage(geSuitChat.instance, geSuitChat.OUTGOING_PLUGIN_CHANNEL, bytes.toByteArray());
        }
    }

}