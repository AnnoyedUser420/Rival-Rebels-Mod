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
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class EmptyRodRenderer implements IItemRenderer {
    ModelRod md;

    public EmptyRodRenderer() {
        md = new ModelRod();
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
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etemptyrod);
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate(0.5f, 0.5f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.scale(0.5f, 1.25f, 0.5f);
        GlStateManager.pushMatrix();

        md.render();

        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
}
