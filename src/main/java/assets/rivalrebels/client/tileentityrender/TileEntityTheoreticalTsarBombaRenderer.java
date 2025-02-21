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

import assets.rivalrebels.client.model.ModelTheoreticalTsarBomba;
import assets.rivalrebels.common.tileentity.TileEntityTheoreticalTsarBomba;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityTheoreticalTsarBombaRenderer extends TileEntitySpecialRenderer {
    private final ModelTheoreticalTsarBomba model;

    public TileEntityTheoreticalTsarBombaRenderer() {
        model = new ModelTheoreticalTsarBomba();
    }

    public void renderAModelAt(TileEntityTheoreticalTsarBomba tile, double d, double d1, double d2, float f) {
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) d + 0.5F, (float) d1 + 1F, (float) d2 + 0.5F);
        GlStateManager.scale(1.3f, 1.3f, 1.3f);
        int metadata = tile.getBlockMetadata();

        if (metadata == 2) {
            GlStateManager.rotate(180, 0, 1, 0);
            GlStateManager.rotate(90, 1, 0, 0);
        }

        if (metadata == 3) {
            GlStateManager.rotate(90, 1, 0, 0);
        }

        if (metadata == 4) {
            GlStateManager.rotate(-90, 0, 1, 0);
            GlStateManager.rotate(90, 1, 0, 0);
        }

        if (metadata == 5) {
            GlStateManager.rotate(90, 0, 1, 0);
            GlStateManager.rotate(90, 1, 0, 0);
        }
        model.render();
        GlStateManager.popMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f) {
        renderAModelAt((TileEntityTheoreticalTsarBomba) tileentity, d, d1, d2, f);
    }
}
