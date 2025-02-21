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
import assets.rivalrebels.client.guihelper.GuiButton;
import assets.rivalrebels.client.guihelper.GuiScroll;
import assets.rivalrebels.common.round.RivalRebelsClass;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class GuiClass extends GuiScreen {
    private final int xSizeOfTexture = 256;
    private final int ySizeOfTexture = 256;
    private int posX;
    private int posY;
    private final String[] displaytext = new String[15];
    private final float[] sizelookup = new float[]{1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
    private GuiButton nextButton;
    private GuiButton doneButton;
    private GuiScroll gameScroll;
    private boolean prevClick = true;
    private RivalRebelsClass rrclass = RivalRebelsClass.NONE;

    public GuiClass(RivalRebelsClass rrc) {
        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;
        rrclass = rrc;
    }

    @Override
    public void initGui() {
        posX = (this.width - xSizeOfTexture) / 2;
        posY = (this.height - ySizeOfTexture) / 2;
        this.buttonList.clear();

        nextButton = new GuiButton(0, posX + 188, posY + 102, 60, 11, "RivalRebels.class.next");
        doneButton = new GuiButton(1, posX + 188, posY + 119, 60, 11, "RivalRebels.class.done");
        gameScroll = new GuiScroll(2, posX + 243, posY + 9, 74);
        this.buttonList.add(nextButton);
        this.buttonList.add(doneButton);
        this.buttonList.add(gameScroll);
    }

    @Override
    public void updateScreen() {

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void handleKeyboardInput() {

    }

    @Override
    public void drawScreen(int x, int y, float d) {
        if (rrclass == RivalRebelsClass.NONE) rrclass = RivalRebelsClass.REBEL;
        Tessellator tessellator = Tessellator.getInstance();
        float f = 0.00390625F;
        drawDefaultBackground();
        drawGradientRect(posX, posY, posX + xSizeOfTexture, posY + ySizeOfTexture, 0xFF000000, 0xFF000000);
        drawPanel(posX + 162, posY + 40, 80, 74, gameScroll.getScroll(), gameScroll.limit, rrclass.name + ".description");
        drawGradientRect(posX + 160, posY + 9, posX + 244, posY + 38, 0xFF000000, 0xFF000000);
        GlStateManager.color(1F, 1F, 1F);
        this.mc.renderEngine.bindTexture(RivalRebels.guitclass);
        BufferBuilder buf = tessellator.getBuffer();
        buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(posX, posY + ySizeOfTexture, zLevel).tex(0, ySizeOfTexture * f).endVertex();
        buf.pos(posX + xSizeOfTexture, posY + ySizeOfTexture, zLevel).tex(xSizeOfTexture * f, ySizeOfTexture * f).endVertex();
        buf.pos(posX + xSizeOfTexture, posY, zLevel).tex(xSizeOfTexture * f, 0).endVertex();
        buf.pos(posX, posY, zLevel).tex(0, 0).endVertex();
        tessellator.draw();

        this.mc.renderEngine.bindTexture(rrclass.resource);

        buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(posX + 12, posY + 140, zLevel).tex(0, ySizeOfTexture * f).endVertex();
        buf.pos(posX + 140, posY + 140, zLevel).tex(xSizeOfTexture * f, ySizeOfTexture * f).endVertex();
        buf.pos(posX + 140, posY + 12, zLevel).tex(xSizeOfTexture * f, 0).endVertex();
        buf.pos(posX + 12, posY + 12, zLevel).tex(0, 0).endVertex();
        tessellator.draw();

        float scalefactor = 1.5f;
        GlStateManager.scale(scalefactor * 1.2f, scalefactor, scalefactor);
        drawCenteredString(fontRenderer, rrclass.name, (int) ((posX + 76) / (scalefactor * 1.2f)), (int) ((posY + 16) / scalefactor), rrclass.color);
        GlStateManager.scale(1 / (scalefactor * 1.2f), 1 / scalefactor, 1 / scalefactor);

        scalefactor = 0.666f;
        GlStateManager.scale(scalefactor, scalefactor, scalefactor);
        drawCenteredString(fontRenderer, I18n.translateToLocal("RivalRebels.class." + rrclass.name + ".minidesc"), (int) ((posX + 76) / scalefactor), (int) ((posY + 28) / scalefactor), rrclass.color);
        GlStateManager.scale(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);

        scalefactor = 0.666f;
        GlStateManager.scale(scalefactor, scalefactor, scalefactor);
        drawCenteredString(fontRenderer, I18n.translateToLocal("RivalRebels.class.description"), (int) ((posX + 181) / scalefactor), (int) ((posY + 28) / scalefactor), rrclass.color);
        GlStateManager.scale(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);

        GlStateManager.color(1F, 1F, 1F);

        for (int i = 0; i < sizelookup.length; i++) {
            int X = posX + 18 + (i % 9) * 22;
            int Y = posY + 158 + 22 * (int) Math.floor(i / 9);
            float size = sizelookup[i];
            if (x >= X && y >= Y && x < X + 16 && y < Y + 16) {
                if (size < 1.5) {
                    size += 0.1;
                }
            } else if (size > 1) {
                size -= 0.1;
            }
            sizelookup[i] = size;
        }
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        for (int i = rrclass.inventory.length - 1; i >= 0; i--) {
            int X = posX + 18 + (i % 9) * 22;
            int Y = posY + 158 + 22 * (i / 9);
            GlStateManager.pushMatrix();
            GlStateManager.translate(X + 8, Y + 8, 0);
            GlStateManager.scale(sizelookup[i], sizelookup[i], sizelookup[i]);
            GlStateManager.translate(-X - 8, -Y - 8, 0);
            itemRender.renderItemIntoGUI(rrclass.inventory[i], X, Y);
            GlStateManager.popMatrix();
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        }
        for (int i = rrclass.inventory.length - 1; i >= 0; i--) {
            int X = posX + 18 + (i % 9) * 22;
            int Y = posY + 158 + 22 * (i / 9);
            GlStateManager.pushMatrix();
            GlStateManager.translate(X + 8, Y + 8, 20);
            GlStateManager.scale(sizelookup[i], sizelookup[i], sizelookup[i]);
            GlStateManager.translate(-X - 8, -Y - 8, 0);
            ItemStack itemstack = rrclass.inventory[i];
            if (itemstack.getCount() > 1)
                fontRenderer.drawStringWithShadow("" + itemstack.getCount(), X + 17 - fontRenderer.getStringWidth("" + itemstack.getCount()), Y + 9, 0xFFFFFF);
            if (sizelookup[i] > 1) {
                drawGradientRect(X + 17, Y + 3, (int) (X + ((fontRenderer.getStringWidth(itemstack.getDisplayName()) + 4) * (sizelookup[i] - 1) * 2) + 15), Y + 13, 0xaa111111, 0xaa111111);
                fontRenderer.drawStringWithShadow(itemstack.getDisplayName(), X + 18, Y + 4, 0xFFFFFF);
            }
            GlStateManager.popMatrix();
        }

        super.drawScreen(x, y, d);

        if (Mouse.isButtonDown(0) && !prevClick) {
            if (nextButton.mousePressed(mc, x, y)) {
                switch (rrclass) {
                    case HACKER:
                    case NONE:
                        rrclass = RivalRebelsClass.REBEL;
                        break;
                    case INTEL:
                        rrclass = RivalRebelsClass.HACKER;
                        break;
                    case NUKER:
                        rrclass = RivalRebelsClass.INTEL;
                        break;
                    case REBEL:
                        rrclass = RivalRebelsClass.NUKER;
                        break;
                }
            }
            if (doneButton.mousePressed(mc, x, y)) {
                this.mc.displayGuiScreen(new GuiSpawn(rrclass));
            }
        }
        prevClick = Mouse.isButtonDown(0);
    }

    protected void drawPanel(int x, int y, int width, int height, int scroll, int scrolllimit, String display) {
        int length = 10;
        int dist = (int) (-((float) scroll / (float) scrolllimit) * (((length) * 10) - height));
        float scalefactor = 0.6666f;
        GlStateManager.scale(scalefactor, scalefactor, scalefactor);
        fontRenderer.drawSplitString(I18n.translateToLocal("RivalRebels.class." + display), (int) (x * 1.5), (int) ((y + dist) * 1.5), (int) (width * 1.5), 0xffffff);
        GlStateManager.scale(1 / scalefactor, 1 / scalefactor, 1 / scalefactor);
    }
}
