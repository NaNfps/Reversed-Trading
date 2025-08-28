package me.nanfps.reversedtrading;

import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_VILLAGERS = BUILDER
            .comment("Whether you can reverse villagers' trading")
            .define("enableVillagers", true);

    public static final ModConfigSpec.BooleanValue ENABLE_WANDERING_TRADERS = BUILDER
            .comment("Whether you can reverse wandering traders' trading")
            .define("enableWanderingTraders", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
