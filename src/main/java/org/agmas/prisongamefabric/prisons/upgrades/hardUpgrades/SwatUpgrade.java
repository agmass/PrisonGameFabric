package org.agmas.prisongamefabric.prisons.upgrades.hardUpgrades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;

import java.util.Optional;

public class SwatUpgrade extends PrisonUpgrade {

    public SwatUpgrade() {
        super("swat", 3000, Identifier.of("minecraft:netherite_helmet"), Optional.empty(), Optional.empty());
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
