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
package assets.rivalrebels.common.block;

import assets.rivalrebels.common.tileentity.TileEntityPlasmaExplosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockPlasmaExplosion extends BlockContainer {
    @SideOnly(Side.CLIENT)
    IIcon icon;

    public BlockPlasmaExplosion() {
        super(Material.portal);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var) {
        return new TileEntityPlasmaExplosion();
    }

    @Override
    public final IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        icon = iconregister.registerIcon("RivalRebels:ak");
    }
}