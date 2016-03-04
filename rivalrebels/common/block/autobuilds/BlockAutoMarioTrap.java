package assets.rivalrebels.common.block.autobuilds;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAutoMarioTrap extends BlockAutoTemplate
{
	public BlockAutoMarioTrap()
	{
		super();
	}
	
	@Override
	public void build(World world, int x, int y, int z)
	{
		super.build(world, x, y, z);
		if (!world.isRemote)
		{
			placeBlockCarefully(world, x, y, z, Blocks.air);
			int r = 2;
			for (int z1 = -r; z1 <= r; z1++)
			{
				for (int x1 = -r; x1 <= r; x1++)
				{
					placeBlockCarefully(world, x + x1, y - 1, z + z1, RivalRebels.amario);
					placeBlockCarefully(world, x + x1, y - 2, z + z1, Blocks.air);
					placeBlockCarefully(world, x + x1, y - 3, z + z1, Blocks.air);
				}
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	IIcon	icon1;
	@SideOnly(Side.CLIENT)
	IIcon	icon2;
	@SideOnly(Side.CLIENT)
	IIcon	icon3;
	@SideOnly(Side.CLIENT)
	IIcon	icon4;
	@SideOnly(Side.CLIENT)
	IIcon	icon5;
	@SideOnly(Side.CLIENT)
	IIcon	icon6;
	
	@SideOnly(Side.CLIENT)
	@Override
	public final IIcon getIcon(int side, int meta)
	{
		if (side == 0) return icon1;
		if (side == 1) return icon2;
		if (side == 2) return icon3;
		if (side == 3) return icon4;
		if (side == 4) return icon5;
		if (side == 5) return icon6;
		return icon1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconregister)
	{
		icon1 = iconregister.registerIcon("RivalRebels:dh"); // BOTTOM
		icon2 = iconregister.registerIcon("RivalRebels:dh"); // TOP
		icon3 = iconregister.registerIcon("RivalRebels:de"); // SIDE N
		icon4 = iconregister.registerIcon("RivalRebels:de"); // SIDE S
		icon5 = iconregister.registerIcon("RivalRebels:de"); // SIDE W
		icon6 = iconregister.registerIcon("RivalRebels:de"); // SIDE E
	}
}