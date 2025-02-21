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

import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class CommandKillme extends CommandBase {
    @Override
    public String getName() {
        return "killme";
    }

    @Override
    public String getUsage(ICommandSender par1ICommandSender) {
        return "/" + getName();
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender par1) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] array) throws PlayerNotFoundException {
        EntityPlayer person = getCommandSenderAsPlayer(sender);
        person.attackEntityFrom(RivalRebelsDamageSource.cyanide, 10000);
        sender.sendMessage(new TextComponentString("Do you think the cyanide tasted good?"));
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender par1ICommandSender, String[] par2ArrayOfStr, BlockPos pos) {
        return par2ArrayOfStr.length >= 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, server.getOnlinePlayerNames()) : null;
    }
}