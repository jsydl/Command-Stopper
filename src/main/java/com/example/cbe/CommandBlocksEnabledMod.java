package com.example.cbe;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class CommandBlocksEnabledMod implements ModInitializer {

    // Register via Fabric API (public, stable)
    public static final GameRules.Key<GameRules.BooleanValue> COMMAND_BLOCKS_ENABLED =
            GameRuleRegistry.register(
                    "commandBlocksEnabled",
                    GameRules.Category.MISC,
                    GameRuleFactory.createBooleanRule(true)
            );

    @Override
    public void onInitialize() {
        System.out.println("[CommandBlocksEnabled] gamerule registered: commandBlocksEnabled");
    }

    /**
     * Only evaluate on the server. If we're somehow on the client Level, just allow.
     */
    public static boolean isEnabled(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getGameRules().getBoolean(COMMAND_BLOCKS_ENABLED);
        }
        return true; // client/world preview: don't block
    }
}
