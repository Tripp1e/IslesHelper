package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.features.bossrush.Frog;
import com.tripp1e.isleshelper.features.bossrush.General;
import com.tripp1e.isleshelper.config.ConfigManager;
import com.tripp1e.isleshelper.mixin.accessor.TextDisplayEntityAccessor;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.entity.decoration.DisplayEntity;

public class Events {

    public static void init() {

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (Utils.getNull()) return;

            //General
            if (ConfigManager.get().bossRush.onlyPartyChatsEnabled) General.onlyPartyMessages();
            if (ConfigManager.get().bossRush.timerEnabled) General.timer(Utils.drawContext);

            //Frog
            if (Utils.getBoss().equals("frog")) {
                if (ConfigManager.get().bossRush.frogStomachWarningEnabled) Frog.stomachExplodeWarn();
            }

        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (Utils.getNull()) return;
            Utils.drawContext = drawContext;
        });

        ClientEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (Utils.getNull()) return;
            if (entity instanceof DisplayEntity.TextDisplayEntity textEntity) {
                //IslesHelperClient.LOGGER.info("TextLines: " + (TextDisplayEntityAccessor)textEntity.invokeGetText());
            }

            if (ConfigManager.get().bossRush.teammateDeathMessageEnabled) General.teamDeathNotify(entity);
        }));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Utils.getNull()) return;


        });

    }

}
