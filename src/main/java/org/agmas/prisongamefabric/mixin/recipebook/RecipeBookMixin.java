package org.agmas.prisongamefabric.mixin.recipebook;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.book.RecipeBook;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.block.RegenerativeBlock;
import org.agmas.prisongamefabric.items.jobItems.MiningItem;
import org.agmas.prisongamefabric.prisons.shopSigns.ShopSign;
import org.agmas.prisongamefabric.util.Profile;
import org.agmas.prisongamefabric.util.jobs.MiningJob;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RecipeBook.class)
public abstract class RecipeBookMixin {


    @Inject(method = "add(Lnet/minecraft/util/Identifier;)V", at = @At("HEAD"), cancellable = true)
    private void injected(Identifier id, CallbackInfo ci) {
        if (id.getNamespace().equalsIgnoreCase("minecraft")) {
            ci.cancel();
        }
    }


}
