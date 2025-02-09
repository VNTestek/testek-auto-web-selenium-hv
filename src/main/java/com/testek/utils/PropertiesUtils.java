//package com.testek.utils;
//
//import com.testek.consts.FrameConst;
//import com.testek.database.DatabaseInfo;
//import com.testek.database.DatabaseType;
//import com.testek.utils.configloader.ResourceReader;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.*;
//
//import static java.util.Locale.ENGLISH;
//import static java.util.Locale.JAPANESE;
//
///**
// * Properties which loading from project configuration
// */
//public class PropertiesUtils {
//    private static Properties properties;
//    private static FileInputStream file;
//    private static String ENV_CONFIG_PATH = "src/test/resources/config/environment.json";
//    private static ResourceBundle resourceConfig;
//
//    /**
//     * Updates/Setting the language used in the website
//     *
//     * @param language :
//     */
//    private PropertiesUtils(String language) {
//        Locale locale = Locale.ENGLISH;
//        if ("vi".equals(language)) {
//            locale = new Locale("vi", "VI");
//        }
//        resourceConfig = ResourceBundle.getBundle("language", locale);
//    }
//
//    /**
//     * Create a new Properties object
//     */
//    public static PropertiesUtils getInstance(String language) {
//        return new PropertiesUtils(language);
//    }
//
//    /**
//     * Return the language value of keyword into data model
//     *
//     * @param key : The key at bundle language
//     * @return The value of this key
//     */
//    public static String getLanguageValue(String key) {
//        try {
//            if (resourceConfig == null) getInstance(properties.getProperty("language"));
//            return resourceConfig.getString(key);
//        } catch (Exception e) {
//            Log.error("VException: getLanguageValue: " + e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * Load all the project's properties
//     */
//    public static synchronized void loadConfiguration() {
//        /* Load environment configuration */
//        JSONObject envConfigs = loadEnvConfiguration();
//
//        /* Load all configuration */
//        loadConfig();
//
//        /* Update properties */
//        updateMavenProperties(properties, envConfigs);
//    }
//
//    /**
//     * Update env and property
//     */
//    private static void updateMavenProperties(Properties property, JSONObject configObjects) {
//        // Read properties from Maven
//        Properties properties = System.getProperties();
//        String browser = properties.getProperty("browser", null);
//        String target = properties.getProperty("target", null);
//        String headless = properties.getProperty("headless", null);
//        String remoteIP = properties.getProperty("remote_url", null);
//        String remotePort = properties.getProperty("remote_port", null);
//        String language = properties.getProperty("language", null);
//        String appVersion = properties.getProperty("app_version", null);
//        String appEnvironment = properties.getProperty("app_environment", null);
//        String databaseConnect = properties.getProperty("database_connect", "false");
//        String collectRes = properties.getProperty("collectRes", "false");
//
//        updateProperty(property, "browser", browser);
//        updateProperty(property, "target", target);
//        updateProperty(property, "headless", headless);
//        updateProperty(property, "remoteIP", remoteIP);
//        updateProperty(property, "remotePort", remotePort);
//        updateProperty(property, "language", language);
//        updateProperty(property, "appVersion", appVersion);
//        updateProperty(property, "appVersion", appEnvironment);
//
//        String exeEnv = properties.getProperty("app_environment", "SIT");
//        // Application configuration
//        JSONObject configJSON = configObjects.getJSONObject("config");
//        JSONObject appJSON = configJSON.getJSONObject("env");
//        // Database Config
//        JSONObject envConfig = (JSONObject) appJSON.get(exeEnv.toLowerCase());
//
//
//        updateProperty(property, "base-url", envConfig.getString("baseUrl"));
//        updateProperty(property, "account", envConfig.getString("account"));
//        updateProperty(property, "password", envConfig.getString("password"));
//        updateProperty(property, "api-url", envConfig.getString("apiUrl"));
//        /* Add more properties here */
//
//
//
//        /* Add properties for Farm */
//
//        /* List of database connection */
//        FrameConst.DATABASE_CONNECT_LIST.clear();
//        if (!Boolean.parseBoolean(databaseConnect)) return;
//        /* TODO: Need to re-check */
//        JSONArray databaseEnvList  =(JSONArray)  appJSON.get("database");
//        databaseEnvList.toList().forEach(d -> {
//            HashMap tmpDB = (HashMap) d;
//            DatabaseType type = DatabaseType.valueOf(String.valueOf(tmpDB.get("type")).toUpperCase());
//            FrameConst.DATABASE_CONNECT_LIST.add(DatabaseInfo.builder().url(String.valueOf(tmpDB.get("url"))).name(String.valueOf(tmpDB.get("name"))).userName(String.valueOf(tmpDB.get("username"))).password(String.valueOf(tmpDB.get("password"))).configPath(String.valueOf(tmpDB.get("config"))).type(type).build());
//        });
//
//        // Collect result
//        //FrameConst.COLLECT_RESPONSE = Boolean.parseBoolean(property.getProperty("collectRes", "false"));
//    }
//
//
//    private static void loadLanguageBundle(String language) {
//        Locale locale = new Locale("vi", "VI");
//        switch (language.toLowerCase()) {
//            case "en":
//                locale = ENGLISH;
//                break;
//            case "ja":
//                locale = JAPANESE;
//                break;
//            case "vi":
//            default:    // Default value: VI
//                break;
//        }
//        resourceConfig = ResourceBundle.getBundle("language", locale);
//    }
//
//    /**
//     * Load all the project's properties
//     */
//    private static void loadConfig() {
//        if (Objects.nonNull(properties)) return;
//
//        String resourceFolderPath = "src/test/resources/config";
//        List<String> files = loadFilesFromResourceFolder(resourceFolderPath);
//
//        try {
//            properties = new Properties();
//            for (String f : files) {
//                // Check file is not .properties, skip it
//                if (!f.endsWith(".properties")) continue;
//
//                Properties tempProp = new Properties();
//                file = new FileInputStream(FrameConst.PROJECT_PATH + f);
//                tempProp.load(file);
//                properties.putAll(tempProp);
//            }
//        } catch (Exception e) {
//            Log.error("VException: loadAllFiles: " + e.getMessage());
//        }
//    }
//
//    private void updateMavenProperties(Properties property) {
//        Properties properties = System.getProperties();
//        String lang = properties.getProperty("appLanguage", null);
//        String remote = properties.getProperty("executionType", null);
//        String remoteIP = properties.getProperty("remoteIP", null);
//        String headless = properties.getProperty("headless", null);
//        String appVersion = properties.getProperty("appVersion", null);
//        String appEnvironment = properties.getProperty("appEnvironment", null);
//        if (Objects.nonNull(lang) && !lang.isEmpty()) property.setProperty("language", lang);
//        if (Objects.nonNull(remote) && !remote.isEmpty()) property.setProperty("target", remote);
//        if (Objects.nonNull(remoteIP) && !remoteIP.isEmpty()) property.setProperty("remote_url", remoteIP);
//        if (Objects.nonNull(headless) && !headless.isEmpty()) property.setProperty("headless", headless);
//        if (Objects.nonNull(appVersion) && !appVersion.isEmpty())
//            property.setProperty("appVersion", appVersion);
//        if (Objects.nonNull(appEnvironment) && !appEnvironment.isEmpty())
//            property.setProperty("appEnvironment", appEnvironment);
//    }
//
//    /**
//     * Load all env configuration
//     *
//     * @return : A JSONObject
//     */
//    public static JSONObject loadEnvConfiguration() {
//        // Add all property
//        JSONObject envConfigJSON = new JSONObject();
//
//        String configPath = "config/environment.json";
//        JSONObject configObject = new JSONObject(Objects.requireNonNull(readDataFromFile(configPath)));
//        envConfigJSON.put("config", configObject);
//        return envConfigJSON;
//    }
//
//    public static String getValue(String key) {
//        String keyVal = null;
//        try {
//            if (file == null) {
//                properties = new Properties();
//                String relPropertiesFilePathDefault = "src/test/resources/config/config.properties";
//                file = new FileInputStream(FrameConst.PROJECT_PATH + relPropertiesFilePathDefault);
//                properties.load(file);
//                file.close();
//            }
//            // Lấy giá trị từ file đã Set
//            keyVal = properties.getProperty(key);
//            return LanguageUtils.convertCharset_ISO_8859_1_To_UTF8(keyVal);
//        } catch (Exception e) {
//            Log.error("VException: GetValue: " + e.getMessage());
//        }
//        return keyVal;
//    }
//
//    public static Boolean getBoolValue(String key) {
//        String value = getValue(key);
//        try {
//            return Boolean.parseBoolean(value);
//        } catch (Exception e) {
//            Log.error("VException: getBoolValue: " + e.getMessage());
//        }
//        return false;
//    }
//
//    public static List<String> loadFilesFromResourceFolder(String folderPath) {
//        List<String> files = new LinkedList<>();
//        File folder = new File(folderPath);
//        if (folder.exists() && folder.isDirectory()) {
//            for (File file : Objects.requireNonNull(folder.listFiles())) {
//                if (file.isFile()) {
//                    files.add(file.getAbsolutePath());
//                }
//            }
//        }
//        return files;
//    }
//
//    /**
//     * Read data from resource
//     *
//     * @param filePath : file path
//     */
//    public static String readDataFromFile(String filePath) {
//        try {
//            Log.info("Read configuration data from: " + filePath);
//            return ResourceReader.readDataFromResource(filePath);
//        } catch (IOException e) {
//            Log.error("Error: " + e.getMessage());
//        }
//        return null;
//    }
//
//
//
//}
