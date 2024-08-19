package org.agmas.prisongamefabric.prisons.shopSigns;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.prisons.upgrades.PrisonUpgrade;
import org.agmas.prisongamefabric.util.Tx;

import java.util.Optional;

public class ShopSign {
    public final String name;
    public final Identifier successFunction;
    public final Identifier failFunction;
    public final Integer price;

    public static final Codec<ShopSign> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(ShopSign::getName),
            Identifier.CODEC.fieldOf("purchase").forGetter(ShopSign::getSuccessFunction),
            Identifier.CODEC.fieldOf("fail").forGetter(ShopSign::getFailFunction),
            Codec.INT.fieldOf("price").forGetter(ShopSign::getPrice)
            // Up to 16 fields can be declared here
    ).apply(instance, ShopSign::new));

    public ShopSign(String name, Identifier successFunction, Identifier failFunction, Integer price) {
        this.name = name;
        this.successFunction = successFunction;
        this.failFunction = failFunction;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Identifier getSuccessFunction() {
        return successFunction;
    }

    public Identifier getFailFunction() {
        return failFunction;
    }
    public void buy(ServerPlayerEntity spe) {
        CommandFunction<ServerCommandSource> function = PrisonGameFabric.serverInstance.getCommandFunctionManager().getFunction(successFunction).orElse(null);
        ServerCommandSource playerSpecificSource = PrisonGameFabric.commandSource.withEntity(spe);
        PrisonGameFabric.serverInstance.getCommandFunctionManager().execute(function, playerSpecificSource);
    }

    public void attemptButFail(ServerPlayerEntity spe) {
        spe.sendMessage(Tx.tf(Formatting.RED, "You don't have enough money to buy " + name + "!"));
        CommandFunction<ServerCommandSource> function = PrisonGameFabric.serverInstance.getCommandFunctionManager().getFunction(failFunction).orElse(null);
        ServerCommandSource playerSpecificSource = PrisonGameFabric.commandSource.withEntity(spe);
        PrisonGameFabric.serverInstance.getCommandFunctionManager().execute(function, playerSpecificSource);
    }
}
