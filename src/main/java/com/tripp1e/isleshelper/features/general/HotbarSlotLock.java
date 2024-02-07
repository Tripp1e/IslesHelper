package com.tripp1e.isleshelper.features.general;

import com.tripp1e.isleshelper.config.ConfigManager;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

//From https://github.com/SkyblockerMod/Skyblocker/blob/master/src/main/java/de/hysky/skyblocker/skyblock/item/HotbarSlotLock.java
public class HotbarSlotLock {
    public static KeyBinding hotbarSlotLock;
    public static void init() {
        hotbarSlotLock = KeyBindingHelper.registerKeyBinding( new KeyBinding(
                "key.hotbarSlotLock",
                GLFW.GLFW_KEY_H,
                "key.categories.isleshelper"
        ));
    }

    public static boolean isLocked(int slot) {
        return ConfigManager.get().general.lockedSlots.contains(slot);
    }

    public static void handleDropSelectedItem(int slot, CallbackInfoReturnable<Boolean> cir) {
        if (isLocked(slot)) cir.setReturnValue(false);
    }

    public static void handleInputEvents(ClientPlayerEntity player) {
        while (hotbarSlotLock.wasPressed()) {
            List<Integer> lockedSlots = ConfigManager.get().general.lockedSlots;
            int selected = player.getInventory().selectedSlot;
            if (!isLocked(player.getInventory().selectedSlot)) lockedSlots.add(selected);
            else lockedSlots.remove(Integer.valueOf(selected));
            ConfigManager.save();
        }
    }
}
