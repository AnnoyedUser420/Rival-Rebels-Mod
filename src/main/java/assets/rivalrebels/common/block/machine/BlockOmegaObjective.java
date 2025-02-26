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

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.tileentity.TileEntityOmegaObjective;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockOmegaObjective extends BlockContainer {
    @SideOnly(Side.CLIENT)
    IIcon icon;

    public BlockOmegaObjective() {
        super(Material.iron);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (x != RivalRebels.round.oObjx || y != RivalRebels.round.oObjy || z != RivalRebels.round.oObjz) {
            world.setBlock(RivalRebels.round.oObjx, RivalRebels.round.oObjy, RivalRebels.round.oObjz, RivalRebels.plasmaexplosion);
            RivalRebels.round.oObjx = x;
            RivalRebels.round.oObjy = y;
            RivalRebels.round.oObjz = z;
            if (world.getBlock(RivalRebels.round.sObjx, RivalRebels.round.sObjy, RivalRebels.round.sObjz) == RivalRebels.sigmaobj)
                RivalRebels.round.roundManualStart();
        }
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
        super.breakBlock(world, x, y, z, par5, par6);
        if (world.getBlock(x, y, z) != RivalRebels.plasmaexplosion) {
            world.setBlock(x, y, z, this);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two adjacent blocks and also whether the player can attach torches, redstone wire,
     * etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return -1;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        RivalRebelsSoundPlayer.playSound(par1World, 10, 3, par2, par3, par4);

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int var) {
        return new TileEntityOmegaObjective();
    }

    @Override
    public final IIcon getIcon(int side, int meta) {
        return icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconregister) {
        icon = iconregister.registerIcon("RivalRebels:ba");
    }
}
