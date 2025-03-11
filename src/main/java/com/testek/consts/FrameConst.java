package com.testek.consts;

import com.testek.database.DatabaseInfo;
import com.testek.utils.configloader.AbsPropertyUtils;
import io.restassured.http.ContentType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FrameworkConst defines the framework constants, including properties and configuration
 * Purpose: Define some final values, ex: project part
 **/
@Data
@NoArgsConstructor
public class FrameConst {
    public static final String PROJECT_PATH = System.getProperty("user.dir") + File.separator;
    public static List<DatabaseInfo> DATABASE_CONNECT_LIST = new ArrayList<>();
    //region PROJECT CONFIG

    @Data
    public static class ProjectConfig {
        public static String BROWSER = AbsPropertyUtils.getValue("browser");
        public static String APP_URL = AbsPropertyUtils.getValue("base_url");
        public static String REMOTE_URL = AbsPropertyUtils.getValue("remote_url");
        public static String REMOTE_PORT = AbsPropertyUtils.getValue("remote_port");
        public static String PROJECT_NAME = AbsPropertyUtils.getValue("project_name");

        public static String APPLICATION_VERSION = AbsPropertyUtils.getValue("testing_version");
        public static String APPLICATION_NAME = AbsPropertyUtils.getValue("app_name");
        public static String APPLICATION_ENV = AbsPropertyUtils.getValue("app_environment");

        public static String AUTHOR = AbsPropertyUtils.getValue("author");
        public static String TARGET = AbsPropertyUtils.getValue("target");
        public static Boolean HEADLESS = AbsPropertyUtils.getBoolValue("headless");
        public static String MAINTAIN_DATA = AbsPropertyUtils.getValue("maintainData");
        public static String TFS_LINK = AbsPropertyUtils.getValue("tfsLink");

        public static int WAIT_DEFAULT = Integer.parseInt(AbsPropertyUtils.getValue("wait_default"));
        public static int WAIT_IMPLICIT = Integer.parseInt(AbsPropertyUtils.getValue("wait_implicit"));
        public static int WAIT_EXPLICIT = Integer.parseInt(AbsPropertyUtils.getValue("wait_explicit"));
        public static int WAIT_PAGE_LOADED = Integer.parseInt(AbsPropertyUtils.getValue("wait_page_loaded"));
        public static int WAIT_SLEEP_STEP = Integer.parseInt(AbsPropertyUtils.getValue("wait_step"));
        public static String ACTIVE_PAGE_LOADED = AbsPropertyUtils.getValue("active_page_load");
    }

    //region REPORTING

    @Getter
    @Setter
    public static class ReportConst {
        public static final String EXTENT_REPORT_NAME = AbsPropertyUtils.getValue("extent_report_name");
        public static final String EXTENT_REPORT_FOLDER = AbsPropertyUtils.getValue("extent_report_folder");
        public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
        public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
        public static String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH + "/" + EXTENT_REPORT_FILE_NAME;
        public static final String REPORT_TITLE = AbsPropertyUtils.getValue("report_title");
        public static final String EXPORT_VIDEO_PATH = AbsPropertyUtils.getValue("export_video_path");
        public static final String EXPORT_CAPTURE_PATH = AbsPropertyUtils.getValue("export_capture_path");
        public static final Boolean OVERRIDE_REPORTS = AbsPropertyUtils.getBoolValue("override_reports");
        public static final Boolean OPEN_REPORTS_AFTER_EXECUTION = AbsPropertyUtils.getBoolValue("open_reports_after_execution");
        public static final Boolean SEND_EMAIL_TO_USERS = AbsPropertyUtils.getBoolValue("send_email_to_users");
        public static final Boolean SCREENSHOT_PASSED_STEPS = AbsPropertyUtils.getBoolValue("screenshot_passed_steps");
        public static final Boolean SCREENSHOT_FAILED_STEPS = AbsPropertyUtils.getBoolValue("screenshot_failed_steps");
        public static final Boolean SCREENSHOT_SKIPPED_STEPS = AbsPropertyUtils.getBoolValue("screenshot_skipped_steps");
        public static final Boolean SCREENSHOT_ALL_STEPS = AbsPropertyUtils.getBoolValue("screenshot_all_steps");
        public static final Boolean DRAW_BORDER_ERR_ELEMENT = AbsPropertyUtils.getBoolValue("draw_border_err_element");
        public static final Boolean ZIP_FOLDER = AbsPropertyUtils.getBoolValue("zip_folder");
        public static final Boolean VIDEO_RECORD = AbsPropertyUtils.getBoolValue("video_record");
        public static final String YES = "yes";
        public static final String NO = "no";
        public static final String BOLD_START = "<b>";
        public static final String BOLD_END = "</b>";
        public static final String FAIL = BOLD_START + "FAIL" + BOLD_END;
        public static final String PASS = BOLD_START + "PASS" + BOLD_END;
        public static String LOG_LEVEL = "DEBUG";
    }
    //endregion

