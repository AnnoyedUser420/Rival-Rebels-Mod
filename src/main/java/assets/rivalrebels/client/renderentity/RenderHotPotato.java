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

import assets.rivalrebels.client.model.ModelTsarBomba;
import assets.rivalrebels.common.entity.EntityHotPotato;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderHotPotato extends Render {
    private final ModelTsarBomba model;

    public RenderHotPotato() {
        model = new ModelTsarBomba();
    }

    public void renderB83(EntityHotPotato b83, double x, double y, double z, float par8, float par9) {
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);
        GlStateManager.rotate(b83.rotationYaw - 90.0f, 0.0F, 1.0F, 0.0F);
        //GlStateManager.rotate(90.0f, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(b83.rotationPitch - 90.0f, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(2.0f, 1.0f, 2.0f);
        model.render();
        GlStateManager.popMatrix();
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then handing it off to a worker function which does the actual work. In all
     * probabilty, the class Render is generic (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1, double d2, float f, float f1). But JAD is pre
     * 1.5 so doesn't do that.
     */
    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        renderB83((EntityHotPotato) par1Entity, par2, par4, par6, par8, par9);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }
}