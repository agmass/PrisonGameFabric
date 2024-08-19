package org.agmas.prisongamefabric.items;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.Tx;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Keycard extends Item implements PolymerItem {

    public int level = 0;

    public Keycard(int level) {
        super(new Item.Settings());
        this.level = level;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.MAP;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void modifyClientTooltip(List<Text> tooltip, ItemStack stack, @Nullable ServerPlayerEntity player) {
        PolymerItem.super.modifyClientTooltip(tooltip, stack, player);
        if (player != null) {
            if (Profile.getProfile(player) != null) {
                if (!Profile.getRole(player).power.powerful) {
                    tooltip.add(Tx.tf(Formatting.RED, "[CONTRABAND]"));
                }
            }
        }
    }
}
