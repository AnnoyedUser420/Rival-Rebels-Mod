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

public class FlamethrowerRenderer implements IItemRenderer {
    ModelFromObj ft;

    public FlamethrowerRenderer() {
        try {
            ft = ModelFromObj.readObjFile("n.obj");
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
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etflamethrower);
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0.7f, 0.1f, 00f);
        GlStateManager.rotate(270, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.18f, 0.18f, 0.18f);
        // GlStateManager.translate(0.3f, 0.05f, -0.1f);

        ft.render();
        if (item.isItemEnchanted()) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GlStateManager.disableLighting();
            ft.render();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }

        GlStateManager.popMatrix();
    }
}
