package com.scienjus.smartqq.model
/**
 * 字体
 * @author ScienJus
 * @date 15/12/19.
 */
public class Font {

    public static final Font DEFAULT_FONT = defaultFont();

    static Font defaultFont() {
        Font font = new Font();
        font.setColor("000000");
        font.setStyle([0, 0, 0] as int[]);
        font.setName("宋体");
        font.setSize(10);
        return font;
    }

    int[] style;

    String color;

    String name;

    int size;

}
