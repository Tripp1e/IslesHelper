package com.tripp1e.isleshelper.config.categories;

import com.tripp1e.isleshelper.Utils;
import com.tripp1e.isleshelper.config.Config;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import net.minecraft.text.Text;

public class GeneralCategory {

    public static ConfigCategory create(Config defaults, Config config) {
        return ConfigCategory.createBuilder()
                .name(Text.of("General Options"))

                .option(Option.<Boolean>createBuilder()
                    .name(Text.of("Only receive Messages from your Party"))
                    .binding(defaults.general.onlyPartyChatsEnabled,
                        () -> config.general.onlyPartyChatsEnabled,
                        newValue -> config.general.onlyPartyChatsEnabled = newValue)
                    .controller(Utils::createBooleanController)
                    .build())

                .group(OptionGroup.createBuilder()
                        .name(Text.of("Hotbar Slot Locking Options"))
                        .collapsed(false)

                            .option(Option.<Boolean>createBuilder()
                            .name(Text.of("Hotbar Slot Locking Enabled"))
                            .binding(defaults.general.lockingSlotsEnabled,
                                () -> config.general.lockingSlotsEnabled,
                                newValue -> config.general.lockingSlotsEnabled = newValue)
                            .controller(Utils::createBooleanController)
                            .build())



                .build())
        .build();
    }

}
