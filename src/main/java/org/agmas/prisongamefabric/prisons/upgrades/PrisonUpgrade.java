package org.agmas.prisongamefabric.prisons.upgrades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class PrisonUpgrade {
    public final String name;
    public final String description;
    public final Integer price;
    public final Identifier itemIcon;
    public final Optional<Identifier> globalLock;
    public final Optional<Identifier> globalUnlock;
    public final Item itemIconAsItem;

    public static final Codec<PrisonUpgrade> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(PrisonUpgrade::getName),
            Codec.STRING.fieldOf("description").forGetter(PrisonUpgrade::getDescription),
            Codec.INT.fieldOf("price").forGetter(PrisonUpgrade::getPrice),
            Identifier.CODEC.fieldOf("itemIcon").forGetter(PrisonUpgrade::getItemIcon),
            Identifier.CODEC.optionalFieldOf("onGlobalLock").forGetter(PrisonUpgrade::getGlobalLock),
            Identifier.CODEC.optionalFieldOf("onGlobalUnlock").forGetter(PrisonUpgrade::getGlobalUnlock)
            // Up to 16 fields can be declared here
    ).apply(instance,PrisonUpgrade::new));

    public PrisonUpgrade(String name, String description, Integer price, Identifier itemIcon, Optional<Identifier> globalLock, Optional<Identifier> globalUnlock) {
        this.name = name;
        this.description = description;
        this.price = price;

        this.itemIcon = itemIcon;
        this.itemIconAsItem = Registries.ITEM.get(itemIcon);
        this.globalLock = globalLock;
        this.globalUnlock = globalUnlock;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Identifier getItemIcon() {
        return itemIcon;
    }

    public Optional<Identifier> getGlobalLock() {
        return globalLock;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Identifier> getGlobalUnlock() {
        return globalUnlock;
    }
}
