package com.testek.study.lesson122.provider;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginProvider {
    @DataProvider(name = "RISE_Login")
    public Object[][] RISE_Login_Data(Method method) {
        // Read content from data file
        String dataFile = System.getProperty("user.dir") + File.separator + "src/test/java/lession12/data/loginData.json";
        JSONObject jsonData = readContentFromJsonFile(dataFile);

        // Convert to a map and parse to data list
        Map dataMap = jsonData.toMap();
        List jsonList = (List<HashMap>) dataMap.get(method.getName());

        // Add data to Objects
        Object[][] result  = new Object[jsonList.size()][1];
        for (int i = 0; i < jsonList.size(); i++) {
            result[i][0] = jsonList.get(i);
        }
        return result;
    }


    /**
     * Read data from json file
     *
     * @param filePath : The file path
     * @return : A JSONObject
     */
    private JSONObject readContentFromJsonFile(String filePath) {
        try {
            File file = new File(filePath);
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (Exception e) {
            System.out.println("Error reading : " + e.getMessage());
        }
        return null;
    }
}
