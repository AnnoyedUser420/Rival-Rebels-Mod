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

import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;

import java.util.List;

public class CommandPlaySound extends CommandBase {
    @Override
    public String getName() {
        return "rrsoundsystem";
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
    public void execute(MinecraftServer server, ICommandSender sender, String[] array) {
        if (array.length == 4) {
            int dir = 0;
            int num = 0;
            float vol = 0f;
            float pit = 0f;
            try {
                dir = Integer.parseInt(array[0].trim());
                num = Integer.parseInt(array[1].trim());
                vol = Float.parseFloat(array[2].trim());
                pit = Float.parseFloat(array[3].trim());
            } catch (Exception E) {
                sender.sendMessage(new TextComponentString("No!"));
            }
            Vec3d cc = sender.getPositionVector();
            RivalRebelsSoundPlayer.playSound(sender.getEntityWorld(), dir, num, cc.x, cc.y, cc.z, vol, pit);
        } else {
            sender.sendMessage(new TextComponentString("No!"));
        }
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender par1ICommandSender, String[] par2ArrayOfStr, BlockPos pos) {
        return null;
    }
}