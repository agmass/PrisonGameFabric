package org.agmas.prisongamefabric.prisons;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.advancement.*;
import net.minecraft.network.message.SentMessage;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.upgrades.UpgradeWithMapSpecifics;
import org.slf4j.Logger;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ResourceListener extends JsonDataLoader implements IdentifiableResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LogUtils.getLogger();
    public ResourceListener() {
        super(GSON, "prisons");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        Log.info(LogCategory.GENERAL,"Loading " + prepared.size() + " Prisons");
        PrisonGameFabric.availablePrisons.clear();
        prepared.forEach((id, json)->{
            try {

                DataResult<Prison> result = Prison.CODEC.parse(JsonOps.INSTANCE, json);

                Prison prison = result.resultOrPartial(LOGGER::error).orElseThrow();
                Log.info(LogCategory.GENERAL,"Added prison: " + prison.getName());
                prison.upgrades.add(new UpgradeWithMapSpecifics(Identifier.of("prisongamefabric", "swat"), Optional.empty(), Optional.empty(), Optional.empty()));
                PrisonGameFabric.availablePrisons.add(prison);
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
        return Identifier.of("pbf", "prisonlistener");
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return IdentifiableResourceReloadListener.super.getFabricDependencies();
    }
}
