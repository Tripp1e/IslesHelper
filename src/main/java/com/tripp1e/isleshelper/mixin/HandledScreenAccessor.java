package com.tripp1e.isleshelper.mixin;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;


// Code from https://github.com/SkyblockerMod/Skyblocker/blob/master/src/main/java/de/hysky/skyblocker/mixin/accessor/HandledScreenAccessor.java#L8
@Mixin(HandledScreen.class)
public interface HandledScreenAccessor {
    @Accessor("x")
    int getX();

    @Accessor("y")
    int getY();

    @Accessor
    int getBackgroundWidth();

    @Accessor
    int getBackgroundHeight();
}
