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
package assets.rivalrebels.client.gui;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.guihelper.GuiKnob;
import assets.rivalrebels.common.item.weapon.ItemTesla;
import assets.rivalrebels.common.packet.ItemUpdate;
import assets.rivalrebels.common.packet.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class GuiTesla extends GuiScreen {
    private final int xSizeOfTexture = 256;
    private final int ySizeOfTexture = 256;
    private int posX;
    private int posY;
    private GuiKnob knob;
    private int s = 0;

    public GuiTesla(int start) {
        s = start - 90;
    }

    @Override
    public void initGui() {
        posX = (this.width - xSizeOfTexture) / 2;
        posY = (this.height - ySizeOfTexture) / 2;
        this.buttonList.clear();
        knob = new GuiKnob(0, posX + 108, posY + 176, -90, 90, s, true, "Knob");
        this.buttonList.add(knob);
        // mc.inGameHasFocus = true;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void updateScreen() {

    }

    @Override
    public void drawScreen(int x, int y, float d) {
        Tessellator tessellator = Tessellator.getInstance();
        float f = 0.00390625F;
        mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(RivalRebels.guitesla);
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(posX, posY + ySizeOfTexture, zLevel).tex(0, ySizeOfTexture * f).endVertex();
        buf.pos(posX + xSizeOfTexture, posY + ySizeOfTexture, zLevel).tex(xSizeOfTexture * f, ySizeOfTexture * f).endVertex();
        buf.pos(posX + xSizeOfTexture, posY, zLevel).tex(xSizeOfTexture * f, 0).endVertex();
        buf.pos(posX, posY, zLevel).tex(0, 0).endVertex();
        tessellator.draw();
        super.drawScreen(x, y, d);
        if (!(RivalRebels.altRkey ? Keyboard.isKeyDown(Keyboard.KEY_F) : Keyboard.isKeyDown(Keyboard.KEY_R))) {
            this.mc.displayGuiScreen(null);
            this.mc.setIngameFocus();
            PacketDispatcher.packetsys.sendToServer(new ItemUpdate(mc.player.inventory.currentItem, knob.getDegree()));
            ItemStack itemstack = mc.player.inventory.getStackInSlot(mc.player.inventory.currentItem);
            if (itemstack != ItemStack.EMPTY && itemstack.getItem() instanceof ItemTesla) {
                if (!itemstack.hasTagCompound()) itemstack.setTagCompound(new NBTTagCompound());
                itemstack.getTagCompound().setInteger("dial", knob.getDegree());
            }
        }
    }
}
