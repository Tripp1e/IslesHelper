package com.tripp1e.isleshelper;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class Utils{

    public static PlayerEntity getPlayer() {return MinecraftClient.getInstance().player;}
    public static World getWorld() {return MinecraftClient.getInstance().world;}
    public static boolean getNull() { return getPlayer() == null || getWorld() == null;}
    public static InGameHud getHUD() {return MinecraftClient.getInstance().inGameHud;}
    public static DrawContext drawContext = null;
    public static List<String> bosses = Arrays.asList("reaper", "queen", "nanook", "frog", "turtle", "dragon");

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
        return bosses.stream()
        .anyMatch(e -> getWorld().getRegistryKey().getValue().toString().contains(e));
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