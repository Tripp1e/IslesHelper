package com.tripp1e.isleshelper.features.bossrush;

import com.tripp1e.isleshelper.Utils;
import com.tripp1e.isleshelper.config.ConfigManager;
import com.tripp1e.isleshelper.rendering.Renderer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.DisplayEntity;

public class General {


    public static void teamDeathNotify(Entity entity) {
        if (entity instanceof ArmorStandEntity && entity.getCustomName() != null) {
            if (entity.getCustomName().toString().toLowerCase().contains("downed")) {
                Utils.sendTitle("Teammate Died", 0, 60, 0);
            }
        }
    }

    public static void onlyPartyMessages() {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((message, a, b, c, d) -> message.toString().toLowerCase().startsWith("party"));
    }

    public static DisplayEntity.TextDisplayEntity displayEntity = null;
    private static double startTime = System.currentTimeMillis();
    private static double currentTime = System.currentTimeMillis();
    public static void timer(DrawContext context) {
        startTime = Utils.isInBoss() ? startTime : currentTime;
        String deltaTime = (currentTime - startTime)/1000D + "";

        int x = ConfigManager.get().bossRush.timerX;
        int y = ConfigManager.get().bossRush.timerY;


        if (displayEntity != null && Utils.isInBoss()) {
            Utils.sendTitle(deltaTime, 0, 2, 0);
        } else currentTime = System.currentTimeMillis();

        if (context != null && Utils.isInBoss())
        Renderer.renderer.drawString(context.getMatrices(), deltaTime, x, y, 1, 1, 1, 1);

    }

}
