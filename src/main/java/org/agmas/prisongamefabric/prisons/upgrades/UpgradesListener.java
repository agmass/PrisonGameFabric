package org.agmas.prisongamefabric.prisons.upgrades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.block.AbstractBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.block.UpgradeLockedBlock;
import org.agmas.prisongamefabric.prisons.Prison;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Map;

public class UpgradesListener extends JsonDataLoader implements IdentifiableResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LogUtils.getLogger();
    public UpgradesListener() {
        super(GSON, "upgrades");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        Log.info(LogCategory.GENERAL,"Loading " + prepared.size() + " Upgrades");
        PrisonGameFabric.availablePrisonUpgrades.clear();
        prepared.forEach((id, json)->{
            try {

                DataResult<PrisonUpgrade> result = PrisonUpgrade.CODEC.parse(JsonOps.INSTANCE, json);
                PrisonGameFabric.availablePrisonUpgrades.put(id, result.getOrThrow());
                Log.info(LogCategory.GENERAL,"Added Upgrade " + result.getOrThrow());
            } catch (Exception exception) {
                Log.error(LogCategory.GENERAL,exception.getMessage());
            }
        });

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public Identifier getFabricId() {
        return Identifier.of("pbf", "upgradeslistener");
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return IdentifiableResourceReloadListener.super.getFabricDependencies();
    }
}
