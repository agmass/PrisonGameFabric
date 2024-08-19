package org.agmas.prisongamefabric.mixin;

import net.minecraft.block.StructureBlock;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureBlockBlockEntity.class)
public abstract class StructureBlockMixin {


    @Shadow public abstract void setTemplateName(@Nullable String templateName);

    @Shadow private String author;

    @Shadow private String metadata;

    @Shadow private BlockPos offset;

    @Shadow private Vec3i size;

    @Shadow private BlockRotation rotation;

    @Shadow private BlockMirror mirror;

    @Shadow private StructureBlockMode mode;

    @Shadow private boolean ignoreEntities;

    @Shadow private boolean powered;

    @Shadow private boolean showAir;

    @Shadow private boolean showBoundingBox;

    @Shadow private float integrity;

    @Shadow private long seed;

    @Shadow protected abstract void updateBlockMode();

    @Inject(method = "readNbt", at = @At("HEAD"), cancellable = true)
    private void injected(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo ci) {
        this.setTemplateName(nbt.getString("name"));
        this.author = nbt.getString("author");
        this.metadata = nbt.getString("metadata");
        int i = MathHelper.clamp(nbt.getInt("posX"), -99999, 99999);
        int j = MathHelper.clamp(nbt.getInt("posY"), -99999, 99999);
        int k = MathHelper.clamp(nbt.getInt("posZ"), -99999, 99999);
        this.offset = new BlockPos(i, j, k);
        int l = nbt.getInt("sizeX");
        int m = nbt.getInt("sizeY");
        int n = nbt.getInt("sizeZ");
        this.size = new Vec3i(l, m, n);

        try {
            this.rotation = BlockRotation.valueOf(nbt.getString("rotation"));
        } catch (IllegalArgumentException var12) {
            this.rotation = BlockRotation.NONE;
        }

        try {
            this.mirror = BlockMirror.valueOf(nbt.getString("mirror"));
        } catch (IllegalArgumentException var11) {
            this.mirror = BlockMirror.NONE;
        }

        try {
            this.mode = StructureBlockMode.valueOf(nbt.getString("mode"));
        } catch (IllegalArgumentException var10) {
            this.mode = StructureBlockMode.DATA;
        }

        this.ignoreEntities = nbt.getBoolean("ignoreEntities");
        this.powered = nbt.getBoolean("powered");
        this.showAir = nbt.getBoolean("showair");
        this.showBoundingBox = nbt.getBoolean("showboundingbox");
        if (nbt.contains("integrity")) {
            this.integrity = nbt.getFloat("integrity");
        } else {
            this.integrity = 1.0F;
        }

        this.seed = nbt.getLong("seed");
        this.updateBlockMode();
        ci.cancel();
    }

}
