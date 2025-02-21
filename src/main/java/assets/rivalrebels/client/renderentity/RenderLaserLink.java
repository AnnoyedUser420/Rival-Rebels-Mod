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

import assets.rivalrebels.common.entity.EntityLaserLink;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderLaserLink extends Render {
    static float red = 0.5F;
    static float green = 0.1F;
    static float blue = 0.1F;

    public void renderLaserLink(EntityLaserLink ell, double x, double y, double z, float yaw, float pitch) {
        double distance = ell.motionX * 100;
        if (distance > 0) {
            float radius = 0.7F;
            Tessellator tessellator = Tessellator.instance;

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.disableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GlStateManager.translate((float) x, (float) y, (float) z);
            GlStateManager.rotate(-ell.rotationYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(ell.rotationPitch, 1.0F, 0.0F, 0.0F);

            for (float o = 0; o <= radius; o += radius / 16) {
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(red, green, blue, 1f);
                tessellator.addVertex(0 + o, 0 - o, 0);
                tessellator.addVertex(0 + o, 0 + o, 0);
                tessellator.addVertex(0 + o, 0 + o, 0 + distance);
                tessellator.addVertex(0 + o, 0 - o, 0 + distance);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(red, green, blue, 1f);
                tessellator.addVertex(0 - o, 0 - o, 0);
                tessellator.addVertex(0 + o, 0 - o, 0);
                tessellator.addVertex(0 + o, 0 - o, 0 + distance);
                tessellator.addVertex(0 - o, 0 - o, 0 + distance);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(red, green, blue, 1f);
                tessellator.addVertex(0 - o, 0 + o, 0);
                tessellator.addVertex(0 - o, 0 - o, 0);
                tessellator.addVertex(0 - o, 0 - o, 0 + distance);
                tessellator.addVertex(0 - o, 0 + o, 0 + distance);
                tessellator.draw();
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_F(red, green, blue, 1f);
                tessellator.addVertex(0 + o, 0 + o, 0);
                tessellator.addVertex(0 - o, 0 + o, 0);
                tessellator.addVertex(0 - o, 0 + o, 0 + distance);
                tessellator.addVertex(0 + o, 0 + o, 0 + distance);
                tessellator.draw();
            }

            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void doRender(Entity entityLaserLink, double x, double y, double z, float yaw, float pitch) {
        this.renderLaserLink((EntityLaserLink) entityLaserLink, x, y, z, yaw, pitch);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}
