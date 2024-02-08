package com.tripp1e.isleshelper.config.categories;

import com.tripp1e.isleshelper.Utils;
import com.tripp1e.isleshelper.config.Config;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import net.minecraft.text.Text;

public class BossRushCategory {

    public static ConfigCategory create(Config defaults, Config config) {
        return ConfigCategory.createBuilder()
            .name(Text.of("Boss Rush Options"))

            .option(Option.<Boolean>createBuilder()
                .name(Text.of("Notify when Teammate died"))
                .binding(defaults.bossRush.teammateDeathMessageEnabled,
                    () -> config.bossRush.teammateDeathMessageEnabled,
                    newValue -> config.bossRush.teammateDeathMessageEnabled = newValue)
                .controller(Utils::createBooleanController)
                .build())

            .group(OptionGroup.createBuilder()
                .name(Text.of("Timer Options"))
                .collapsed(true)

                    .option(Option.<Boolean>createBuilder()
                        .name(Text.of("Time Bossrush Runs?"))
                        .binding(defaults.bossRush.timerEnabled,
                            () -> config.bossRush.timerEnabled,
                            newValue -> config.bossRush.timerEnabled = newValue)
                        .controller(Utils::createBooleanController)
                        .build())

                    .option(Option.<Integer>createBuilder()
                        .name(Text.of("X of Timer"))
                        .binding(defaults.bossRush.timerX,
                            () -> config.bossRush.timerX,
                            newValue -> config.bossRush.timerX = newValue)
                        .controller(opt->Utils.createIntegerController(opt, 0, 1000))
                        .build())

                    .option(Option.<Integer>createBuilder()
                        .name(Text.of("Y of Timer"))
                        .binding(defaults.bossRush.timerY,
                            () -> config.bossRush.timerY,
                            newValue -> config.bossRush.timerY = newValue)
                        .controller(opt->Utils.createIntegerController(opt, 0, 500))
                        .build())
                    .build())

            .group(OptionGroup.createBuilder()
                .name(Text.of("Frog Options"))
                .collapsed(true)

                    .option(Option.<Boolean>createBuilder()
                        .name(Text.of("Warn about explosions in the frog's stomach"))
                        .binding(defaults.bossRush.frogStomachWarningEnabled,
                            () -> config.bossRush.frogStomachWarningEnabled,
                            newValue -> config.bossRush.frogStomachWarningEnabled = newValue)
                        .controller(Utils::createBooleanController)
                        .build())

                    .build())
                .build();


    }

}
