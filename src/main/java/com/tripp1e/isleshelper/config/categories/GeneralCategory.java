package com.tripp1e.isleshelper.config.categories;

import com.tripp1e.isleshelper.Utils;
import com.tripp1e.isleshelper.config.ConfigManager;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import net.minecraft.text.Text;


// Modified Code from https://github.com/SkyblockerMod/Skyblocker/blob/master/src/main/java/de/hysky/skyblocker/config/categories/GeneralCatagory.java
public class GeneralCategory {
    public static ConfigCategory create(ConfigManager defaults, ConfigManager config) {
        return ConfigCategory.createBuilder()
                .name(Text.of("General Options"))

                //Ungrouped Options
                .option(Option.<Boolean>createBuilder()
                        .name(Text.of("Enable Warning for Explosions on "))
                        .binding(defaults.general.frogStomachWarning,
                                () -> config.general.frogStomachWarning,
                                newValue -> config.general.frogStomachWarning = newValue)
                        .controller(Utils::createBooleanController)
                        .build())
                .build();
    }

}