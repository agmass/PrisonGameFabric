package org.agmas.prisongamefabric;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.agmas.prisongamefabric.items.Keycard;
import org.agmas.prisongamefabric.items.jobItems.LumberjackAxe;
import org.agmas.prisongamefabric.items.jobItems.ShovellingShovel;

public class PrisonGameItems {



    public static final Item ONE_CARD = new Keycard(1);
    public static final Item TWO_CARD = new Keycard(2);
    public static final Item THREE_CARD = new Keycard(3);
    public static final Item LUMBERJACKAXE = new LumberjackAxe(new Item.Settings());
    public static final Item SHOVELSHOVEL = new ShovellingShovel(new Item.Settings());

    public static void initalize() {
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "keycardlevelone"), ONE_CARD);
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "keycardleveltwo"), TWO_CARD);
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "keycardlevelthree"), THREE_CARD);
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "lumberjackaxe"), LUMBERJACKAXE);
        Registry.register(Registries.ITEM, Identifier.of("prisongamefabric", "shovellingshovel"), SHOVELSHOVEL);

    }
}
