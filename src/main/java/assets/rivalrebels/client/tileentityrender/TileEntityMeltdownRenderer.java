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
package assets.rivalrebels.client.tileentityrender;

import assets.rivalrebels.client.model.ModelBlastSphere;
import assets.rivalrebels.common.tileentity.TileEntityMeltDown;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityMeltdownRenderer extends TileEntitySpecialRenderer {
    ModelBlastSphere model;

    public TileEntityMeltdownRenderer() {
        model = new ModelBlastSphere();
    }

    public void renderAModelAt(TileEntityMeltDown tile, double d, double d1, double d2, float f) {
        float fsize = (float) Math.sin(tile.size);
        if (fsize <= 0) return;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) d + 0.5F, (float) d1 + 0.5F, (float) d2 + 0.5F);
        GlStateManager.pushMatrix();
        GlStateManager.rotate(tile.size * 50, 0f, 1, 0f);

        model.renderModel(fsize * 5.5f, 1, 1, 1, 0.4f);

        GlStateManager.rotate(tile.size * 50, 0f, 1, 0f);

        model.renderModel(fsize * 5.6f, 1, 1, 1, 0.4f);

        GlStateManager.popMatrix();

        model.renderModel(fsize * 5.9f, 1, 1, 1, 0.4f);

        GlStateManager.popMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        renderAModelAt((TileEntityMeltDown) tileentity, d, d1, d2, f);
    }
}
