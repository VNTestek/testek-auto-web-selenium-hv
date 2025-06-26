package com.testek.projects.dataprovider;

import com.testek.consts.FrameConst;

public interface DataPath {
    String env = FrameConst.ExecuteConfig.EXE_ENV.toLowerCase();

    String DATA_LOGIN_DEMO = "data/" + env + "/json/loginData.json";
    String DATA_LOGIN = "data/" + env + "/json/loginDataFull.json";
    String DATA_CREATE_PRODUCT = "data/" + env + "/json/createProduct.json";
}
