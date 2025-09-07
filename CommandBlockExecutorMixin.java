package com.example.cbe.mixin;

import com.example.cbe.CommandBlocksEnabledMod;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.block.entity.CommandBlockExecutor; // Yarn name as of 1.21.x
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Prevents command block execution when gamerule commandBlocksEnabled=false.
 *
 * We inject at the head of the executor's execute method and cancel with `false`.
 */
@Mixin(CommandBlockExecutor.class)
public abstract class CommandBlockExecutorMixin {

    @Inject(method = "execute", at = @At("HEAD"), cancellable = true)
    private void cbe$gateExecute(ServerWorld world, CallbackInfoReturnable<Boolean> cir) {
        World w = world; // alias for clarity
        boolean enabled = w.getGameRules().getBoolean(CommandBlocksEnabledMod.COMMAND_BLOCKS_ENABLED);
        if (!enabled) {
            // Cancel execution: return false so command blocks do nothing this tick.
            cir.setReturnValue(false);
        }
    }
}
