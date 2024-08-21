package org.agmas.prisongamefabric.mixin.scheduleMixins;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.level.ServerWorldProperties;
import org.agmas.prisongamefabric.util.Schedule;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerWorld.class)
public abstract class TickTimeMixin {

    @Shadow public abstract List<ServerPlayerEntity> getPlayers();

    @Shadow public abstract void setTimeOfDay(long timeOfDay);

    @Shadow @Final private ServerWorldProperties worldProperties;

    @Inject(method = "tickTime", at = @At("HEAD"), cancellable = true)
    private void injected(CallbackInfo ci) {
        Schedule.ScheduleEntry currentPeriod = Schedule.getCurrentPeriod();
        double progress = (double) Schedule.time /currentPeriod.duration;
        double finalTime = (progress*(currentPeriod.worldEndTime-currentPeriod.worldStartTime))+currentPeriod.worldStartTime;
        worldProperties.setTime((long) finalTime);
        setTimeOfDay((long) finalTime);
        Schedule.scheduleBar.setName(Text.translatable("schedule.prisongamefabric." + Schedule.getCurrentPeriod().name));
        Schedule.scheduleBar.setPercent(Math.clamp((float) progress, 0, 1));
        ci.cancel();
    }
}
