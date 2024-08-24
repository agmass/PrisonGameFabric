package org.agmas.prisongamefabric.items.blackMarketItems;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RiotShield extends ShieldItem implements PolymerItem {
    public RiotShield(Settings settings) {
        super(new Item.Settings().maxDamage(150).component(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT));
    }

    @Override
    public void postProcessComponents(ItemStack stack) {
        super.postProcessComponents(stack);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSprinting()) {
            ItemStack itemStack = user.getStackInHand(hand);
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
            user.getItemCooldownManager().set(this, 20);
            itemStack.damage(2, user, EquipmentSlot.MAINHAND);
            user.setVelocity(user.getRotationVector().x, 0, user.getRotationVector().z);
            user.velocityDirty = true;
            user.velocityModified = true;
        }
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (user instanceof PlayerEntity p) {
            if (p.getItemCooldownManager().getCooldownProgress(stack.getItem(), 0) >= 0.5f) {
                world.getOtherEntities(p, p.getBoundingBox().expand(0.5), (player)->{return true;}).forEach((player -> {
                    if (player.isLiving()) {
                        player.damage(p.getDamageSources().playerAttack(p), 4);
                        player.timeUntilRegen = 0;
                    }


                }));
            }
        }
        if (Math.random()<=0.1)
            stack.damage(1, user, EquipmentSlot.MAINHAND);
        super.usageTick(world, user, stack, remainingUseTicks);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.prisongamefabric.riotshield.tip1"));
        tooltip.add(Text.translatable("item.prisongamefabric.riotshield.tip2"));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.SHIELD;
    }

}
