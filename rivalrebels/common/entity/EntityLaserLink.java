package assets.rivalrebels.common.entity;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityLaserLink extends EntityInanimate
{
	public Random		rand		= new Random();
	public Long			randLong	= rand.nextLong();
	public int			ticksExisted;
	
	public EntityPlayer	shooter;
	
	public EntityLaserLink(World par1World)
	{
		super(par1World);
		ignoreFrustumCheck = true;
	}
	
	public EntityLaserLink(World par1World, EntityPlayer player, double distance)
	{
		this(par1World);
		shooter = player;
		ticksExisted = 0;
		motionX = distance / 100f;
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		posY -= 0.08;
		posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.2F);
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		setSize(0.5F, 0.5F);
	}
	
	public EntityLaserLink(World par1World, double x, double y, double z, float yaw, float pitch, double distance)
	{
		this(par1World);
		setLocationAndAngles(x, y, z, yaw, pitch);
		motionX = distance/100f;
		ticksExisted = 0;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		setSize(0.5F, 0.5F);
	}
	
	@Override
	public boolean isInRangeToRenderDist(double par1)
	{
		return true;
	}
	
	@Override
	public int getBrightnessForRender(float par1)
	{
		return 1000;
	}
	
	@Override
	public float getBrightness(float par1)
	{
		return 1000F;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (ticksExisted == 1) setDead();
		ticksExisted++;
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound var1)
	{
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound var1)
	{
	}
}

// package RivalRebels.common;
//
// import java.util.Random;
//
// import net.minecraft.entity.Entity;
// import net.minecraft.entity.player.EntityPlayer;
// import net.minecraft.util.MathHelper;
// import net.minecraft.nbt.NBTTagCompound;
// import net.minecraft.util.Vec3;
// import net.minecraft.world.World;
//
// public class EntityLightningLink extends Entity
// {
// public Random rand = new Random();
// public Long randLong = rand.nextLong();
// public int ticksExisted;
//
// public EntityLightningLink(World par1World)
// {
// super(par1World);
// }
//
// public EntityLightningLink(World par1World, EntityPlayer player, double distance, double segmentdistance)
// {
// super(par1World);
// ticksExisted = 0;
// motionY = distance;
// motionX = segmentdistance;
// setLocationAndAngles(player.posX, player.posY + player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
// posX -= (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
// posY -= 0.15060000149011612D;
// posZ -= (MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F);
// yOffset = 0.0F;
// setSize(0.5F, 0.5F);
// }
//
// public EntityLightningLink(World par1World, double x, double y, double z, float yaw, float pitch, double distance, double segmentdistance)
// {
// super(par1World);
// setLocationAndAngles(x, y, z, yaw, pitch);
// motionY = distance;
// motionX = segmentdistance;
// ticksExisted = 0;
// yOffset = 0.0F;
// setSize(0.5F, 0.5F);
// }
//
// @Override
// public boolean isInRangeToRenderVec3D(Vec3 par1Vec3)
// {
// return true;
// }
//
// @Override
// public boolean isInRangeToRenderDist(double par1)
// {
// return true;
// }
//
// @Override
// protected void entityInit()
// {
// }
//
// @Override
// public void onUpdate()
// {
// setDead();
// }
//
// @Override
// protected void readEntityFromNBT(NBTTagCompound var1)
// {
// }
//
// @Override
// protected void writeEntityToNBT(NBTTagCompound var1)
// {
// }
// }
