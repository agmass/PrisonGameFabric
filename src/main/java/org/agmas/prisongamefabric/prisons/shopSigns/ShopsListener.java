package org.agmas.prisongamefabric.prisons.shopSigns;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Map;

public class ShopsListener extends JsonDataLoader implements IdentifiableResourceReloadListener {

    private static final Gson GSON = new GsonBuilder().create();
    private static final Logger LOGGER = LogUtils.getLogger();
    public ShopsListener() {
        super(GSON, "shops");
    }

    @Override
    protected void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
        Log.info(LogCategory.GENERAL,"Loading " + prepared.size() + " Shop Signs");
        PrisonGameFabric.availableSigns.clear();
        prepared.forEach((id, json)->{
            try {

                DataResult<ShopSign> result = ShopSign.CODEC.parse(JsonOps.INSTANCE, json);
                PrisonGameFabric.availableSigns.put(id, result.getOrThrow());
                Log.info(LogCategory.GENERAL,"Added Shop Sign " + result.getOrThrow().name);
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
        return Identifier.of("pbf", "shopslistener");
    }

    @Override
    public Collection<Identifier> getFabricDependencies() {
        return IdentifiableResourceReloadListener.super.getFabricDependencies();
    }

}
