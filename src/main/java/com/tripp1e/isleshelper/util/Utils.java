package com.tripp1e.isleshelper.util;

import com.tripp1e.isleshelper.mixin.accessor.BossBarHudAccessor;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;


import static com.tripp1e.isleshelper.util.Icons.bosses;

public class Utils{

    public static PlayerEntity getPlayer() {return MinecraftClient.getInstance().player;}
    public static World getWorld() {return MinecraftClient.getInstance().world;}
    public static boolean getNull() { return getPlayer() == null || getWorld() == null;}
        public static ClientPlayNetworkHandler getNetworkhandler() {return MinecraftClient.getInstance().getNetworkHandler();}
    public static boolean onIsles() {
        return getNetworkhandler() != null && getNetworkhandler().getConnection().getAddress().toString().contains("play.skyblockisles.net");}
    public static InGameHud getHUD() {return MinecraftClient.getInstance().inGameHud;}
    public static DrawContext drawContext = null;

    public static String getBoss() {
        return bosses.stream()
                .filter(e -> getWorld().getRegistryKey().getValue().toString().contains(e))
                .findFirst().orElse("none");
    }
    public static String getBossType() {
        String registryValue = getWorld().getRegistryKey().getValue().toString();
        return registryValue.contains("rookie") ? "rookie" : (registryValue.contains("expert") ? "expert" : "other");
    }

    public static boolean isInBoss() {
        return ((BossBarHudAccessor)MinecraftClient.getInstance().inGameHud.getBossBarHud())
                .getBossbars()
                .values()
                .stream()
                .map(bossBar -> bossBar.getName().toString().toLowerCase())
                .anyMatch(bossbarString -> bosses.stream().anyMatch(bossbarString::contains));
    }


    public static void sendTitle(String title, int fade, int stay, int leave) {
        getHUD().setTitleTicks(fade, stay, leave);
        getHUD().setTitle(Text.of(title));
    }

    public static BooleanControllerBuilder createBooleanController(Option<Boolean> opt) {
        return BooleanControllerBuilder.create(opt).yesNoFormatter().coloured(true);
    }
    public static IntegerSliderControllerBuilder createIntegerController(Option<Integer> opt, int min, int max) {
        return IntegerSliderControllerBuilder.create(opt).step(1).range(min, max);
    }

}