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
import net.minecraft.client.renderer.texture.IIconRegister;

public class ItemCoreCopper extends ItemCore {
    public ItemCoreCopper() {
        super();
        maxStackSize = 1;
        timemult = 0.25f;
        setCreativeTab(RivalRebels.rralltab);
    }

    @Override
    public void registerIcons(IIconRegister iconregister) {
        itemIcon = iconregister.registerIcon("RivalRebels:ay");
    }
}