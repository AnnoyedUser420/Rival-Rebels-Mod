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
package assets.rivalrebels.common.item;

import assets.rivalrebels.RivalRebels;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.HashSet;

public class ItemRod extends ItemTool {
    public int power;

    public ItemRod() {
        super(1, ToolMaterial.EMERALD, new HashSet());
        maxStackSize = 1;
        this.setMaxDamage(32);
        setCreativeTab(RivalRebels.rralltab);
    }

    @Override
    public boolean isItemTool(ItemStack is) {
        return true;
    }

    @Override
    public boolean isDamageable() {
        return true;
    }
}