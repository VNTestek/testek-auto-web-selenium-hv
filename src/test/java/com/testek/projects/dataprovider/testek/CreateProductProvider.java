package com.testek.projects.dataprovider.testek;

import com.testek.datadriven.BaseProvider;
import com.testek.projects.dataprovider.DataPath;
import com.testek.projects.model.CreateProductModel;
import com.testek.utils.configloader.JsonUtils;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class CreateProductProvider extends BaseProvider {
    JsonUtils jsonUtils = JsonUtils.getInstance();


    @DataProvider(name = "Testek_CreateProduct_001_Valid")
    public  Object[][] Testek_CreateProduct_001_Valid (Method method) {

        var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_CREATE_PRODUCT, method.getName());

        // Using Model Class and Data From Json file
        CreateProductModel templateModel = new CreateProductModel();
        return updateDataModel(templateModel, dataList);
    }
}