    //endregion
    @Data
    public static class DataConfig {
        private DataConfig() {
        }

        public static String SEPARATE_KEY = "%MS%";
        public static String SKIP_KEY = "^";
        public static String CONFIG_COL = "CONFIG";
        public static String DATA_ID_COL = "DATA_ID";
        public static String DETAIL_DATA_COL = "DETAIL_DATA";
        public static String IS_FILL = "fill";
        public static String IS_VERIFY = "verify";
        public static String SPECIFIC = "specific";
        public static String ELEMENT_NOT_FOUND = "ELEMENT %s - NOT FOUND";
        public static String ELEMENT_PROPERTY_TEXT_CONTENT = "textContent";
        public static String ELEMENT_PROPERTY_VALUE = "value";

        public static final String LANG_VI = "vi";

        public static final String LANG_EN = "en";
    }

    @Data
    public static class Icon {
        private Icon() {
        }

        public static String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
        public static String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
        public static String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
        public static String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
        public static String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
        public static String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
        public static String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
        public static String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
        public static String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
        public static String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
        public static String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";
        public static String ICON_NAVIGATE_RIGHT = "<i class='fa fa-arrow-circle-right' ></i>";
        public static String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
        public static String ICON_BUG = "<i class='fa fa-bug' ></i>";
        public static final String ICON_SOCIAL_GITHUB_PAGE_URL = "";
        public static final String ICON_SOCIAL_LINKEDIN_URL = "";
        public static final String ICON_SOCIAL_GITHUB_URL = "";
        public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
        public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";
        public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";
        public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
        public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    }

    /**
     * Browser Type
     */
    @Getter
    public enum Browser {
        CHROME, EDGE, FIREFOX, SAFARI
    }

    /**
     * The target for execution
     */
    @Getter
    public enum Target {
        LOCAL,      // Execute at your machine
        REMOTE      // Execute with Selenium Grid
    }

    /**
     * LogType class represents the type of log messages
     */
    @Getter
    public enum LogType {
        INFO,           // Display information
        STEP,           // Display the log for each steps
        VERIFY,         // Display verification log
        DEBUG,           // Display the debug log
        WARNING
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

    /**
     * FailureHandling class defines how to handle failures
     */
    @Getter
    public enum FailureHandling {
        STOP_ON_FAILURE,            // Stop the execution when a failure occurs
        CONTINUE_ON_FAILURE         // Continue the execution when a failure occurs
    }

    @Getter
    public enum HTTPMethod {
        POST, PUT, DELETE, PATCH, GET
    }

    @Getter
    public enum ContentTypeManager {
        APPLICATION_JSON(ContentType.JSON),
        MULTIPART_FORM(ContentType.MULTIPART),
        FORM_URLENCODED(ContentType.URLENC);

        private final ContentType value;

        ContentTypeManager(ContentType contentType) {
            this.value = contentType;
        }
    }
}
