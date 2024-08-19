package org.agmas.prisongamefabric.prisons;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;

public class PrisonLocation {
    public final float x;
    public final float y;
    public final float z;
    public final Optional<Float> yaw;
    public final Optional<Float> pitch;

    public static final Codec<PrisonLocation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("x").forGetter(PrisonLocation::getX),
            Codec.FLOAT.fieldOf("y").forGetter(PrisonLocation::getY),
            Codec.FLOAT.fieldOf("z").forGetter(PrisonLocation::getZ),
            Codec.FLOAT.optionalFieldOf("yaw").forGetter(PrisonLocation::getYaw),
            Codec.FLOAT.optionalFieldOf("pitch").forGetter(PrisonLocation::getPitch)
            // Up to 16 fields can be declared here
    ).apply(instance, PrisonLocation::new));

    public PrisonLocation(float x, float y, float z, Optional<Float> yaw, Optional<Float> pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Vec3d toVec3D() {
        return new Vec3d(x,y,z);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Optional<Float> getPitch() {
        return pitch;
    }

    public Optional<Float> getYaw() {
        return yaw;
    }
}
