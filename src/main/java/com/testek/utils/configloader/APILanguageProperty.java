package com.testek.utils.configloader;

import java.util.Objects;

import static com.testek.consts.FrameConst.DataConfig.*;

/**
 * Class for using language local
 */
public class APILanguageProperty {
    private static ThreadLocal<String> languageLocal = new ThreadLocal<>();

    public static String getLanguageLocal() {
        String value = languageLocal.get();
        return Objects.nonNull(value) && value.contains(LANG_EN) ? LANG_EN : LANG_VI;
    }

    public static void setLanguageLocal(String language) {
        languageLocal.set(language);
    }
}
