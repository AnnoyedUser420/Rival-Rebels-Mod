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
package assets.rivalrebels.common.block.trap;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.tileentity.TileEntityNuclearBomb;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockNuclearBomb extends BlockContainer {
    public int orientation;
    @SideOnly(Side.CLIENT)
    IIcon icon;

    public BlockNuclearBomb() {
        super(Material.iron);
    }

    public static int determineOrientation(World world, int x, int y, int z, EntityPlayer entity) {
        if (MathHelper.abs((float) entity.posX - x) < 2.0F && MathHelper.abs((float) entity.posZ - z) < 2.0F) {
            double var5 = entity.posY + 1.82D - entity.yOffset;

            if (var5 - y > 2.0D) {
                return 1;
            }

            if (y - var5 > 0.0D) {
                return 0;
            }
        }

        int var7 = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0)));
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack i) {
        if (MathHelper.abs((float) entity.posX - x) < 2.0F && MathHelper.abs((float) entity.posZ - z) < 2.0F) {
            double var5 = entity.posY + 1.82D - entity.yOffset;

            if (var5 - y > 2.0D) {
                world.setBlockMetadataWithNotify(x, y, z, 1, 0);
                return;
            }

            if (y - var5 > 0.0D) {
                world.setBlockMetadataWithNotify(x, y, z, 0, 0);
                return;
            }
        }
        int var7 = MathHelper.floor_double((entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 0))), 0);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (!par5EntityPlayer.isSneaking()) {
            if (par5EntityPlayer.inventory.getCurrentItem() != null && par5EntityPlayer.inventory.getCurrentItem().getItem() == RivalRebels.pliers) {
                FMLNetworkHandler.openGui(par5EntityPlayer, RivalRebels.instance, 0, par1World, x, y, z);
                // par5EntityPlayer.openGui(RivalRebels.instance, RivalRebels.nuclearBombGuiID, par1World, x, y, z);
            } else if (!par1World.isRemote) {
                par5EntityPlayer.addChatMessage(new ChatComponentText("§7[§4Orders§7] §cUse pliers to open."));
            }
        }
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    /**
     * each class overrides this to return a new <className>
     */
    @Override
    public TileEntity createNewTileEntity(World par1World, int var) {
        return new TileEntityNuclearBomb();
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
