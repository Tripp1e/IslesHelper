package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IslesHelperClient implements ClientModInitializer {
    public static String MOD_ID = "isleshelper";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Jesser where is the cocainer");
        ConfigManager.init();

        Events.hudRenderCallback();
        Events.startClientTick();
    }

}