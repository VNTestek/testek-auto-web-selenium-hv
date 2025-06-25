package com.testek.consts;

import com.testek.database.config.DatabaseInfo;
import io.restassured.http.ContentType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * FrameworkConst defines the framework constants, including properties and configuration
 * Purpose: Define some final values, ex: project part
 **/
@Data
@NoArgsConstructor
public class FrameConst {
    //region PROJECT CONFIGURATION
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String LANG_VI = "vi";
    public static final String LANG_EN = "en";
    public static String PROJECT_NAME = "Testek";           // Project Name
    public static String PROJECT_KEY_NAME = "Testek";              // Support Generate Test Case automatically then upload to Jira
    public static String PROJECT_RESULT_DB = "apitestek";         // Database Name for storing test results
    public static String JIRA_DOMAIN = "https://testek.atlassian.net/browse/";
    public static String LOG_LEVEL = "DEBUG";
    public static String LANG = LANG_VI;

    public static List<DatabaseInfo> DATABASE_CONNECT_LIST = new ArrayList<>();
    public static boolean DATABASE_CONNECT_CONFIG = true;

    //region PROJECT CONFIG

    @Getter
    public static class APIProperty {
        public static String AUTH_KEY_HEADER = "Authorization";
        public static String CONTENT_KEY_HEADER = "Content-Type";
    }

    @Setter
    @Getter
    public static class AppConfig {
        public static String APP_VERSION = "1.0";
        public static String APP_LANGUAGE = "EN";
        public static String APP_DOMAIN = "https://testek.vn";
        public static String API_DOMAIN = "https://testek.vn";
        public static String USER_NAME;
        public static String PASSWORD;
    }

    @Setter
    @Getter
    public static class ExecuteConfig {
        public static String EXE_ENV = "SIT";
        public static String CATEGORY_TYPE = "ALL";
        public static String EXECUTED_MODULES = Objects.isNull(System.getProperty("modules")) ? "ALL" : System.getProperty("modules");
        public static ExeTarget EXE_ENV_TARGET = ExeTarget.LOCAL;
        public static Boolean EXE_PARALLEL = false;

        public static String EXE_BROWSER = "chrome";
        public static Boolean HEADLESS_FLAG = false;
    }

    @Getter
    @Setter
    public static class WaitConfig {
        public static long WAIT_EXPLICIT;
        public static long WAIT_IMPLICIT;
        public static long WAIT_PAGE_LOADED;
        public static long WAIT_DEFAULT;
    }

    @Getter
    public static class DataConfig {
        public static String SEPARATE_KEY = "%MS%";
        public static String SKIP_KEY = "^";
        public static String CONFIG_COL = "CONFIG";
        public static String DATA_ID_COL = "DATA_ID";
        public static String DETAIL_DATA_COL = "DETAIL_DATA";
        public static String IS_FILL = "fill";
        public static String IS_VERIFY = "verify";
        public static String SPECIFIC = "specific";
        public static String ELEMENT_NOT_FOUND = "ELEMENT %s - NOT FOUND";
    }

    @Getter
    public enum ElementProperty {
        TEXT_CONTENT("textContent"),
        PLACE_HOLDER("placeholder"),
        ELEMENT_PROPERTY_CONTENT_DESC("content-desc"),
        ELEMENT_PROPERTY_TEXT_CONTENT("textContent"),
        ELEMENT_PROPERTY_VALUE("value"),
        ;

        private final String value;

        ElementProperty(String value) {
            this.value = value;
        }
    }

    @Getter
    @Setter
    public static class SeleniumConfig {
        public static String REMOTE_URL = "";
        public static String REMOTE_PORT = "";
    }

    @Getter
    public enum ExeTarget {
        LOCAL,          // Local Environment (Your PC, GitLab)
        AWS,            // Device Farm
        LAMBDA,
        REMOTE          // For Web
    }

    @Getter
    public enum ConfigJson {
        APP_ACTIVITY, APP_PACKAGE, APPIUM_URL, AVD_LAUNCH_TIMEOUT, BUNDLE_ID, URL
    }

    @Getter
    public enum Browser {
        CHROME, EDGE, FIREFOX, SAFARI
    }

    @Getter
    public enum AccessToken {
        VALID, WITHOUT_TOKEN, LOGOUT_TOKEN, EXPIRED_TOKEN, CHANGE_PASS_TOKEN, LOCKED_USER, EMPTY_TOKEN, UPPER_TOKEN, LOWER_TOKEN, WRONG_TOKEN
    }

    @Getter
    public enum HTTPMethod {
        POST, PUT, DELETE, PATCH, GET
    }

    @Getter
    public enum ContentTypeManager {
        APPLICATION_JSON(ContentType.JSON), MULTIPART_FORM(ContentType.MULTIPART), FORM_URLENCODED(ContentType.URLENC);

        private final ContentType value;

        ContentTypeManager(ContentType contentType) {
            this.value = contentType;
        }
    }


    @Getter
    public enum LogType {
        INFO, STEP, VERIFY, WARNING
    }

    @Getter
    public enum FailureHandling {
        STOP_ON_FAILURE, CONTINUE_ON_FAILURE, OPTIONAL
    }

    @Getter
    public enum ConfigProperties {
        RECORD_SCREEN, OVERRIDE_REPORTS, PASSED_STEP_SCREENSHOTS, FAILED_STEP_SCREENSHOTS, SKIPPED_STEP_SCREENSHOTS, RETRY_FAILED_TESTS, RETRY_COUNT, OVERRIDE_SERVER_LOG
    }

    @Getter
    public enum TCType {
        API, MOBILE, WEBSITE
    }


    /**
     * Test case Type, used to categorize the test cases
     * REGRESSION: Test cases for regression testing
     * SMOKE: Test cases for smoke testing
     * SANITY: Test cases for sanity testing
     * You can add more types if needed
     */
    @Getter
    public enum CategoryType {
        REGRESSION,
        SMOKE,
        SANITY
    }
}
