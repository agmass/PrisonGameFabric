package org.agmas.prisongamefabric.mapgame.microUis;

import com.mojang.authlib.properties.PropertyMap;
import eu.pb4.polymer.core.impl.ui.MicroUi;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.agmas.prisongamefabric.PrisonGameFabric;
import org.agmas.prisongamefabric.util.Tx;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class HireMicroUI extends MicroUi {
    public HireMicroUI(ServerPlayerEntity spe) {
        super((int) Math.ceil((double) spe.getServer().getCurrentPlayerCount() /9));
        this.title(Tx.tf(Formatting.GRAY, "Hire"));
        AtomicInteger loop = new AtomicInteger();
        spe.getServer().getPlayerManager().getPlayerList().forEach((a)->{
            if (!a.equals(spe)) {
                ItemStack playerRepresenter = Items.PLAYER_HEAD.getDefaultStack();
                playerRepresenter.set(DataComponentTypes.PROFILE, new ProfileComponent(a.getGameProfile()));
                playerRepresenter.set(DataComponentTypes.ITEM_NAME, Tx.ttf(Formatting.GOLD, a.getName().copy()));
                this.slot(loop.get(), playerRepresenter, (aa, b, c, d) -> new SelectRoleMicroUI(spe, a));
                loop.getAndIncrement();
            }
        });
        if (loop.get() == 0) {
            spe.sendMessage(Tx.ttf(Formatting.RED, Text.translatable("error.noPlayersToHire")));
        } else {
            this.open(spe);
        }
    }
}
