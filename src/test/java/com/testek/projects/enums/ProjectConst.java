package com.testek.projects.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectConst {

    static String WEB_URL = "https://testek.vn/lab/auto";
//    @Getter
//    public static class ModuleURL {
//
//        public final static String DASHBOARD_PATH = WEB_URL + "/dashboard";
//        public final static String PRODUCT_PATH = WEB_URL + "/product";
//    }

    @Getter
    public enum ModuleURL {
        DASHBOARD("Dashboard", WEB_URL + "/dashboard"),
        PRODUCT("Product", WEB_URL + "/product");

        private final String name;
        private final String path;

        ModuleURL(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
