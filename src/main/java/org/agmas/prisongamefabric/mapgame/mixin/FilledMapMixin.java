package org.agmas.prisongamefabric.mapgame.mixin;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.mapgame.Scene;
import org.agmas.prisongamefabric.mapgame.Sprite;
import org.agmas.prisongamefabric.mapgame.sprites.icons.Icon;
import org.agmas.prisongamefabric.prisons.PrisonLocation;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Tx;
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

@Mixin(FilledMapItem.class)
public abstract class FilledMapMixin {
    @Shadow public abstract void updateColors(World world, Entity entity, MapState state);

    @Shadow
    @Nullable
    public static MapState getMapState(ItemStack map, World world) {
        return null;
    }

    @Shadow
    private static MapIdComponent allocateMapId(World world, int x, int z, int scale, boolean showIcons, boolean unlimitedTracking, RegistryKey<World> dimension) {
        return null;
    }

    @Inject(method = "createMap", at = @At("HEAD"), cancellable = true)
    private static void injected(World world, int x, int z, byte scale, boolean showIcons, boolean unlimitedTracking, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack itemStack = new ItemStack(Items.FILLED_MAP);
        MapIdComponent mapIdComponent = allocateMapId(world, 99999, 99999, scale, false, unlimitedTracking, world.getRegistryKey());
        itemStack.set(DataComponentTypes.MAP_ID, mapIdComponent);
        cir.setReturnValue(itemStack);
        cir.cancel();
    }


    @Inject(method = "inventoryTick", at = @At("HEAD"), cancellable = true)
    private void injected(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (!world.isClient && stack.getName().equals(Text.of("Computer"))) {
            if (stack.getEnchantments().isEmpty()) {
                stack.addEnchantment(world.getServer().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.BINDING_CURSE).get(), 0);
                stack.addEnchantment(world.getServer().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.VANISHING_CURSE).get(), 0);
            }
            MapState state = getMapState(stack, world);
            if (state != null) {
                if (entity instanceof ServerPlayerEntity playerEntity) {
                    Profile p = Profile.getProfile(playerEntity);
                    state.update(playerEntity, stack);
                    if (playerEntity.isSneaking()) {
                        playerEntity.getInventory().armor.set(3,p.helmetItem);
                        ci.cancel();
                        return;
                    }
                    p.actionBarInvasion = 2;
                    playerEntity.sendMessage(Tx.wrapInBrackets(Formatting.DARK_RED, Tx.tf(Formatting.RED, "Sneak to Exit")), true);
                }


                if (state.scale != 4) {
                    state.zoomOut();
                    state.zoomOut();
                    state.zoomOut();
                    state.zoomOut();
                }
                if (entity instanceof ServerPlayerEntity spe) {

                    PrisonLocation pl = PrisonGameFabric.active.computerTeleport;
                    requestTeleport(spe,pl.x,pl.y,pl.z,pl.yaw.orElse(0f),pl.pitch.orElse(0f));

                    Profile p = Profile.getProfile(spe);
                    p.isInScene = true;
                    p.previousMapState= state;
                    Scene scene = Profile.getScene(spe);
                    scene.playerMouseX = Math.clamp(scene.playerMouseX, 0, 120);
                    scene.playerMouseY = Math.clamp(scene.playerMouseY, 0, 120);
                    for (int y = 0; y < 128; y++) {
                        for (int x = 0; x < 128; x++) {
                            state.putColor(x, y, scene.backgroundColor);
                        }
                    }
                    scene.update();
                    for (Sprite sprite : scene.sprites) {
                        if (sprite.x > 128 || sprite.y > 128) {
                            continue;
                        }
                        if (sprite.sampleColor != null) {
                            for (int y = (int) sprite.y; y < (int) sprite.y+sprite.height; y++) {
                                for (int x = (int) sprite.x; x < (int) sprite.x+sprite.width; x++) {

                                    if (x < 128 &&y < 128) {
                                        state.putColor(x, y, (byte) sprite.sampleColor);
                                    }
                                }
                            }
                        }
                        byte transparency = -1;
                        if (sprite instanceof Icon i) {
                            if (i.selected) {
                                transparency = Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.LOW);
                            }
                        }
                        int yLevel = 0;
                        boolean shouldDither=true;
                        if (sprite.bitmap != null) {
                            for (Byte[] bytes : sprite.bitmap) {
                                int xLevel = 0;
                                for (Byte aByte : bytes) {
                                    if (sprite.x + xLevel < 128 &&sprite.y + yLevel < 128) {
                                        if (aByte != -1) {
                                            if (!(sprite.dither && shouldDither)) {
                                                state.putColor((int) (sprite.x + xLevel), (int) (sprite.y + yLevel), aByte);
                                            }
                                        } else if (transparency != -1) {
                                            state.putColor((int) (sprite.x + xLevel), (int) (sprite.y + yLevel), transparency);
                                        }

                                    }
                                    shouldDither = !shouldDither;
                                    xLevel++;
                                }
                                shouldDither = !shouldDither;
                                yLevel++;
                            }
                        }
                    }

                }

            }
        }
        ci.cancel();
    }

    @Unique
    public void requestTeleport(ServerPlayerEntity player, double x, double y, double z, float yaw, float pitch) {
        this.requestTeleport(player, x, y, z, yaw, pitch, Collections.emptySet());
    }

    @Unique
    public void requestTeleport(ServerPlayerEntity player, double x, double y, double z, float yaw, float pitch, Set<PositionFlag> flags) {

        double d = flags.contains(PositionFlag.X) ? player.getX() : 0.0;
        double e = flags.contains(PositionFlag.Y) ? player.getY() : 0.0;
        double f = flags.contains(PositionFlag.Z) ? player.getZ() : 0.0;
        float g = flags.contains(PositionFlag.Y_ROT) ? player.getYaw() : 0.0F;
        float h = flags.contains(PositionFlag.X_ROT) ? player.getPitch() : 0.0F;

        player.updatePositionAndAngles(x, y, z, yaw, pitch);
        player.networkHandler.sendPacket(new PlayerPositionLookS2CPacket(x - d, y - e, z - f, yaw - g, pitch - h, flags, 0));
    }

}
