package com.mzlz.proxy;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

/**
 * ***********************************************************************
 * Copyright PixeledCow (C) 2015. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of PixeledCow. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the liscense, and void any
 * agreements with you, the third party.
 * Thanks.
 * ************************************************************************
 */


public class Core extends Plugin implements Listener {

    public static String TAG = "§4§l[§c§LMCLZ§4§l] §6";
    public static String STAFF = "§2[§aStaff§2] §7";

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, this);
        this.getProxy().getPluginManager().registerCommand(this, new Broadcast("broadcast"));
        this.getProxy().getPluginManager().registerCommand(this, new StaffChat("s"));
    }

    @EventHandler
    public void onLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if(player.hasPermission("mclz.staff")) {
            for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if(players.hasPermission("mclz.staff")) {
                    players.sendMessage(Core.STAFF + "§5" + player.getDisplayName() + " §7has joined §7§l" + player.getServer()
                            .getInfo().getName().toUpperCase() + "§7.");
                }
            }
        }

    }

    @EventHandler
    public void onConnect(ServerSwitchEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if(player.hasPermission("mclz.staff")) {
            for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if(players.hasPermission("mclz.staff")) {
                    players.sendMessage(Core.STAFF + "§5" + player.getDisplayName() + " §7joined §7§l" + player.getServer()
                        .getInfo().getName().toUpperCase() + "§7.");
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();

        if(player.hasPermission("mclz.staff")) {
            for(ProxiedPlayer players : ProxyServer.getInstance().getPlayers()) {
                if(players.hasPermission("mclz.staff")) {
                    players.sendMessage(Core.STAFF + "§5" + player.getDisplayName() + " §7disconnected.");
                }
            }
        }
    }
}
