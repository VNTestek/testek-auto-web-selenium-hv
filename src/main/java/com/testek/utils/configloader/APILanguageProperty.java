package com.testek.utils.configloader;

import java.util.Objects;

import static com.testek.consts.FrameConst.DataConfig.*;

/**
 * Class for using language local
 */
public class APILanguageProperty {
    private static ThreadLocal<String> LANGUAGE = new ThreadLocal<>();

    public static String getLANGUAGE() {
        String value = LANGUAGE.get();
        return Objects.nonNull(value) && value.contains(LANG_EN) ? LANG_EN : LANG_VI;
    }

    public static void setLANGUAGE(String language) {
        LANGUAGE.set(language);
    }

    public void unload() {
        LANGUAGE.remove(); // Compliant
    }
}
