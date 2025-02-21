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

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.packet.PacketDispatcher;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class CommandResetGame extends CommandBase {
    @Override
    public String getName() {
        return "rrreset";
    }

    @Override
    public String getUsage(ICommandSender par1ICommandSender) {
        return "/" + getName() + " <player>";
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
    public void execute(MinecraftServer server, ICommandSender sender, String[] array) throws PlayerNotFoundException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        if (array.length == 1 && array[0].length() > 0) {
            if (array[0].equals("all")) {
                RivalRebels.round.rrplayerlist.clearTeam();
                PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
                player.sendMessage(new TextComponentString("§7All players have been reset."));
            } else if (RivalRebels.round.rrplayerlist.contains(array[0])) {
                RivalRebels.round.rrplayerlist.getForName(array[0]).clearTeam();
                PacketDispatcher.packetsys.sendToAll(RivalRebels.round.rrplayerlist);
                player.sendMessage(new TextComponentString("§7Player successfully reset."));
            } else {
                player.sendMessage(new TextComponentString("§7No player by that name."));
            }
        }
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    @Override
    public List getTabCompletions(MinecraftServer server, ICommandSender par1ICommandSender, String[] par2ArrayOfStr, BlockPos pos) {
        return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, server.getOnlinePlayerNames()) : null;
    }
}