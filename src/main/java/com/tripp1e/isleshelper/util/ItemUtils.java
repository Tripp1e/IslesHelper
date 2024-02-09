package com.tripp1e.isleshelper.util;

import com.tripp1e.isleshelper.IslesHelper;
import com.tripp1e.isleshelper.mixin.accessor.HandledScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.slot.Slot;

import java.util.Arrays;
import java.util.List;

public class ItemUtils {

    public static List<String> parseItemLore(ItemStack item) {
        NbtCompound nbtCompound = item.getNbt();
        if (nbtCompound == null) return null;


        //Parse
        NbtList loreList = nbtCompound.getCompound("display").getList("Lore", 8);
        return Arrays.stream(loreList.toString()
                        .replaceAll("\"italic\":false,", "")
                        .replaceAll("\"color\":\"white\",", "")
                        .split("\\[|'|\"text\"|\"\\]|,"))
                .filter(str -> !(str.contains("\uE140") || str.isEmpty() || str.contains("strikethrough")))
                .map(str -> {
                    return str.replaceAll("\"", "")
                            .replaceAll("}", "")
                            .replaceAll("\\{", "")
                            .replaceAll("]", "")
                            .replaceAll("\\[", "")
                            .replaceAll(":", "");})
                .filter(str -> !str.contains("color") &&
                        !str.contains("  ") &&
                        !str.contains("\"\"") &&
                        !str.contains("extra") &&
                        !str.contains("bold") &&
                        !str.contains("\\[\\[") &&
                        !str.contains("\\]\\]") &&
                        !str.isEmpty())
                .toList();
    }

    public static int getHoveredSlot() {
        if (MinecraftClient.getInstance().currentScreen == null) return -999;

        Slot hoveredSlot = ((HandledScreenAccessor) MinecraftClient.getInstance().currentScreen).getTouchHoveredSlot();
        return hoveredSlot.getIndex();
    }

    public static void testItem(){

        List<String> list = parseItemLore(Utils.getPlayer().getInventory().getStack(getHoveredSlot()));
        if (list != null) {
        IslesHelper.LOGGER.info(list.toString()); }
        else IslesHelper.LOGGER.info("List is Empty");

    }


}
