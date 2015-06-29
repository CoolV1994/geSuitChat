package com.minecraftdimensions.gesuitchat.listeners;

import com.minecraftdimensions.gesuitchat.Utilities;
import com.minecraftdimensions.gesuitchat.geSuitChat;
import com.minecraftdimensions.gesuitchat.managers.ChannelManager;
import com.minecraftdimensions.gesuitchat.managers.PlayerManager;
import com.minecraftdimensions.gesuitchat.managers.PrefixSuffixManager;
import com.minecraftdimensions.gesuitchat.objects.GSPlayer;
import com.minecraftdimensions.gesuitchat.objects.ServerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class MessageListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived( String pluginChannel, Player receiver, byte[] message ) {
        if ( !pluginChannel.equalsIgnoreCase( geSuitChat.INCOMING_PLUGIN_CHANNEL ) )
            return;
        DataInputStream in = new DataInputStream( new ByteArrayInputStream( message ) );
        String channel = null;
        try {
            channel = in.readUTF();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        assert channel != null;
        if ( channel.equals( "SendGlobalChat" ) ) {
            try {
                ChannelManager.getGlobalChat( in.readUTF(), in.readUTF() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return;
        }
        if ( channel.equals( "GetVersion" ) ) {
            String p = null;
            try {
                p = in.readUTF();
            } catch ( IOException e ) {
            }
            if ( p != null ) {
                Player player = Bukkit.getPlayer( p );
                player.sendMessage( ChatColor.RED + "Chat - " + ChatColor.GOLD + geSuitChat.instance.getDescription().getVersion() );
            }
            PlayerManager.sendVersion();
            Bukkit.getConsoleSender().sendMessage( ChatColor.RED + "Chat - " + ChatColor.GOLD + geSuitChat.instance.getDescription().getVersion() );
        }
        if ( channel.equals( "SendAdminChat" ) ) {
            try {
                ChannelManager.getAdminChat( in.readUTF() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return;
        }
        if ( channel.equals( "SendPlayer" ) ) {
            try {
                PlayerManager.addPlayer( new GSPlayer( Utilities.makeUUID(in.readUTF()), in.readUTF(), in.readUTF(), in.readBoolean(), in.readUTF(), in.readUTF(), in.readBoolean(), in.readBoolean(), in.readBoolean() ) );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return;
        }
        if ( channel.equals( "SendChannel" ) ) {
            if ( !ChannelManager.receivedChannels ) {
                ChannelManager.receivedChannels = true;
            }
            try {
                ChannelManager.addChannel( in.readUTF() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return;
        }
        if ( channel.equals( "SendServerData" ) ) {
            try {
                new ServerData( in.readUTF(), in.readUTF(), in.readInt(), in.readBoolean(), in.readUTF() );
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return;
        }
        if ( channel.equals( "PrefixesAndSuffixes" ) ) {

            boolean prefix;
            String group;
            String affix;
            try {
                prefix = in.readBoolean();
                group = in.readUTF();
                affix = in.readUTF();
                if ( prefix ) {
                    PrefixSuffixManager.prefixes.put( group, affix );
                    PrefixSuffixManager.prefix = true;
                    if ( Bukkit.getPluginManager().getPermission( "bungeesuite.chat.prefix." + group ) == null ) {
                        Bukkit.getPluginManager().addPermission( new Permission( "bungeesuite.chat.prefix." + group, "Gives access to the " + affix + " prefix", PermissionDefault.FALSE ) );
                    }
                } else {
                    PrefixSuffixManager.suffixes.put( group, affix );
                    PrefixSuffixManager.suffix = true;
                    if ( Bukkit.getPluginManager().getPermission( "bungeesuite.chat.suffix." + group ) == null ) {
                        Bukkit.getPluginManager().addPermission( new Permission( "bungeesuite.chat.suffix." + group, "Gives access to the " + affix + " suffix", PermissionDefault.FALSE ) );
                    }
                }
            } catch ( IOException e ) {
                e.printStackTrace();
            }

        }
        if ( channel.equals( "SendPlayersIgnores" ) ) {
            String player = null;
            String ignoresString[] = null;
            try {
                player = in.readUTF();
                ignoresString = in.readUTF().split( "%" );
            } catch ( IOException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            final ArrayList<String> ignores = new ArrayList<>();
            Collections.addAll( ignores, ignoresString );
            final String name = player;
            GSPlayer p = PlayerManager.getPlayer( player );
            if ( p != null ) {
                p.setIgnores( ignores );
            } else {
                Bukkit.getScheduler().runTaskLaterAsynchronously( geSuitChat.instance, new Runnable() {

                    @Override
                    public void run() {
                        PlayerManager.getPlayer( name ).setIgnores( ignores );
                    }

                }, 10L );
            }
            return;
        }
        if ( channel.equals( "Reload" ) ) {
            ChannelManager.reload();
        }
        if ( channel.equals( "PluginCheck" ) ) {
            if ( geSuitChat.factionChat ) {
                ChannelManager.requestFactionChannels();
            }
            if ( geSuitChat.towny ) {
                ChannelManager.requestTownyChannels();
            }
        }
    }

}
