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
package assets.rivalrebels.common.entity;

import assets.rivalrebels.common.core.FileRW;
import assets.rivalrebels.common.core.RivalRebelsDamageSource;
import assets.rivalrebels.common.core.RivalRebelsSoundPlayer;
import assets.rivalrebels.common.explosion.Explosion;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EntityB2Frag extends EntityInanimate {
    public int type = 0;
    public int health;
    protected boolean inGround;
    float motionyaw = 0;
    float motionpitch = 0;
    float offset = 0;
    double size = 0;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile;
    private int ticksInGround;
    private int ticksInAir;
    private boolean isSliding = false;

    public EntityB2Frag(World par1World) {
        super(par1World);
        health = 300;
        boundingBox.setBounds(-2.5, -2.5, -2.5, 2.5, 2.5, 2.5);
        setSize(7.5F, 7.5F);
        ignoreFrustumCheck = true;
    }

    public EntityB2Frag(World par1World, Entity toBeGibbed, int Type) {
        super(par1World);
        health = 300;
        boundingBox.setBounds(-2.5, -2.5, -2.5, 2.5, 2.5, 2.5);
        setSize(7.5F, 7.5F);
        ignoreFrustumCheck = true;

        isSliding = false;
        type = Type;

        motionyaw = (float) ((Math.random() - 0.5) * 35);
        motionpitch = (float) ((Math.random() - 0.5) * 25);

        setLocationAndAngles(toBeGibbed.posX, toBeGibbed.posY, toBeGibbed.posZ, toBeGibbed.rotationYaw, toBeGibbed.rotationPitch);

        double ox = posX;
        double oz = posZ;

        if (Type == 1) {
            posX -= (MathHelper.cos(((-rotationYaw) / 180.0F) * (float) Math.PI) * 7.5F);
            posZ -= (MathHelper.sin(((-rotationYaw) / 180.0F) * (float) Math.PI) * 7.5F);
        } else if (Type == 0) {
            posX -= (MathHelper.cos(((-rotationYaw + 180) / 180.0F) * (float) Math.PI) * 7.5F);
            posZ -= (MathHelper.sin(((-rotationYaw + 180) / 180.0F) * (float) Math.PI) * 7.5F);
        }

        setPosition(posX, posY, posZ);

        motionX = toBeGibbed.motionX;
        motionY = toBeGibbed.motionY;
        motionZ = toBeGibbed.motionZ;

        motionX += (-ox + posX) * 0.1;
        motionZ += (-oz + posZ) * 0.1;

        setFire(10);
    }

    public static byte[] getBytesString(String str) {
        return FileRW.getBytesString(str);
    }

    @Override
    public AxisAlignedBB getCollisionBox(Entity par1Entity) {
        return par1Entity.boundingBox;
    }

    @Override
    public AxisAlignedBB getBoundingBox() {
        return boundingBox;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (ticksInAir == 0) {
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            if (side == Side.SERVER) {
                Iterator iter = worldObj.playerEntities.iterator();
                while (iter.hasNext()) {
                    EntityPlayer player = (EntityPlayer) iter.next();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(9);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try {
                        outputStream.writeInt(17);
                        outputStream.writeInt(getEntityId());
                        outputStream.writeByte(type);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (outputStream != null) outputStream.close();
                        } catch (IOException error) {

                        }
                    }
                    //Packet250CustomPayload packet = new Packet250CustomPayload();
                    //packet.data = bos.toByteArray();
                    //packet.length = bos.size();
                    //PacketDispatcher.sendPacketToPlayer(packet, player);
                }
            }
        }

        ++ticksInAir;

        lastTickPosX = posX;
        lastTickPosY = posY;
        lastTickPosZ = posZ;
        super.onUpdate();

        if (inGround) {
            ++ticksInGround;

            if (ticksInGround == 1200) {
                setDead();
            }

            inGround = false;
            motionX *= (rand.nextFloat() * 0.2F);
            motionY *= (rand.nextFloat() * 0.2F);
            motionZ *= (rand.nextFloat() * 0.2F);
            ticksInGround = 0;
            ticksInAir = 0;
        }

        Vec3 vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        Vec3 vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = Vec3.createVectorHelper(posX, posY, posZ);
        vec31 = Vec3.createVectorHelper(posX + motionX, posY + motionY, posZ + motionZ);

        if (movingobjectposition != null) {
            isSliding = true;
            posY = movingobjectposition.hitVec.yCoord + offset;
        }

        if (!worldObj.isRemote) {
            List var5 = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
            Iterator var8 = var5.iterator();

            while (var8.hasNext()) {
                Entity var9 = (Entity) var8.next();

                if (var9 instanceof EntityRocket) {
                    ((EntityRocket) var9).explode(null);
                }

                if (var9 instanceof EntityPlasmoid) {
                    ((EntityPlasmoid) var9).explode();
                }

                if (var9 instanceof EntityLaserBurst) {
                    var9.setDead();
                    attackEntityFrom(DamageSource.generic, 6);
                }
            }
        }

        rotationPitch += motionpitch;
        rotationYaw += motionyaw;
        posX += motionX;
        posY += motionY;
        posZ += motionZ;

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.5F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.5F;

        float f2 = 0.99F;
        float f3 = 0.05F;

        if (isSliding) {
            motionpitch = 0;
            motionyaw = 0;
            motionY = 0;
            f2 = 0.7f;
            f3 = 0.0f;
        }

        motionpitch *= (double) f2;
        motionyaw *= (double) f2;
        motionX *= f2;
        motionY *= f2;
        motionZ *= f2;
        motionY -= f3;

        setPosition(posX, posY, posZ);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short) xTile);
        par1NBTTagCompound.setShort("yTile", (short) yTile);
        par1NBTTagCompound.setShort("zTile", (short) zTile);
        par1NBTTagCompound.setByte("inTile", (byte) inTile);
        par1NBTTagCompound.setByte("Type", (byte) type);
        par1NBTTagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        xTile = par1NBTTagCompound.getShort("xTile");
        yTile = par1NBTTagCompound.getShort("yTile");
        zTile = par1NBTTagCompound.getShort("zTile");
        inTile = par1NBTTagCompound.getByte("inTile") & 255;
        type = par1NBTTagCompound.getByte("Type") & 255;
        inGround = par1NBTTagCompound.getByte("inGround") == 1;
    }

    @Override
    public boolean isInRangeToRenderDist(double par1) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        if (!isDead) {
            health -= par2;

            if (health <= 0) {
                setDead();
                new Explosion(worldObj, posX, posY, posZ, 6, true, true, RivalRebelsDamageSource.rocket);
                RivalRebelsSoundPlayer.playSound(this, 0, 0, 30, 1);
            }
        }

        return true;
    }

    @Override
    protected void entityInit() {
    }
}
