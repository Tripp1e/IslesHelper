package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.features.bossrush.Frog;
import com.tripp1e.isleshelper.features.bossrush.GeneralBossRush;
import com.tripp1e.isleshelper.config.ConfigManager;
import com.tripp1e.isleshelper.features.general.GeneralIsles;
import com.tripp1e.isleshelper.rendering.Renderer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.entity.decoration.InteractionEntity;

public class Events {

    public static void init() {

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (Utils.getNull()) return;

            //General
            if (ConfigManager.get().bossRush.timerEnabled) GeneralBossRush.timer();

            //Frog
            if (Utils.getBoss().equals("frog")) {
                if (ConfigManager.get().bossRush.frogStomachWarningEnabled) Frog.stomachExplodeWarn();
            }

        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (Utils.getNull()) return;

            Utils.drawContext = drawContext;
            if(Utils.isInBoss() && ConfigManager.get().bossRush.timerEnabled) Renderer.renderer.drawString(drawContext.getMatrices(), GeneralBossRush.deltaTime, ConfigManager.get().bossRush.timerX, ConfigManager.get().bossRush.timerY, 1, 1, 1, 1);

        });

        ClientEntityEvents.ENTITY_LOAD.register(((entity, world) -> {
            if (Utils.getNull()) return;
            if (entity instanceof InteractionEntity) {
                IslesHelperClient.LOGGER.info("CustomName: " + entity.getName());
            }

                if (ConfigManager.get().bossRush.teammateDeathMessageEnabled) GeneralBossRush.teamDeathNotify(entity);


        }));

        ClientReceiveMessageEvents.ALLOW_GAME.register(((message, overlay) -> {
            if (Utils.getNull()) return true;

            if (ConfigManager.get().general.onlyPartyChatsEnabled) return GeneralIsles.onlyPartyMessages(message.getString());
            else return true;
        }));

    }

}