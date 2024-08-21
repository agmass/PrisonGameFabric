package org.agmas.prisongamefabric.items.blackMarketItems;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockPredicatesChecker;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.util.Profile;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class FunnySugar extends Item implements PolymerItem {
    public FunnySugar(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20*10,0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 20*10,0));

        ItemStack itemStack = user.getStackInHand(hand);
        itemStack.decrementUnlessCreative(1, user);
        return super.use(world, user, hand);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.SUGAR;
    }

}
