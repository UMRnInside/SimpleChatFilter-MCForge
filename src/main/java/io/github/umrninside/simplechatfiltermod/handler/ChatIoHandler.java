package io.github.umrninside.simplechatfiltermod.handler;

import io.github.umrninside.simplechatfiltermod.SimpleChatFilterMod;
import io.github.umrninside.simplechatfiltermod.util.AutomatonBuilder;
import io.github.umrninside.simplechatfiltermod.util.FileReader;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ChatIoHandler {
    // LivingCityTools Compatible
    public static final String keywordFilePath = "ignoreList.citytools";
    public static final String COMMAND = "/simplechatfilter";
    
    private AutomatonBuilder builder;
    private Logger logger;
    public ChatIoHandler(Logger logger) {
        builder = new AutomatonBuilder();
        this.logger = logger;
    }
    
    public void setBuilder(AutomatonBuilder builder) {
        this.builder = builder;
    }
    
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String messageText = event.getMessage().getUnformattedText();
        // logger.debug("Got ClientChatReceivedEvent");
        if (builder.hasMatchedAny(messageText)) {
            event.setCanceled(true);
            logger.debug("Censored content: " + messageText);
        }
    }
    
    // Handle reload commands
    @SubscribeEvent
    public void chatMessage(ClientChatEvent event) {
        String message = event.getMessage();
        if (!message.startsWith(COMMAND)) return;
        message = message.substring(COMMAND.length());
        if (message.equals(" reload")) {
            logger.warn("Reloading keywords...");
            reloadKeywords();
            event.setCanceled(true);
        } else {
            logger.warn("Unknowm command " + message);
        }
    }
    
    public void reloadKeywords() {
        ArrayList<String> strings = FileReader.readFile(keywordFilePath);
        logger.debug("Read " + strings.size() + " strings.");
        builder.rebuildTrie(strings);
    }
}
