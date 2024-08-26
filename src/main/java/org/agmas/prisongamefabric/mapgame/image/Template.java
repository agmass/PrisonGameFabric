package org.agmas.prisongamefabric.mapgame.image;

import java.util.HashMap;
import java.util.Map;

public class Template {
    public Map<Character, Byte> characterMap = new HashMap<>();
    public String template;

    public Template(String template) {
        this.template = template;
    }

    public Template put(char character, byte color) {
        characterMap.put(character, color);
        return this;
    }
}
