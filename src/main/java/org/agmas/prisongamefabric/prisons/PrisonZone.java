package org.agmas.prisongamefabric.prisons;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Arrays;
import java.util.Optional;

public class PrisonZone {
    public final PrisonLocation zoneOne;
    public final PrisonLocation zoneTwo;


    public static final Codec<PrisonZone> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PrisonLocation.CODEC.fieldOf("zoneStart").forGetter(PrisonZone::getZoneOne),
            PrisonLocation.CODEC.fieldOf("zoneEnd").forGetter(PrisonZone::getZoneTwo)
    ).apply(instance, PrisonZone::new));

    public PrisonZone(PrisonLocation zoneOne, PrisonLocation zoneTwo) {
        this.zoneOne = zoneOne;
        this.zoneTwo = zoneTwo;
    }

    public PrisonLocation getZoneOne() {
        return zoneOne;
    }

    public PrisonLocation getZoneTwo() {
        return zoneTwo;
    }

    public boolean isInside(PlayerEntity player) {
        double[] dim = new double[2];

        dim[0] = zoneOne.getX();
        dim[1] = zoneTwo.getX();
        Arrays.sort(dim);
        if(player.getBlockPos().getX() > dim[1] || player.getBlockPos().getX() < dim[0])
            return false;

        dim[0] = zoneOne.getY();
        dim[1] = zoneTwo.getY();
        Arrays.sort(dim);
        if(player.getBlockPos().getY() > dim[1] || player.getBlockPos().getY() < dim[0])
            return false;

        dim[0] = zoneOne.getZ();
        dim[1] = zoneTwo.getZ();
        Arrays.sort(dim);
        return !(player.getBlockPos().getZ() > dim[1]) && !(player.getBlockPos().getZ() < dim[0]);

    }
}
