package com.tripp1e.isleshelper.config;


import com.google.gson.FieldNamingPolicy;
import com.tripp1e.isleshelper.IslesHelperClient;
import com.tripp1e.isleshelper.config.categories.BossRushCategory;
import com.tripp1e.isleshelper.config.categories.GeneralCategory;
import com.tripp1e.isleshelper.mixin.HandledScreenAccessor;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.nio.file.Path;


// Modified Code from https://github.com/SkyblockerMod/Skyblocker/blob/master/src/main/java/de/hysky/skyblocker/config/SkyblockerConfigManager.java
public class ConfigManager {

    // Utils
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("isleshelper.json");
    public static Config get() {return HANDLER.instance();}
    public static void save() {
        HANDLER.save();
    }
    public static Screen createGUI(Screen parent) {
        return YetAnotherConfigLib.create(HANDLER, (defaults, config, builder) -> builder
                .title(Text.of("Isleshelper Config"))
                .category(GeneralCategory.create(defaults, config))
                .category(BossRushCategory.create(defaults, config))).generateScreen(parent);
    }

    // Init
    public static void init() {
        HANDLER.load();
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> dispatcher.register(ClientCommandManager.literal(IslesHelperClient.MOD_ID))));
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof GenericContainerScreen genericContainerScreen && screen.getTitle().getString().equals("IslesHelper Config")) {
                Screens.getButtons(screen).add(ButtonWidget
                        .builder(Text.literal("\uD83D\uDD27"), buttonWidget -> client.setScreen(createGUI(screen)))
                        .dimensions(((HandledScreenAccessor) genericContainerScreen).getX() + ((HandledScreenAccessor) genericContainerScreen).getBackgroundWidth() - 16, ((HandledScreenAccessor) genericContainerScreen).getY() + 4, 12, 12)
                        .tooltip(Tooltip.of(Text.of("IslesHelper Config")))
                        .build());
            }
        });
    }

    // Make Handler
    private static final ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(PATH)
                    .setJson5(false)
                    .appendGsonBuilder(builder -> builder
                            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                            .registerTypeHierarchyAdapter(Identifier.class, new Identifier.Serializer()))
                    .build())
            .build();




}