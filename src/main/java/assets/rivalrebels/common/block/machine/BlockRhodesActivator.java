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
package assets.rivalrebels.common.block.machine;

import assets.rivalrebels.common.tileentity.TileEntityRhodesActivator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRhodesActivator extends BlockContainer {
    @SideOnly(Side.CLIENT)
    IIcon icon;
    @SideOnly(Side.CLIENT)
    IIcon icontop;

    public BlockRhodesActivator() {
        super(Material.iron);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var) {
        return new TileEntityRhodesActivator();
    }

    @Override
    public final IIcon getIcon(int side, int meta) {
        if (side == 0 || side == 1) return icontop;
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        icon = iconregister.registerIcon("RivalRebels:ci");
        icontop = iconregister.registerIcon("RivalRebels:ch");
    }
}