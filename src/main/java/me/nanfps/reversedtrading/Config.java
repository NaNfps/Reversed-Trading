package me.nanfps.reversedtrading;

import net.minecraftforge.common.ForgeConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_VILLAGERS = BUILDER
            .comment("Whether you can reverse villagers' trading")
            .define("enableVillagers", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_WANDERING_TRADERS = BUILDER
            .comment("Whether you can reverse wandering traders' trading")
            .define("enableWanderingTraders", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();
}
