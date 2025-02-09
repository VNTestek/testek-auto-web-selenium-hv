package com.testek.projects.dataprovider;

import static com.testek.consts.FrameConst.ProjectConfig.APPLICATION_ENV;

public interface DataPath {
    String DATA_LOGIN_DEMO = "data/" + APPLICATION_ENV+ "/json/loginData.json";
    String DATA_LOGIN = "data/" + APPLICATION_ENV+ "/json/loginDataFull.json";

}
