package org.agmas.prisongamefabric.mixin.scheduleMixins;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.text.Text;
import org.agmas.prisongamefabric.util.Schedule;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class ScheduleTickMixin {

    @Shadow public abstract PlayerManager getPlayerManager();

    @Shadow private int ticks;

    @Redirect(method = "tickWorlds", at = @At(value = "FIELD", target = "Lnet/minecraft/server/MinecraftServer;ticks:I"))
    private int injected2(MinecraftServer instance) {
        return ticks % 5 == 0 ? 0 : 20;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void injected(CallbackInfo ci) {
        Schedule.time++;
        Schedule.advanceSchedule();
    }
}
