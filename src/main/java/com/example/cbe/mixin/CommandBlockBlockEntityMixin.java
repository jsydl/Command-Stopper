package com.example.cbe.mixin;

import com.example.cbe.CommandBlocksEnabledMod;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Target the Mojang-mapped BaseCommandBlock (what command blocks use to run)
@Mixin(targets = "net.minecraft.world.level.BaseCommandBlock")
public class CommandBlockBlockEntityMixin {

    // Signature in Mojang 1.21.8: boolean performCommand(Level)
    @Inject(
            method = "performCommand(Lnet/minecraft/world/level/Level;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void cbe$gateExecute(Level level, CallbackInfoReturnable<Boolean> cir) {
        if (!CommandBlocksEnabledMod.isEnabled(level)) {
            // cancel execution
            cir.setReturnValue(false);
        }
    }
}
