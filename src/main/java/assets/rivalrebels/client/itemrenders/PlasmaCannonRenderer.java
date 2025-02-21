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
import assets.rivalrebels.client.model.ModelRod;
import assets.rivalrebels.client.objfileloader.ModelFromObj;
import assets.rivalrebels.client.tileentityrender.TileEntityForceFieldNodeRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class PlasmaCannonRenderer implements IItemRenderer {
    // ModelPlasmaCannon md;
    ModelRod md2;
    ModelRod md3;

    ModelFromObj model;

    public PlasmaCannonRenderer() {
        // md = new ModelPlasmaCannon();
        md2 = new ModelRod();
        md2.rendersecondcap = false;
        md3 = new ModelRod();
        try {
            model = ModelFromObj.readObjFile("m.obj");
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
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.1f, 0f, 0f);
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etplasmacannon);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0.2f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(0.03125f, 0.03125f, 0.03125f);
        GlStateManager.pushMatrix();

        model.render();
        if (item.getEnchantmentTagList() != null) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GlStateManager.disableLighting();
            model.render();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }

        GlStateManager.popMatrix();
        GlStateManager.popMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.ethydrod);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0.2f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.rotate(225, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.5f, 0.5f, 0.0f);
        GlStateManager.scale(0.25f, 0.5f, 0.25f);
        md2.render();
        if (item.getEnchantmentTagList() != null) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GlStateManager.disableLighting();
            md2.render();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0.2f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.rotate(247.5f, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.175f, 0.1f, 0.0f);
        GlStateManager.scale(0.25f, 0.5f, 0.25f);
        md3.render();
        if (item.getEnchantmentTagList() != null) {
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, TileEntityForceFieldNodeRenderer.id[(int) ((TileEntityForceFieldNodeRenderer.getTime() / 100) % TileEntityForceFieldNodeRenderer.frames)]);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GlStateManager.disableLighting();
            md3.render();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
        }
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
}
