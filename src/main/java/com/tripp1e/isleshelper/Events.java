package com.tripp1e.isleshelper;

import com.tripp1e.isleshelper.bossrush.Frog;
import com.tripp1e.isleshelper.bossrush.General;
import com.tripp1e.isleshelper.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.ColorHelper;

public class Events {
    public static void hudRenderCallback() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (Utils.getWorld() == null) return;

            //Timer
            int x = ConfigManager.get().general.generalX;
            int y = ConfigManager.get().general.generalY;
            int color = ColorHelper.Argb.getArgb(255, 255, 255, 255);

            if (Utils.isInBoss()) drawContext.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, General.deltaTime, x, y, color);
            }
        );
    }

    public static void startClientTick() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (Utils.getWorld() == null) return;

            //General
            if (ConfigManager.get().general.generalTeammateDeathMessage) General.teamDeathNotify();
            if (ConfigManager.get().general.generalOnlyPartyChats) General.onlyPartyMessages();
            if (ConfigManager.get().general.generalTimerEnabled) General.timer();

            //Frog
            if (Utils.getBoss().equals("frog")) {
                if (ConfigManager.get().general.frogStomachWarning) Frog.stomachExplodeWarn();
            }
        });
    }

}
