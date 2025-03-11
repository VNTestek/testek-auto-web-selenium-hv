package com.testek.projects.common;

import com.testek.consts.FrameConst;
import com.testek.database.DatabaseInfo;
import com.testek.database.DatabaseType;
import com.testek.utils.LogUtils;
import com.testek.utils.configloader.AbsPropertyUtils;
import com.testek.utils.configloader.ResourceReader;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.JAPANESE;

/**
 * Properties which loading from project configuration
 */
public class PropertiesUtils extends AbsPropertyUtils {
    private static ResourceBundle resourceConfig;

    @Getter
    public static PropertiesUtils instance = new PropertiesUtils();

    private PropertiesUtils() {
    }

    /**
     * Updates/Setting the language used in the website
     *
     * @param language :
     */
    private PropertiesUtils(String language) {
        Locale locale = Locale.ENGLISH;
        if ("vi".equals(language)) {
            locale = new Locale("vi", "VI");
        }
        resourceConfig = ResourceBundle.getBundle("language", locale);
    }

    /**
     * Create a new Properties object
     */
    public static PropertiesUtils getInstance(String language) {
        return new PropertiesUtils(language);
    }

    /**
     * Return the language value of keyword into data model
     *
     * @param key : The key at bundle language
     * @return The value of this key
     */
    public static String getLanguageValue(String key) {
        try {
            if (resourceConfig == null) getInstance(properties.getProperty("language"));
            return resourceConfig.getString(key);
        } catch (Exception e) {
            LogUtils.error("VException: getLanguageValue: " + e.getMessage());
            return null;
        }
    }

    /**
     * Update property
     *
     * @param property : Properties object
     * @param key      : Key
     * @param value    : Value
     */
    @Override
    public void updateProperty(Properties property, String key, String value, boolean... hasBoolean) {
        if (hasBoolean.length >= 1 && hasBoolean[0]) {
            property.setProperty(key, String.valueOf(value));
        } else if (Objects.nonNull(value) && !value.isEmpty()) {
            property.setProperty(key, value);
        }
    }

    /**
     * Update env and property
     */
    public void updateMavenProperties(Properties property, JSONObject configObjects) {
        /*
        1. Check lai su dung chung System Env
        2. Neu da co trong system thi bo qua luon k can update
        3. Neu chua co trong sytem thi se thuc hien update vao system
         */

        // Read properties from Maven
        Properties properties = System.getProperties();
        String browser = properties.getProperty("browser", null);
        String target = properties.getProperty("target", null);
        String headless = properties.getProperty("headless", null);
        String remoteIP = properties.getProperty("remote_url", null);
        String remotePort = properties.getProperty("remote_port", null);
        String language = properties.getProperty("language", null);
        String appVersion = properties.getProperty("app_version", null);
        String appEnvironment = properties.getProperty("app_environment", null);
        String databaseConnect = properties.getProperty("database_connect", "false");
        String collectRes = properties.getProperty("collectRes", "false");

        updateProperty(property, "browser", browser);
        updateProperty(property, "target", target);
        updateProperty(property, "headless", headless);
        updateProperty(property, "remote_url", remoteIP);
        updateProperty(property, "remote_port", remotePort);
        updateProperty(property, "language", language);
        updateProperty(property, "app_version", appVersion);
        updateProperty(property, "app_environment", appEnvironment);
        updateProperty(property, "database_connect", databaseConnect);
        updateProperty(property, "collectRes", collectRes);

        String exeEnv = property.getProperty("app_environment", "SIT");
        // Application configuration
        JSONObject configJSON = configObjects.getJSONObject("config");
        JSONObject appJSON = configJSON.getJSONObject("env");
        // Database Config
        JSONObject envConfig = (JSONObject) appJSON.get(exeEnv.toLowerCase());


        updateProperty(property, "base_url", envConfig.getString("baseUrl"));
        updateProperty(property, "account", envConfig.getString("account"));
        updateProperty(property, "password", envConfig.getString("password"));
        updateProperty(property, "api_url", envConfig.getString("apiUrl"));
        /* Add more properties here */



        /* Add properties for Farm */

        /* List of database connection */
        FrameConst.DATABASE_CONNECT_LIST.clear();
        if (!Boolean.parseBoolean(databaseConnect)) return;
        /* TODO: Need to re-check */
        JSONArray databaseEnvList = (JSONArray) appJSON.get("database");
        databaseEnvList.toList().forEach(d -> {
            HashMap tmpDB = (HashMap) d;
            DatabaseType type = DatabaseType.valueOf(String.valueOf(tmpDB.get("type")).toUpperCase());
            FrameConst.DATABASE_CONNECT_LIST.add(DatabaseInfo.builder().url(String.valueOf(tmpDB.get("url"))).name(String.valueOf(tmpDB.get("name"))).userName(String.valueOf(tmpDB.get("username"))).password(String.valueOf(tmpDB.get("password"))).configPath(String.valueOf(tmpDB.get("config"))).type(type).build());
        });

        // Collect result
        //FrameConst.COLLECT_RESPONSE = Boolean.parseBoolean(property.getProperty("collectRes", "false"));
    }


