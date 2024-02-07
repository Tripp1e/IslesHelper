package com.tripp1e.isleshelper.mixin;

import com.mojang.authlib.GameProfile;
import com.tripp1e.isleshelper.features.general.HotbarSlotLock;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// From https://github.com/SkyblockerMod/Skyblocker/blob/master/src/main/java/de/hysky/skyblocker/mixin/ClientPlayerEntityMixin.java
@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {super(world, profile);}
    @Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
    public void skyblockisles$dropSelectedItem(CallbackInfoReturnable<Boolean> cir) {
        HotbarSlotLock.handleDropSelectedItem(this.getInventory().selectedSlot, cir);
    }

}