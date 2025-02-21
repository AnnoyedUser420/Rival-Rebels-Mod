/*******************************************************************************
 * Copyright (c) 2012, 2016 Rodol Phito.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Mozilla Public License Version 2.0
 * which accompanies this distribution, and is available at
 * https://www.mozilla.org/en-US/MPL/2.0/
 * <p>
 * Rival Rebels Mod. All code, art, and design by Rodol Phito.
 * <p>
 * http://RivalRebels.com/
 *******************************************************************************/
package assets.rivalrebels.common.command;

import assets.rivalrebels.common.entity.EntityHotPotato;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CommandHotPotato extends CommandBase {
    public static int x = -1;
    public static int y = -1;
    public static int z = -1;
    public static World world = null;
    public static boolean roundinprogress = false;

    @Override
    public String getName() {
        return "rrhotpotato";
    }

    @Override
    public String getUsage(ICommandSender par1ICommandSender) {
        return "/" + getName();
    }

    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] array) {
        if (world == null) {
            sender.sendMessage(new TextComponentString("§cPlace a jump block and use pliers on it to set the hot potato drop point."));
            return;
        }
        if (array.length == 1) {
            String str = array[0];
            if ("stop".equals(str)) {
                roundinprogress = false;
                sender.sendMessage(new TextComponentString("§cRound stopped."));
                return;
            } else {
                if (roundinprogress) {
                    sender.sendMessage(new TextComponentString("§cRound already in progress! Do /rrhotpotato stop to end the current round."));
                    return;
                }
                int n = Integer.parseInt(array[0]);
                sender.sendMessage(new TextComponentString("§cLet the Hot Potato games begin! " + n + " rounds."));
                EntityHotPotato tsar = new EntityHotPotato(world, x, y, z, n);
                world.spawnEntity(tsar);
                roundinprogress = true;
                return;
            }
        }
        sender.sendMessage(new TextComponentString("§cUsage: /rrhotpotato [number of rounds]"));
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List<String> addTabCompletionOptions(ICommandSender p, String[] s) {
        List<String> l = new ArrayList<>();
        l.add("nuketime");
        return l;
    }
}