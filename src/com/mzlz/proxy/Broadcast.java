package com.mzlz.proxy;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

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

public class Broadcast extends Command {

    public Broadcast(String name) {
        super("broadcast", "mclz.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(!(sender instanceof ProxiedPlayer)) {
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer)sender;

        if(!p.hasPermission("mclz.admin")) {
            p.sendMessage(Core.TAG + "You don't have valid permissions.");
            return;
        }
        else {
            if (args.length == 0) {
                p.sendMessage(Core.TAG + "Invalid Usage! §c/broadcast <Message>");
                return;
            }

            StringBuilder builder = new StringBuilder();

            for (String s : args) {
                builder.append(ChatColor.translateAlternateColorCodes('&', s));
                builder.append(" ");
            }

            String message = builder.substring(0, builder.length() - 1);

            for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
                player.sendMessage("§4§l§m=---=---=---=---=---=---=---=");
                player.sendMessage("§c§l> §c" + sender.getName() + " says...");
                player.sendMessage("§c§l> §c" + message);
                player.sendMessage("§4§l§m=---=---=---=---=---=---=---=");
            }
        }
    }
}
