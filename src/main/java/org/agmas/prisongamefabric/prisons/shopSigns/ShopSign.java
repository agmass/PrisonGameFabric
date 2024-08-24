package org.agmas.prisongamefabric.prisons.shopSigns;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.command.CommandExecutionContext;
import net.minecraft.command.ReturnValueConsumer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.server.function.MacroException;
import net.minecraft.server.function.Procedure;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.PrisonGameItems;
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
    public boolean buy(ServerPlayerEntity spe) {
        CommandFunction<ServerCommandSource> function = PrisonGameFabric.serverInstance.getCommandFunctionManager().getFunction(successFunction).orElse(null);
        ServerCommandSource playerSpecificSource = PrisonGameFabric.commandSource.withEntity(spe);
        return executeOrRefund(function, playerSpecificSource, spe);
    }


    public boolean executeOrRefund(CommandFunction<ServerCommandSource> function, ServerCommandSource source, ServerPlayerEntity spe) {
        Profiler profiler = PrisonGameFabric.serverInstance.getProfiler();
        profiler.push(() -> {
            return "function " + String.valueOf(function.id());
        });

        try {
            Procedure<ServerCommandSource> procedure = function.withMacroReplaced((NbtCompound)null, PrisonGameFabric.serverInstance.getCommandFunctionManager().getDispatcher());
            CommandManager.callWithContext(source, (context) -> {
                CommandExecutionContext.enqueueProcedureCall(context, procedure, source, ReturnValueConsumer.EMPTY);
            });
        } catch (MacroException var9) {
        } catch (Exception var10) {
            Exception exception = var10;
            spe.sendMessage(Tx.tf(Formatting.RED,  exception.getMessage() + ". You should have been refunded."));
            return false;
        } finally {
            profiler.pop();
        }

        return true;

    }

    public void attemptButFail(ServerPlayerEntity spe) {
        spe.sendMessage(Tx.ttf(Formatting.RED, Text.translatable("purchase.fail").append(" " + name + "!")));
        CommandFunction<ServerCommandSource> function = PrisonGameFabric.serverInstance.getCommandFunctionManager().getFunction(failFunction).orElse(null);
        ServerCommandSource playerSpecificSource = PrisonGameFabric.commandSource.withEntity(spe);
        PrisonGameFabric.serverInstance.getCommandFunctionManager().execute(function, playerSpecificSource);
    }
}
