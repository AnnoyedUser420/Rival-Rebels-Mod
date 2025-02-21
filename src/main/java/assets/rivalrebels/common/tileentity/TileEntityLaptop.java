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
package assets.rivalrebels.common.tileentity;

import assets.rivalrebels.RivalRebels;
import assets.rivalrebels.common.item.weapon.ItemBinoculars;
import assets.rivalrebels.common.packet.LaptopRefreshPacket;
import assets.rivalrebels.common.packet.PacketDispatcher;
import assets.rivalrebels.common.round.RivalRebelsTeam;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

import java.util.Iterator;
import java.util.List;

public class TileEntityLaptop extends TileEntity implements IInventory, ITickable {
    public String username = null;
    public RivalRebelsTeam rrteam = null;
    public double slide = 0;
    public int b2spirit = 0;
    public int b2carpet = 0;
    public int numUsingPlayers;
    double test = Math.PI;
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(14, ItemStack.EMPTY);
    private int ticksSinceSync;
    private boolean listed;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return 14;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.chestContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.chestContents.get(index);
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (!this.chestContents.get(index).isEmpty()) {
            ItemStack stack;

            if (this.chestContents.get(index).getCount() <= count) {
                stack = this.chestContents.get(index);
                this.chestContents.set(index, ItemStack.EMPTY);
                return stack;
            } else {
                stack = this.chestContents.get(index).splitStack(count);

                if (this.chestContents.get(index).getCount() == 0) {
                    this.chestContents.set(index, ItemStack.EMPTY);
                }

                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem - like when you close a workbench GUI.
     */
    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack stack = this.chestContents.get(index);
        if (!stack.isEmpty()) {
            this.chestContents.set(index, ItemStack.EMPTY);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.chestContents.set(index, stack);

        if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        markDirty();
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
            int slot = tag.getByte("Slot") & 255;

            if (slot < this.chestContents.size()) {
                this.chestContents.set(slot, new ItemStack(tag));
            }
        }

        b2spirit = nbt.getInteger("b2spirit");
        b2carpet = nbt.getInteger("b2carpet");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        NBTTagList items = new NBTTagList();

        for (int i = 0; i < this.chestContents.size(); ++i) {
            if (!this.chestContents.get(i).isEmpty()) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                this.chestContents.get(i).writeToNBT(tag);
                items.appendTag(tag);
            }
        }

        nbt.setTag("Items", items);

        nbt.setInteger("b2spirit", b2spirit);
        nbt.setInteger("b2carpet", b2carpet);
        return nbt;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(getPos()) == this && player.getDistanceSq(this.getPos().getX() + 0.5D, this.getPos().getY() + 0.5D, this.getPos().getZ() + 0.5D) <= 64.0D;
    }

    public void onGoButtonPressed() {
        if (isReady()) {
            for (int j = 0; j < 3; j++) {
                if (getStackInSlot(j + 6) != null && getStackInSlot(j + 11) != null) {
                    if (getStackInSlot(j + 6).getItem() == RivalRebels.nuclearelement
                            && getStackInSlot(j + 11).getItem() == RivalRebels.hydrod) {
                        b2spirit++;
                        setInventorySlotContents(j + 6, null);
                        setInventorySlotContents(j + 11, null);
                    } else if (getStackInSlot(j + 6).getItem() == Item.getItemFromBlock(RivalRebels.timedbomb)
                            && getStackInSlot(j + 11).getItem() == Item.getItemFromBlock(RivalRebels.timedbomb)) {
                        b2carpet++;
                        setInventorySlotContents(j + 6, null);
                        setInventorySlotContents(j + 11, null);
                    }
                }
            }
            setInventorySlotContents(4, null);
            setInventorySlotContents(5, null);
            setInventorySlotContents(9, null);
            setInventorySlotContents(10, null);
        }
        if (RivalRebels.freeb83nukes) {
            b2spirit += 10;
            b2carpet += 10;
        }
        refreshTasks();
    }

    public boolean hasChips() {
        boolean r = true;
        rrteam = RivalRebelsTeam.NONE;
        for (int j = 0; j < 4; j++) {
            if (getStackInSlot(j) == null) r = false;
            else {
                if (getStackInSlot(j).getTagCompound() == null)
                    getStackInSlot(j).setTagCompound(new NBTTagCompound());
                if (rrteam == RivalRebelsTeam.NONE)
                    rrteam = RivalRebelsTeam.getForID(getStackInSlot(j).getTagCompound().getInteger("team"));
                else if (rrteam != RivalRebelsTeam.getForID(getStackInSlot(j).getTagCompound().getInteger("team")))
                    r = false;
            }
        }
        return r;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count ticks and creates a new spawn inside its implementation.
     */
    @Override
    public void update() {
        ++this.ticksSinceSync;

        slide = (Math.cos(test) + 1) * 45;

        //if (!listed)
        //{
        ItemBinoculars.add(this);
        //	listed = true;
        //}
        List players = world.playerEntities;
        Iterator iter = players.iterator();
        boolean i = false;
        while (iter.hasNext()) {
            EntityPlayer player = (EntityPlayer) iter.next();
            if (player.getDistanceSq(getPos().getX() + 0.5f, getPos().getY() + 0.5f, getPos().getZ() + 0.5f) <= 9) {
                i = true;
            }
        }
        if (i) {
            if (slide < 89.995) test += 0.05;
        } else {
            if (slide > 0.004) test -= 0.05;
        }

        if (b2spirit > 0 && !hasChips()) {
            b2spirit--;
            refreshTasks();
        }
    }

    /**
     * Called when a client event is received with the event number and argument, see World.sendClientEvent
     *
     * @return
     */
    @Override
    public boolean receiveClientEvent(int par1, int par2) {
        if (par1 == 1) {
            this.numUsingPlayers = par2;
            return true;
        }
        return false;
    }

    /**
     * invalidates a tile entity
     */
    @Override
    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
        ItemBinoculars.remove(this);
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        this.chestContents.clear();
    }

    @Override
    public String getName() {
        return "Laptop";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    public void refreshTasks() {
        PacketDispatcher.packetsys.sendToAll(new LaptopRefreshPacket(getPos(), b2spirit, b2carpet));
    }

    public boolean isReady() {
        return hasChips()
                && getStackInSlot(4) != null
                && getStackInSlot(5) != null
                && getStackInSlot(9) != null
                && getStackInSlot(10) != null;
    }
}
