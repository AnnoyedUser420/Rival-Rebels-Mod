package assets.rivalrebels.common.block.machine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBreadBox extends Block {

    public BlockBreadBox() {
        super(Material.IRON);
    }

    @Override
    public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
        blockActivated(world, pos.getX(), pos.getY(), pos.getZ(), player);
    }

    public boolean blockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if (!player.isSneaking()) {
            EntityItem ei = new EntityItem(world, x + .5, y + 1, z + .5, new ItemStack(Items.BREAD, 1));
            if (!world.isRemote) {
                world.spawnEntity(ei);
                if (world.rand.nextInt(64) == 0)
                {
                    player.sendMessage(new TextComponentString("§7[§4Orders§7] §cShift-click (Sneak) to pack up toaster."));
                }
//                    (new ChatComponentText("§7[§4Orders§7] §cShift-click (Sneak) to pack up toaster."));
            }
        } else {
            world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
            EntityItem ei = new EntityItem(world, x + .5, y + .5, z + .5, new ItemStack(this));
            if (!world.isRemote) {
                world.spawnEntity(ei);
            }
        }
        return true;
    }
}
