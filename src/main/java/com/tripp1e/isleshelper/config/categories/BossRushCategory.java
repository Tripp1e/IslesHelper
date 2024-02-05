package com.tripp1e.isleshelper.config.categories;

import com.tripp1e.isleshelper.Utils;
import com.tripp1e.isleshelper.config.ConfigManager;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import net.minecraft.text.Text;

public class BossRushCategory {

    public static ConfigCategory create(ConfigManager defaults, ConfigManager config) {
        return ConfigCategory.createBuilder()
            .name(Text.of("Boss Rush Options"))

            .option(Option.<Boolean>createBuilder()
                .name(Text.of("Notify when Teammate died"))
                .binding(defaults.general.generalTeammateDeathMessage,
                    () -> config.general.generalTeammateDeathMessage,
                    newValue -> config.general.generalTeammateDeathMessage = newValue)
                .controller(Utils::createBooleanController)
                .build())

            .option(Option.<Boolean>createBuilder()
                .name(Text.of("Only receive Messages from your Party"))
                .binding(defaults.general.generalOnlyPartyChats,
                    () -> config.general.generalOnlyPartyChats,
                    newValue -> config.general.generalOnlyPartyChats = newValue)
                .controller(Utils::createBooleanController)
                .build())

            .group(OptionGroup.createBuilder()
                .name(Text.of("Timer Options"))
                .collapsed(true)

                    .option(Option.<Boolean>createBuilder()
                        .name(Text.of("Time Bossrush Runs?"))
                        .binding(defaults.general.generalOnlyPartyChats,
                            () -> config.general.generalOnlyPartyChats,
                            newValue -> config.general.generalOnlyPartyChats = newValue)
                        .controller(Utils::createBooleanController)
                        .build())

                    .option(Option.<Integer>createBuilder()
                        .name(Text.of("X of Timer"))
                        .binding(defaults.general.generalX,
                            () -> config.general.generalX,
                            newValue -> config.general.generalX = newValue)
                        .controller(opt->Utils.createIntegerController(opt, 0, 1000))
                        .build())

                    .option(Option.<Integer>createBuilder()
                        .name(Text.of("Y of Timer"))
                        .binding(defaults.general.generalY,
                            () -> config.general.generalY,
                            newValue -> config.general.generalY = newValue)
                        .controller(opt->Utils.createIntegerController(opt, 0, 500))
                        .build())
                    .build())

            .group(OptionGroup.createBuilder()
                .name(Text.of("Frog Options"))
                .collapsed(true)

                    .option(Option.<Boolean>createBuilder()
                        .name(Text.of("Warn about explosions in the frog's stomach"))
                        .binding(defaults.general.frogStomachWarning,
                            () -> config.general.frogStomachWarning,
                            newValue -> config.general.frogStomachWarning = newValue)
                        .controller(Utils::createBooleanController)
                        .build())

                    .build())
                .build();


    }

}
