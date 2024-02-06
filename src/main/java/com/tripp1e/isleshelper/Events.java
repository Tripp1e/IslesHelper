package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.features.bossrush.Frog;
import com.tripp1e.isleshelper.features.bossrush.General;
import com.tripp1e.isleshelper.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class Events {

    public static void init() {

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (Utils.getWorld() == null) return;

            //General
            if (ConfigManager.get().bossRush.onlyPartyChatsEnabled) General.onlyPartyMessages();
            if (ConfigManager.get().bossRush.timerEnabled) General.timer(null);

            //Frog
            if (Utils.getBoss().equals("frog")) {
                if (ConfigManager.get().bossRush.frogStomachWarningEnabled) Frog.stomachExplodeWarn();
            }

        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (Utils.getWorld() == null) return;
            Utils.drawContext = drawContext;
            if (ConfigManager.get().bossRush.timerEnabled) General.timer(drawContext);
        });

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (Utils.getWorld() == null) return;
            IslesHelperClient.LOGGER.info("Something loaded: " + entity);

            if (ConfigManager.get().bossRush.teammateDeathMessageEnabled) General.teamDeathNotify(entity);
            General.timer(Utils.drawContext);

        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;



        });

    }

}
