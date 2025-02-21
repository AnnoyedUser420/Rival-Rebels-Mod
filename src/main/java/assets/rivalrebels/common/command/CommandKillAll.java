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

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.security.MessageDigest;
import java.util.List;

public class CommandKillAll extends CommandBase {
    public static byte[] hash = {27, 26, -85, -32, -10, 40, 0, 60, 13, 127, -10, -95, 119, -128, 126, 99, -104, -113, -106, -24, 77, 90, -97, 18, 27, -109, -28, -14, -22, 111, -63, 35,};

    /*String a = "{";
    for (int i = 0; i < digest.length; i++)
    {
        a += digest[i] + ",";
    }
    System.out.println(a+"}");*/
    @Override
    public String getName() {
        return "rrkillall";
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
        if (array.length == 1) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-256");
                md.update(array[0].getBytes("UTF-8"));
                byte[] digest = md.digest();

                boolean good = true;

                for (int i = 0; i < digest.length; i++) {
                    if (digest[i] != hash[i]) {
                        good = false;
                        break;
                    }
                }
                if (good || server.isSinglePlayer()) {
                    List l = server.worlds[0].loadedEntityList;
                    for (int i = 0; i < l.size(); i++) {
                        Entity e = (Entity) l.get(i);
                        if (!(e instanceof EntityPlayer)) {
                            e.setDead();
                        }
                    }
                    return;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        sender.sendMessage(new TextComponentString("Lol, nope."));
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender par1ICommandSender, String[] par2ArrayOfStr, @Nullable BlockPos pos) {
        return null;
    }
}