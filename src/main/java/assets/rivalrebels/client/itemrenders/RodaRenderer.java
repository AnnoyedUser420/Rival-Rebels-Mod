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
package assets.rivalrebels.client.itemrenders;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RodaRenderer implements IItemRenderer {
    ModelFromObj model;
    //ModelFromObj	model2;

    public RodaRenderer() {
        try {
            model = ModelFromObj.readObjFile("e.obj");
            //model2 = model.copy().pushNormal();
        } catch (Exception e) {
            System.err.println("Please make sure the model files are in the correct directory.");
        }
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.FIRST_PERSON_MAP || type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GlStateManager.enableLighting();
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etrust);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0.5f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.35f, 0.35f, 0.35f);
        if (type != ItemRenderType.EQUIPPED_FIRST_PERSON) GlStateManager.scale(-1, 1, 1);
        GlStateManager.translate(0.2f, -0.55f, 0.1f);

        model.render();
        GlStateManager.pushMatrix();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GlStateManager.disableLighting();
        model.render();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}
