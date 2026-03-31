package com.example.examplemod;

import assets.rivalrebels.common.block.machine.BlockBreadBox;
import com.example.examplemod.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class ExampleMod
{

    @Mod.Instance
    public static ExampleMod instance;

    @SidedProxy(clientSide = "com.example.examplemod.proxies.ClientProxy", serverSide = "com.example.examplemod.proxies.CommonProxy")
    public static CommonProxy proxy;

    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    private static Logger logger;

    public static final BlockBreadBox BLOCK_BREAD_BOX = new BlockBreadBox();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        BLOCK_BREAD_BOX.setRegistryName("bread_box");
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        logger.error("Registering");
        event.getRegistry().registerAll(BLOCK_BREAD_BOX);
    }


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemBlock(BLOCK_BREAD_BOX).setRegistryName("bread_box"));
    }
}
