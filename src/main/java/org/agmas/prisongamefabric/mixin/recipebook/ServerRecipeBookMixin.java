package org.agmas.prisongamefabric.mixin.recipebook;

import net.minecraft.network.packet.s2c.play.ChangeUnlockedRecipesS2CPacket;
import net.minecraft.recipe.book.RecipeBook;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerRecipeBook;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerRecipeBook.class)
public abstract class ServerRecipeBookMixin {


    @Inject(method = "sendUnlockRecipesPacket", at = @At("HEAD"), cancellable = true)
    private void injected(ChangeUnlockedRecipesS2CPacket.Action action, ServerPlayerEntity player, List<Identifier> recipeIds, CallbackInfo ci) {
        if (recipeIds != null) {
            recipeIds.removeIf((p)->{
                return p.getNamespace().equalsIgnoreCase("minecraft");
            });
            if (recipeIds.isEmpty())
                ci.cancel();
        }
    }


}
