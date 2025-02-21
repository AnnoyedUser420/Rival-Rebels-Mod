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
package assets.rivalrebels.client.renderentity;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.entity.EntityCuchillo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_QUADS;

public class RenderCuchillo extends Render<EntityCuchillo> {
    public RenderCuchillo(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void doRender(EntityCuchillo par1EntityArrow, double par2, double par4, double par6, float par8, float par9) {
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etknife);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) par2, (float) par4, (float) par6);
        GlStateManager.rotate(par1EntityArrow.prevRotationYaw + (par1EntityArrow.rotationYaw - par1EntityArrow.prevRotationYaw) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(par1EntityArrow.prevRotationPitch + (par1EntityArrow.rotationPitch - par1EntityArrow.prevRotationPitch) * par9, 0.0F, 0.0F, 1.0F);
        Tessellator t = Tessellator.getInstance();
        byte var11 = 0;
        float var12 = 0.0F;
        float var13 = 0.5F;
        float var14 = (var11 * 10) / 32.0F;
        float var15 = (5 + var11 * 10) / 32.0F;
        float var16 = 0.0F;
        float var17 = 0.15625F;
        float var18 = (5 + var11 * 10) / 32.0F;
        float var19 = (10 + var11 * 10) / 32.0F;
        float normal = 0.05625F;
        GlStateManager.enableRescaleNormal();

        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(normal, normal, normal);
        GlStateManager.translate(-4.0F, 0.0F, 0.0F);
        BufferBuilder buf = t.getBuffer();
        buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
        buf.pos(-7.0D, -2.0D, -2.0D).tex(var16, var18).normal(normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, -2.0D, 2.0D).tex(var17, var18).normal(normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, 2.0D, 2.0D).tex(var17, var19).normal(normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, 2.0D, -2.0D).tex(var16, var19).normal(normal, 0.0F, 0.0F).endVertex();
        t.draw();
        GL11.glNormal3f(-normal, 0.0F, 0.0F);
        buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
        buf.pos(-7.0D, 2.0D, -2.0D).tex(var16, var18).normal(-normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, 2.0D, 2.0D).tex(var17, var18).normal(-normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, -2.0D, 2.0D).tex(var17, var19).normal(-normal, 0.0F, 0.0F).endVertex();
        buf.pos(-7.0D, -2.0D, -2.0D).tex(var16, var19).normal(-normal, 0.0F, 0.0F).endVertex();
        t.draw();

        for (int var23 = 0; var23 < 4; ++var23) {
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glNormal3f(0.0F, 0.0F, normal);
            buf.begin(GL_QUADS, DefaultVertexFormats.POSITION_TEX_NORMAL);
            buf.pos(-8.0D, -2.0D, 0.0D).tex(var12, var14).normal(0.0F, 0.0F, normal).endVertex();
            buf.pos(8.0D, -2.0D, 0.0D).tex(var13, var14).normal(0.0F, 0.0F, normal).endVertex();
            buf.pos(8.0D, 2.0D, 0.0D).tex(var13, var15).normal(0.0F, 0.0F, normal).endVertex();
            buf.pos(-8.0D, 2.0D, 0.0D).tex(var12, var15).normal(0.0F, 0.0F, normal).endVertex();
            t.draw();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCuchillo entity) {
        return null;
    }
}