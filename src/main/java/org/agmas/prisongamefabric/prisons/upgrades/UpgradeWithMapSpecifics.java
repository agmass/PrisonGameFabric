package org.agmas.prisongamefabric.prisons.upgrades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.prisons.PrisonZone;

import java.util.Optional;

public class UpgradeWithMapSpecifics {
    public final Identifier upgrade;
    public final Optional<Identifier> functionOnLock;
    public final Optional<Identifier> functionOnUnlock;
    public final Optional<PrisonZone> safeZone;

    public static final Codec<UpgradeWithMapSpecifics> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("upgrade").forGetter(UpgradeWithMapSpecifics::getUpgrade),
            Identifier.CODEC.optionalFieldOf("onLock").forGetter(UpgradeWithMapSpecifics::getFunctionOnUnlock),
            Identifier.CODEC.optionalFieldOf("onUnlock").forGetter(UpgradeWithMapSpecifics::getFunctionOnLock),
            PrisonZone.CODEC.optionalFieldOf("safeZone").forGetter(UpgradeWithMapSpecifics::getSafeZone)
    ).apply(instance, UpgradeWithMapSpecifics::new));

    public UpgradeWithMapSpecifics(Identifier upgrade, Optional<Identifier> functionOnLock, Optional<Identifier> functionOnUnlock, Optional<PrisonZone> safeZone) {
        this.upgrade = upgrade;
        this.functionOnLock = functionOnLock;
        this.functionOnUnlock = functionOnUnlock;
        this.safeZone = safeZone;
    }

    public Identifier getUpgrade() {
        return upgrade;
    }

    public Optional<Identifier> getFunctionOnUnlock() {
        return functionOnUnlock;
    }

    public Optional<Identifier> getFunctionOnLock() {
        return functionOnLock;
    }

    public Optional<PrisonZone> getSafeZone() {
        return safeZone;
    }
}
