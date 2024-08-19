package org.agmas.prisongamefabric.prisons.upgrades.hardUpgrades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;

import java.util.Optional;

public class SwatUpgrade extends PrisonUpgrade {

    public SwatUpgrade() {
        super("SWAT Guards", "Unlock the new State of the art Netherite-Armored\nSwat guards for the low low price of 3000 Warden Funds.", 3000, Identifier.of("minecraft:netherite_helmet"), Optional.empty(), Optional.empty());
    }

    @Override
    public void specialLockBehaviour() {
        super.specialLockBehaviour();
    }

    @Override
    public void specialUnlockBehaviour() {
        super.specialUnlockBehaviour();
    }
}
