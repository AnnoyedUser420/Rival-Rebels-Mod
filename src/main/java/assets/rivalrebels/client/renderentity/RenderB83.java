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
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.common.entity.EntityB83;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderB83 extends Render<EntityB83> {
    public static ModelFromObj md;

    public RenderB83(RenderManager renderManager) {
        super(renderManager);
        try {
            md = ModelFromObj.readObjFile("c.obj");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doRender(EntityB83 b83, double x, double y, double z, float par8, float par9) {
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.scale(RivalRebels.nukeScale, RivalRebels.nukeScale, RivalRebels.nukeScale);
        GlStateManager.rotate(b83.rotationYaw - 90.0f, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(b83.rotationPitch - 180.0f, 0.0F, 0.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etb83);
        md.render();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityB83 entity) {
        return null;
    }
}