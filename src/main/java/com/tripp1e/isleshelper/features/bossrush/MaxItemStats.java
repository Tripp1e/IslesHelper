package com.tripp1e.isleshelper.features.bossrush;

import com.tripp1e.isleshelper.IslesHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.Arrays;
import java.util.List;

public class MaxItemStats {

    public static void parseItemLore(ItemStack item) {
        NbtCompound nbtCompound = item.getNbt();
        if (nbtCompound == null) return;


        //Parse
        NbtList loreList = nbtCompound.getCompound("display").getList("Lore", 8);
        List<String> list = Arrays.stream(loreList.toString()
                        .replaceAll("\"italic\":false,", "")
                        .replaceAll("\"color\":\"white\",", "")
                        .split("\\[|'|\"text\"|\"\\]|,"))
                .filter(str -> !(str.contains("\uE140") || str.isEmpty() || str.contains("strikethrough")))
                .map(str -> {
                    String strNew = str.replaceAll("\"", "")
                            .replaceAll("}", "")
                            .replaceAll("\\{", "")
                            .replaceAll("]", "")
                            .replaceAll("\\[", "")
                            .replaceAll(":", "");
                            return strNew;})
                .filter(str -> !str.contains("color") &&
                        !str.contains("  ") &&
                        !str.contains("\"\"") &&
                        !str.contains("extra") &&
                        !str.contains("bold") &&
                        !str.contains("\\[\\[") &&
                        !str.contains("\\]\\]") &&
                        !str.isEmpty())
                .toList();

        IslesHelper.LOGGER.info(list.toString());
    }

}