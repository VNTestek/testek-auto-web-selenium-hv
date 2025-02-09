package com.testek.projects.dataprovider.testek;

import com.testek.datadriven.BaseProvider;
import com.testek.projects.dataprovider.DataPath;
import com.testek.projects.model.LoginModel;
import com.testek.utils.configloader.JsonUtils;
import lombok.Data;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
public class LoginProvider extends BaseProvider {
    JsonUtils jsonUtils = JsonUtils.getInstance();


    @DataProvider(name = "Testek_Login_001_Valid")
    public  Object[][] Testek_Login_001_Valid(Method method) {

        var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_LOGIN, method.getName());

        // Using Model Class and Data From Json file
        LoginModel templateModel = new LoginModel();
        return updateDataModel(templateModel, dataList);
    }

    @DataProvider(name = "Testek_Login_Demo_Valid")
    public  Object[][] Testek_Login_Demo_Valid(Method method) {

        var dataList = jsonUtils.readDataDrivenFromJSON(DataPath.DATA_LOGIN_DEMO, method.getName());

        // Using Model Class and Data From Json file
        LoginModel templateModel = new LoginModel();
        return updateDataModel(templateModel, dataList);
    }
}