    public static void loadLanguageBundle(String language) {
        Locale locale = new Locale("vi", "VI");
        switch (language.toLowerCase()) {
            case "en":
                locale = ENGLISH;
                break;
            case "ja":
                locale = JAPANESE;
                break;
            case "vi":
            default:    // Default value: VI
                break;
        }
        resourceConfig = ResourceBundle.getBundle("language", locale);
    }

    /**
     * Load all the project's properties
     */
    private static void loadConfig() {
        if (Objects.nonNull(properties)) return;

        String resourceFolderPath = "src/test/resources/config";
        List<String> files = loadFilesFromResourceFolder(resourceFolderPath);

        try {
            properties = new Properties();
            for (String f : files) {
                // Check file is not .properties, skip it
                if (!f.endsWith(".properties")) continue;

                Properties tempProp = new Properties();
                tempProp.load(new FileInputStream(f));
                properties.putAll(tempProp);
            }
        } catch (Exception e) {
            LogUtils.error("VException: loadAllFiles: " + e.getMessage());
        }
    }

    /**
     * Load all env configuration
     *
     * @return : A JSONObject
     */
    public static JSONObject loadEnvConfiguration() {
        // Add all property
        JSONObject envConfigJSON = new JSONObject();

        String configPath = "config/environment.json";
        JSONObject configObject = new JSONObject(Objects.requireNonNull(readDataFromFile(configPath)));
        envConfigJSON.put("config", configObject);
        return envConfigJSON;
    }

    @Override
    public void executionInfo(Properties properties) {
        System.out.println("\n\n==================EXECUTION INFO========================");
        System.out.println("\tWEB AUTOMATION - PRODUCT MANAGEMENT SYSTEM");
//        System.out.printf("\tEnvironment: %s%n", FrameConst.EXE_ENV);
//        System.out.printf("\tExecution Module: %s%n", FrameConst.EXECUTED_MODULES);
//        System.out.printf("\tExecution Type: %s%n", FrameConst.CATEGORY_TYPE);
//        System.out.printf("\tLanguage: %s%n", FrameConst.APP_LANGUAGE);
//        System.out.printf("\tTesting Version: %s%n", FrameConst.APP_VERSION);
//        System.out.printf("\tCollect Response: %s%n", FrameConst.COLLECT_RESPONSE);
        System.out.println("========================================================\n\n");
    }

    @Override
    public void loadAllProperties() {
        /* Load environment configuration */
        JSONObject envConfigs = loadEnvConfiguration();

        /* Load all configuration */
        loadConfig();

        /* Update properties */
        updateMavenProperties(properties, envConfigs);
        executionInfo(properties);
    }


    public static List<String> loadFilesFromResourceFolder(String folderPath) {
        List<String> files = new LinkedList<>();
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                if (file.isFile()) {
                    files.add(file.getAbsolutePath());
                }
            }
        }
        return files;
    }

    /**
     * Read data from resource
     *
     * @param filePath : file path
     */
    public static String readDataFromFile(String filePath) {
        try {
            LogUtils.info("Read configuration data from: " + filePath);
            return ResourceReader.readDataFromResource(filePath);
        } catch (IOException e) {
            LogUtils.error("Error: " + e.getMessage());
        }
        return null;
    }


}
