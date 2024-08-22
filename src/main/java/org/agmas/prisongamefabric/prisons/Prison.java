package org.agmas.prisongamefabric.prisons;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.prisons.upgrades.UpgradeWithMapSpecifics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Prison {
    public final String name;
    public final Identifier itemIcon;
    public final Item itemIconAsItem;
    public final List<PrisonLocation> cellLocations;
    public ArrayList<UpgradeWithMapSpecifics> upgrades;
    public final PrisonLocation wardenSpawn;
    public final PrisonLocation guardSpawn;
    public final PrisonLocation computerTeleport;
    public final PrisonLocation blackMarketTeleport;
    public final PrisonZone escapeZone;
    public final PrisonZone blackMarketExit;
    public final Optional<List<Identifier>> onTick;

    public static final Codec<Prison> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(Prison::getName),
            Identifier.CODEC.fieldOf("itemIcon").forGetter(Prison::getItemIcon),
            PrisonLocation.CODEC.listOf().fieldOf("cellLocations").forGetter(Prison::getCellLocations),
            UpgradeWithMapSpecifics.CODEC.listOf().fieldOf("upgrades").forGetter(Prison::getUpgrades),
            PrisonLocation.CODEC.fieldOf("wardenSpawn").forGetter(Prison::getWardenSpawn),
            PrisonLocation.CODEC.fieldOf("guardSpawn").forGetter(Prison::getGuardSpawn),
            PrisonLocation.CODEC.fieldOf("computerTeleport").forGetter(Prison::getComputerTeleport),
            PrisonLocation.CODEC.fieldOf("blackMarketTeleport").forGetter(Prison::getBlackMarketTeleport),
            PrisonZone.CODEC.fieldOf("escapeZone").forGetter(Prison::getEscapeZone),
            PrisonZone.CODEC.fieldOf("blackMarketExit").forGetter(Prison::getBlackMarketExit),
            Identifier.CODEC.listOf().optionalFieldOf("onTick").forGetter(Prison::getOnTick)
            // Up to 16 fields can be declared here
    ).apply(instance, Prison::new));

    public Prison(String name, Identifier itemIcon, List<PrisonLocation> spawnLocation, List<UpgradeWithMapSpecifics> upgrades, PrisonLocation wardenSpawn, PrisonLocation guardSpawn, PrisonLocation computerTeleport, PrisonLocation blackMarketTeleport, PrisonZone escapeZone, PrisonZone blackMarketExit, Optional<List<Identifier>> onTick) {
        this.name = name;
        this.itemIcon = itemIcon;
        this.itemIconAsItem = Registries.ITEM.get(itemIcon);
        this.cellLocations = spawnLocation;
        this.upgrades = new ArrayList<UpgradeWithMapSpecifics>(upgrades);
        this.wardenSpawn = wardenSpawn;
        this.guardSpawn = guardSpawn;
        this.computerTeleport = computerTeleport;
        this.blackMarketTeleport = blackMarketTeleport;
        this.escapeZone = escapeZone;
        this.blackMarketExit = blackMarketExit;
        this.onTick = onTick;
    }

    public PrisonZone getEscapeZone() {
        return escapeZone;
    }

    public Optional<List<Identifier>> getOnTick() {
        return onTick;
    }

    public String getName() {
        return this.name;
    }

    public List<PrisonLocation> getCellLocations() {
        return cellLocations;
    }

    public PrisonLocation getWardenSpawn() {
        return wardenSpawn;
    }

    public Identifier getItemIcon() {
        return itemIcon;
    }

    public PrisonLocation getComputerTeleport() {
        return computerTeleport;
    }

    public PrisonLocation getGuardSpawn() {
        return guardSpawn;
    }

    public ArrayList<UpgradeWithMapSpecifics> getUpgrades() {
        return upgrades;
    }

    public PrisonLocation getBlackMarketTeleport() {
        return blackMarketTeleport;
    }

    public PrisonZone getBlackMarketExit() {
        return blackMarketExit;
    }
}
