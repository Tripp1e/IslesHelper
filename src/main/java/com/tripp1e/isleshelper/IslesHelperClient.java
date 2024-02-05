package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.bossrush.Frog;
import com.tripp1e.isleshelper.bossrush.General;
import com.tripp1e.isleshelper.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IslesHelperClient implements ClientModInitializer {
    public static String MOD_ID = "isleshelper";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitializeClient() {
        LOGGER.info("Jesser where is the cocainer");
        tick();
        ConfigManager.init();
    }


    //Runs every tick
    private void tick() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.player != null && client.world != null && client.world.isClient) {
                //General
                if (ConfigManager.get().general.generalTeammateDeathMessage) General.teamDeathNotify();
                if (ConfigManager.get().general.generalOnlyPartyChats) General.onlyPartyMessages();

                //Frog
                if (client.world.getRegistryKey().getValue().toString().contains("frog")) {
                    if (ConfigManager.get().general.frogStomachWarning) Frog.stomachExplodeWarn();
                }
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ConfigManager.get().general.generalOnlyPartyChats) General.onlyPartyMessages();
        });
    }

}