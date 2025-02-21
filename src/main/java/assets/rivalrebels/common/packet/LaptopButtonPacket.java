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

import assets.rivalrebels.common.tileentity.TileEntityLaptop;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class LaptopButtonPacket implements IMessage {
    BlockPos pos;

    public LaptopButtonPacket() {

    }

    public LaptopButtonPacket(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static class Handler implements IMessageHandler<LaptopButtonPacket, IMessage> {
        @Override
        public IMessage onMessage(LaptopButtonPacket m, MessageContext ctx) {
            if (ctx.getServerHandler().player.getDistanceSq(m.pos) < 100) {
                TileEntity te = ctx.getServerHandler().player.world.getTileEntity(m.pos);
                if (te instanceof TileEntityLaptop) {
                    ((TileEntityLaptop) te).onGoButtonPressed();
                }
            }
            return null;
        }
    }
}
