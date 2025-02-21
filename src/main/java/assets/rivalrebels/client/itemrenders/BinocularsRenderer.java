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
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class BinocularsRenderer implements IItemRenderer {
    ModelFromObj model;

    public BinocularsRenderer() {
        try {
            model = ModelFromObj.readObjFile("b.obj");
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
        Minecraft.getMinecraft().renderEngine.bindTexture(RivalRebels.etbinoculars);
        GlStateManager.enableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0.5f, -0.03f);
        GlStateManager.rotate(35, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(90, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(0.35f, 0.35f, 0.35f);
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON && (Mouse.isButtonDown(1))) {
            GlStateManager.popMatrix();
            return;
        }
        GlStateManager.translate(0.6f, 0.05f, 0.3f);

        if (model.name.isEmpty())
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§43D ERROR:§f Cannot find RivalRebels.zip or RivalRebels folder inside mods folder. Please make sure the RivalRebels mod file is named RivalRebels.zip, or visit §2www.rivalrebels.com §ffor support."));
        model.render();

        GlStateManager.popMatrix();
    }
}
