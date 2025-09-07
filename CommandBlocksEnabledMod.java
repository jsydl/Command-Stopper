package com.example.cbe;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.GameRules;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;

public class CommandBlocksEnabledMod implements ModInitializer {
    public static final String MOD_ID = "cbe";

    public static final GameRules.Key<GameRules.BooleanRule> COMMAND_BLOCKS_ENABLED =
            GameRuleRegistry.register("commandBlocksEnabled",
                    GameRules.Category.COMMANDS,
                    GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {
        // Nothing else needed; the mixin checks this gamerule before execution.
    }
}
