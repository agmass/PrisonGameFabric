package org.agmas.prisongamefabric.commands.argumentTypes;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.agmas.prisongamefabric.util.Roles.Role;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RoleArgumentType implements ArgumentType<Role> {


    public static RoleArgumentType role() {
        return new RoleArgumentType();
    }

    private static final Map<String,Role> ROLE_MAP = Map.of(
            "warden", Role.WARDEN,
            "guard", Role.GUARD,
            "nurse", Role.NURSE,
            "prisoner", Role.PRISONER,
            "swat", Role.SWAT,
            "criminal", Role.CRIMINAL,
            "out", Role.OUTOFGAME
    );

    public static <S> Role getRole(String name, CommandContext<S> context) {
        // Note that you should assume the CommandSource wrapped inside of the CommandContext will always be a generic type.
        // If you need to access the ServerCommandSource make sure you verify the source is a server command source before casting.
        return context.getArgument(name, Role.class);
    }

    @Override
    public Role parse(StringReader reader) throws CommandSyntaxException {
        int argBeginning = reader.getCursor(); // The starting position of the cursor is at the beginning of the argument.
        if (!reader.canRead()) {
            reader.skip();
        }
        while (reader.canRead() && (Character.isLetterOrDigit(reader.peek()) || reader.peek() == '-')) { // "peek" provides the character at the current cursor position.
            reader.skip(); // Tells the StringReader to move it's cursor to the next position.
        }
        String roleString = reader.getString().substring(argBeginning, reader.getCursor());
        return ROLE_MAP.getOrDefault(roleString, Role.OUTOFGAME);
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return ArgumentType.super.listSuggestions(context, builder);
    }


    @Override
    public Collection<String> getExamples() {
        return ROLE_MAP.keySet();
    }
}
