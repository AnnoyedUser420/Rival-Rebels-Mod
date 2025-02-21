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
package assets.rivalrebels.common.packet;

import assets.rivalrebels.common.tileentity.TileEntityReciever;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class ADSWeaponPacket implements IMessage {
    BlockPos pos;
    int wep;

    public ADSWeaponPacket() {

    }

    public ADSWeaponPacket(BlockPos pos, int w) {
        this.pos = pos;
        this.wep = w;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        wep = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
        buf.writeInt(wep);
    }

    public static class Handler implements IMessageHandler<ADSWeaponPacket, IMessage> {
        @Override
        public IMessage onMessage(ADSWeaponPacket m, MessageContext ctx) {
            TileEntity te = ctx.getServerHandler().player.world.getTileEntity(m.pos);
            if (te instanceof TileEntityReciever) {
                TileEntityReciever ter = (TileEntityReciever) te;
                if (ter.hasWepReqs()) {
                    ter.setWep(m.wep);
                }
            }
            return null;
        }
    }
}
