package org.agmas.prisongamefabric.mapgame.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.prisons.PrisonLocation;
import org.agmas.prisongamefabric.util.Profile;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.Set;

@Mixin(ItemFrameEntity.class)
public abstract class ItemFrameMixin {

    @Shadow public abstract ItemStack getHeldItemStack();

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract void setHeldItemStack(ItemStack stack);

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void injected(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player instanceof ServerPlayerEntity spe) {
            if (getHeldItemStack().getName().equals(Text.of("CreateComputer"))) {
                ItemStack computer = FilledMapItem.createMap((World) spe.getServerWorld(), 9999,9999, (byte) 4,false,false);
                computer.set(DataComponentTypes.ITEM_NAME, Text.of("Computer"));
                setHeldItemStack(computer.copy());
                cir.setReturnValue(ActionResult.FAIL);
                cir.cancel();
            }
            if (getHeldItemStack().getName().equals(Text.of("Computer"))) {
                Profile p = Profile.getProfile(spe);
                p.scene.justAttacked = true;
                if (!p.isInScene) {
                    if (!spe.getInventory().armor.get(3).getItem().equals(Items.FILLED_MAP)) {
                        p.helmetItem = spe.getInventory().getArmorStack(3).copy();

                        spe.getInventory().armor.set(3, getHeldItemStack());
                    }
                }
                cir.setReturnValue(ActionResult.FAIL);
                cir.cancel();
            }
        }
    }
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void injected(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof ServerPlayerEntity spe) {
            if (getHeldItemStack().getName().equals(Text.of("CreateComputer"))) {
                ItemStack computer = FilledMapItem.createMap((World) spe.getServerWorld(), 9999,9999, (byte) 4,false,false);
                computer.set(DataComponentTypes.ITEM_NAME, Text.of("Computer"));
                setHeldItemStack(computer.copy());
                cir.setReturnValue(false);
                cir.cancel();
            }
            if (getHeldItemStack().getName().equals(Text.of("Computer"))) {
                Profile p = Profile.getProfile(spe);
                p.scene.justAttacked = true;
                if (!p.isInScene) {
                    if (!spe.getInventory().armor.get(3).getItem().equals(Items.FILLED_MAP)) {
                        p.helmetItem = spe.getInventory().getArmorStack(3).copy();
                        spe.getInventory().armor.set(3, getHeldItemStack());
                    }
                }
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

}
