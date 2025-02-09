package com.testek.utils;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.testek.consts.FrameConst.ReportConst.*;

@Getter
public class ReportUtils {
    private ReportUtils() {
    }

    /**
     * Create the report file path
     */
    public static String createExtentReportPath(String reportName) {
        String name = OVERRIDE_REPORTS ?
                getCurrentDate() + "_" + EXTENT_REPORT_NAME + reportName + ".html"
                : EXTENT_REPORT_NAME + reportName + ".html";
        return EXTENT_REPORT_FOLDER_PATH + "/" + name;
    }

    public static String createJsonExtentObserverPath(String reportName) {
        String name = OVERRIDE_REPORTS ?
                getCurrentDate() + "_" + EXTENT_REPORT_NAME + reportName + ".json"
                : EXTENT_REPORT_NAME + reportName + ".json";
        return EXTENT_REPORT_FOLDER_PATH + "/" + name;
    }


    public static void openReports() {
//        if (OPEN_REPORTS_AFTER_EXECUTION) {
//            try {
//                Desktop.getDesktop().browse(new File(FrameConst.getExtentReportFilePath()).toURI());
//            } catch (FileNotFoundException fileNotFoundException) {
//                throw new InvalidPathForFilesException("Exception: The report is not found",
//                        fileNotFoundException.fillInStackTrace());
//            } catch (IOException ioException) {
//                throw new FrameworkException("Exception: reading the report ", ioException.fillInStackTrace());
//            }
//        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }
}

