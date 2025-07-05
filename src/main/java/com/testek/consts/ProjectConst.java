package com.testek.consts;

import com.github.rzymek.opczip.reader.ordered.OrderedZipStreamReader;
import lombok.Getter;
import lombok.Setter;

import static com.testek.consts.FrameConst.AppConfig.APP_DOMAIN;

@Getter
@Setter
public class ProjectConst {


    @Getter
    public enum ModuleURL {
        DASHBOARD("Dashboard", APP_DOMAIN + "/dashboard"),
        PRODUCT("Product", APP_DOMAIN + "/product"),
        ORDER("Order", APP_DOMAIN + "/order"),
        CREATESUPPLIER("Create Supplier", APP_DOMAIN + "/create-supplier"),
        SUPPLIER("Supplier", APP_DOMAIN + "/supplier");

        private final String name;
        private final String path;

        ModuleURL(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    @Getter
    public enum Databases {
        API_TESTING("APITESTING");

        private final String name;

        Databases(String name) {
            this.name = name;
        }
    }


    @Getter
    public enum DBSchema {
        TESTEK_PRODUCT("products", "products", "products", "products");
        private final String devEnv;
        private final String sitEnv;
        private final String uatEnv;
        private final String prdEnv;

        DBSchema(String devEnv, String sitEnv, String uatEnv, String prdEnv) {
            this.devEnv = devEnv;
            this.sitEnv = sitEnv;
            this.uatEnv = uatEnv;
            this.prdEnv = prdEnv;
        }
    }
}
