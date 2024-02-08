package com.tripp1e.isleshelper.mixin.accessor;

import net.minecraft.client.gui.hud.ClientBossBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.UUID;

@Mixin(net.minecraft.client.gui.hud.BossBarHud.class)
public interface BossBarHudAccessor {

    @Accessor("bossBars")
    Map<UUID, ClientBossBar> getBossbars();

}
