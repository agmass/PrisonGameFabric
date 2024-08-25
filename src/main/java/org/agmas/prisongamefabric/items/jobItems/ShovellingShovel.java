package org.agmas.prisongamefabric.items.jobItems;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockPredicatesChecker;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameBlocks;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.StateSaverAndLoader;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class ShovellingShovel extends Item implements PolymerItem, MiningItem {

    ArrayList<BlockPredicate> breakables = new ArrayList<>();
    public ShovellingShovel(Settings settings) {
        super(settings);
        breakables.add(BlockPredicate.Builder.create().blocks(PrisonGameBlocks.POOPBLOCK).build());
        breakables.add(BlockPredicate.Builder.create().blocks(Blocks.COARSE_DIRT).build());

    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        stack.set(DataComponentTypes.CAN_BREAK, new BlockPredicatesChecker(breakables,true));
        return stack;
    }

    @Override
    public void onMine(PlayerEntity p, BlockState b, World w, BlockPos pos) {

        Profile profile = Profile.getProfile(p);
        profile.addMoney(4,true);
        MiningItem.super.onMine(p,b,w,pos);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.IRON_SHOVEL;
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, TooltipType tooltipType, RegistryWrapper.WrapperLookup lookup, @Nullable ServerPlayerEntity player) {
        ItemStack stack = PolymerItem.super.getPolymerItemStack(itemStack, tooltipType, lookup, player);
        stack.set(DataComponentTypes.CAN_BREAK, new BlockPredicatesChecker(breakables,true));
        return stack;
    }
}
