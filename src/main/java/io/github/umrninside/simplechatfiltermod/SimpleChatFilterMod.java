package io.github.umrninside.simplechatfiltermod;

import io.github.umrninside.simplechatfiltermod.handler.ChatIoHandler;
import io.github.umrninside.simplechatfiltermod.util.AutomatonBuilder;
import io.github.umrninside.simplechatfiltermod.util.FileReader;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

@Mod(modid = SimpleChatFilterMod.MODID, name = SimpleChatFilterMod.NAME, version = SimpleChatFilterMod.VERSION)
public class SimpleChatFilterMod
{
    public static final String MODID = "simplechatfiltermod";
    public static final String NAME = "Simple Chat Filter Mod";
    public static final String VERSION = "0.1";
    
    public ChatIoHandler chatIoHandler;

    private static Logger logger;
    private AutomatonBuilder automatonBuilder = new AutomatonBuilder();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("Init SimpleChatFilter");
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        chatIoHandler = new ChatIoHandler(logger);
        chatIoHandler.reloadKeywords();
        MinecraftForge.EVENT_BUS.register(chatIoHandler);
    }
}
