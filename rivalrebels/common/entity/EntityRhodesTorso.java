package assets.rivalrebels.common.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.Explosion;
import assets.rivalrebels.common.tileentity.TileEntityLaptop;

public class EntityRhodesTorso extends EntityRhodesPiece
{
	public EntityRhodesTorso(World w)
	{
		super(w);
	}
	
	public EntityRhodesTorso(World w, double x, double y, double z)
	{
		super(w, x, y, z);
		health = 2000;
	}

	@Override
	public int getMaxAge()
	{
		return 1500;
	}
}