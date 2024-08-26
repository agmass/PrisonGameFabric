package org.agmas.prisongamefabric.mapgame.image;

import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;

import java.util.HashSet;
import java.util.Set;

/**
 * A canvas which can be drawn on to generate frames for the terminal.
 */
public class Image {
    private final Set<Integer> affectedPixels = new HashSet<>();
    public Byte[][] bitmap;
    public int width;
    public int height;

    /**
     * Creates a new image with the specified dimensions.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public Image(int width, int height) {
        bitmap = new Byte[height][width];
        this.width = width;
        this.height = height;

        setBackgroundColor((byte) -1);
    }

    /**
     * Sets the background color of the image. This does not overwrite any previously drawn elements.
     * @param color The color of the background.
     * @return The image.
     */
    public Image setBackgroundColor(byte color) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                var pixel = calculatePixelNumber(x, y);

                if (affectedPixels.contains(pixel))
                    continue; // Avoid drawing over elements when refreshing background

                setPixel(x, y, color, false);
            }
        }

        return this;
    }

    /**
     * Fills a rectangle with a color.
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param color The color.
     * @return The image.
     */
    public Image fillRectangle(int x1, int y1, int x2, int y2, byte color) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                setPixel(x, y, color);
            }
        }

        return this;
    }

    /**
     * Applies a template to the image at the specified coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param template The template.
     * @return The image.
     */
    public Image applyTemplate(int x, int y, Template template) {
        int startingX = x;

        for (char character : template.template.toCharArray()) {
            if (character == '\n') {
                y++;
                x = startingX;
                continue;
            }

            if (!template.characterMap.containsKey(character)) {
                x++;
                continue;
            }

            byte color = template.characterMap.get(character);
            setPixel(x, y, color);

            x++;
        }

        return this;
    }

    /**
     * Applies a template to the image at coordinates (0, 0)
     * @param template The template.
     * @return The image.
     */
    public Image applyTemplate(Template template) {
        return applyTemplate(0, 0, template);
    }

    /**
     * Sets an individual pixel to a color.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param color The color.
     * @param markAffected If the pixel should be marked as "affected" (if true, setting the background color won't override this pixel.)
     * @return The image.
     */
    public Image setPixel(int x, int y, byte color, boolean markAffected) {
        if (y < 0 || y >= bitmap.length || x >= bitmap[y].length || x < 0)
            return this;

        bitmap[y][x] = color;

        if (markAffected)
            affectedPixels.add(calculatePixelNumber(x, y));

        return this;
    }

    /**
     * Sets an individual pixel to a color.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param color The color.
     * @return The image.
     */
    public Image setPixel(int x, int y, byte color) {
        return setPixel(x, y, color, true);
    }

    /**
     * Draws the text at the specified coordinates. Allowed character set is [a-zA-Z0-9].
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param text The text.
     * @return The image.
     */
    public Image drawText(int x, int y, String text) {
        byte black = Blocks.BLACK_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);
        byte white = Blocks.WHITE_CONCRETE.getDefaultMapColor().getRenderColorByte(MapColor.Brightness.NORMAL);

        for (char character : text.toCharArray()) {
            int[] border = CharacterMap.borderMap.get(character);
            int[] font = CharacterMap.fontMap.get(character);

            int width = border[0];
            drawFromMap(x, y, white, border);
            drawFromMap(x + 1, y + 1, black, font);

            x += width - 1;
        }

        return this;
    }

    /**
     * Draws a border at the specified coordinates with the specified radius.
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param radius The radius of the border.
     * @param color The color of the border.
     * @return The image.
     */
    public Image drawBorderRadius(int x1, int y1, int x2, int y2, int radius, byte color) {
        fillRectangle(x1 + radius, y1, x2 - radius, y1, color);
        fillRectangle(x1, y1 + radius, x1, y2 - radius, color);
        fillRectangle(x2, y1 + radius, x2, y2 - radius, color);
        fillRectangle(x1 + radius, y2, x2 - radius, y2, color);

        for (int x = radius; x > 0; x--) {
            setPixel(x1 + x, y1 + radius - x, color);
            setPixel(x2 - x, y1 + radius - x, color);
            setPixel(x1 + x, y2 - radius + x, color);
            setPixel(x2 - x, y2 - radius + x, color);
        }

        return this;
    }

    /**
     * Fills the pixels inside the border.
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param radius The radius of the border.
     * @param color The color of the border.
     * @param bias 1/-1 if the top/bottom of the border should be ignored, or 0 if none.
     * @return The image.
     */
    public Image fillInsideBorder(int x1, int y1, int x2, int y2, int radius, byte color, int bias) {
        for (int x = x1 + 1; x < x2; x++) {
            for (int y = y1 + 1; y < y2; y++) {
                boolean inTopRadius = bias != 1 && y <= y1 + radius;

                if ((inTopRadius || y >= y2 - radius) && (inTopRadius || bias != -1)) {
                    int distance = inTopRadius ? radius - (y - y1) : radius - (y2 - y);

                    if (x <= distance || (x2 - x) <= distance)
                        continue;
                }

                setPixel(x, y, color);
            }
        }

        return this;
    }

    /**
     * Fills the pixels inside the border.
     * @param x1 The first x coordinate.
     * @param y1 The first y coordinate.
     * @param x2 The second x coordinate.
     * @param y2 The second y coordinate.
     * @param radius The radius of the border.
     * @param color The color of the border.
     * @return The image.
     */
    public Image fillInsideBorder(int x1, int y1, int x2, int y2, int radius, byte color) {
        return fillInsideBorder(x1, y1, x2, y2, radius, color, 0);
    }

    private void drawFromMap(int x, int y, byte color, int[] data) {
        int startingX = x;
        var width = data[0];

        for (int i = 1; i < data.length; i++) {
            String binary = String
                    .format("%1$" + width + "s", Integer.toBinaryString(data[i]))
                    .replace(' ', '0');

            for (char pixel : binary.toCharArray()) {
                if (pixel == '0') {
                    x++;
                    continue;
                }

                setPixel(x, y, color);
                x++;
            }

            y++;
            x = startingX;
        }
    }

    private int calculatePixelNumber(int x, int y) {
        return y * width + x;
    }
}
