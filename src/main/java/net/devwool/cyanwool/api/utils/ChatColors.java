package net.devwool.cyanwool.api.utils;

import java.awt.Color;
import java.util.regex.Pattern;

/**
 * 
 * From: Spout-Vanilla
 * 
 * @author Developed
 *
 */
public enum ChatColors {
    BLACK('0', Color.BLACK, Color.BLACK), DARK_BLUE('1', new Color(0, 0, 170), new Color(0, 0, 42)), DARK_GREEN('2', new Color(0, 170, 0), new Color(0, 42, 0)), DARK_AQUA('3', new Color(0, 170, 170), new Color(0, 42, 42)), DARK_RED('4', new Color(170, 0, 0), new Color(42, 0, 0)), PURPLE('5', new Color(170, 0, 170), new Color(42, 0, 42)), GOLD('6', new Color(255, 170, 0), new Color(42, 42, 0)), GRAY('7', new Color(170, 170, 170), new Color(42, 42, 42)), DARK_GRAY('8', new Color(85, 85, 85), new Color(21, 21, 21)), BLUE('9', new Color(85, 85, 255), new Color(21, 21, 63)), GREEN('a', new Color(85, 255, 85), new Color(21, 63, 21)), AQUA('b', new Color(85, 255, 255), new Color(21, 63, 63)), RED('c', new Color(255, 85, 85), new Color(63, 21, 21)), PINK('d', new Color(255, 85, 255), new Color(63, 21, 63)), YELLOW('e', new Color(255, 255, 85), new Color(63, 63, 21)), WHITE('f', Color.WHITE, new Color(63, 63, 63)), OBFUSCATED('k'), BOLD('l'), UNDERLINE('n'), ITALIC('o'), RESET('r');

    public static final char COLOR_CHAR = '\u00A7';
    private final char c;
    private final Color foregroundColor, backgroundColor;
    private static final Pattern STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf(COLOR_CHAR) + "[0-9A-FK-OR]");

    private ChatColors(char c) {
        this(c, null, null);
    }

    private ChatColors(char c, Color foregroundColor, Color backgroundColor) {
        this.c = c;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    public char getChar() {
        return c;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public String toString() {
        return "" + COLOR_CHAR + c;
    }

    // From Bukkit: ChatColor.java
    public static String translateAlternateColorCodes(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = ChatColors.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    public static String stripColor(final String input) {
        if (input == null) {
            return null;
        }

        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }
}