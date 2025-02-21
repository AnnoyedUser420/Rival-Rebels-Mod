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
import assets.rivalrebels.common.entity.EntityB2Frag;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderB2Frag extends Render<EntityB2Frag> {
    ModelFromObj md1;
    ModelFromObj md2;

    public RenderB2Frag(RenderManager renderManager) {
        super(renderManager);
        try {
            md1 = ModelFromObj.readObjFile("f.obj");
            md1.scale(3, 3, 3);
            md2 = ModelFromObj.readObjFile("g.obj");
            md2.scale(3, 3, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doRender(EntityB2Frag b2spirit, double x, double y, double z, float par8, float par9) {
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(b2spirit.rotationYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(b2spirit.rotationPitch, 0.0F, 0.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etb2spirit);
        GlStateManager.disableCull();
        if (b2spirit.type == 0) md1.render();
        if (b2spirit.type == 1) md2.render();
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityB2Frag entity) {
        return null;
    }
}