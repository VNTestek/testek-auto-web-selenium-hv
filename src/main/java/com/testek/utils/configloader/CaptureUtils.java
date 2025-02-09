package com.testek.utils.configloader;

import com.testek.utils.Log;
import org.apache.commons.io.FileUtils;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.testek.consts.FrameConst.ReportConst.EXPORT_CAPTURE_PATH;
import static com.testek.consts.FrameConst.ReportConst.EXPORT_VIDEO_PATH;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

/**
 * CaptureHelpers class provides the ability to capture images or records video under execution
 * Using Monte Media library
 */
public class CaptureUtils extends ScreenRecorder {
    private static ScreenRecorder screenRecorder;
    String name;

    /**
     * Init the constructor
     */
    public CaptureUtils(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat,
                        Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.name = name;
    }

    /**
     * Start recording the video file
     *
     * @param fileName : The name of video file
     */
    public static void startRecord(String fileName) {
        File file = new File("./" + EXPORT_VIDEO_PATH + File.separator + fileName + File.separator);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration();
        try {
            screenRecorder = new CaptureUtils(gc, captureSize,
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, fileName);

            screenRecorder.start();
        } catch (Exception e) {
            Log.error("VException: " + e.getMessage());
        }
    }

    /**
     * Stop recording
     */
    public static void stopRecord() {
        try {
            screenRecorder.stop();
        } catch (IOException e) {
            Log.error("VException: " + e.getMessage());
        }
    }

    /**
     * Capture the screenshot
     *
     * @param driver   : The Selenium WebDriver
     * @param fileName :
     */
    public static void captureScreenshot(WebDriver driver, String fileName) {
        try {
            String path = System.getProperty("user.dir") + File.separator + EXPORT_CAPTURE_PATH;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
                Log.info("captureScreenshot: Create folder: " + file);
            }

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(path + "/" + fileName + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png"));
            Log.info("captureScreenshot: Screenshot taken current URL: " + driver.getCurrentUrl());
        } catch (Exception e) {
            Log.error("Exception while taking screenshot: " + e.getMessage());
        }
    }

    /**
     * Create a new media file
     *
     * @param fileFormat : Format of the file
     * @return The media file
     */
    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) {
            movieFolder.mkdirs();
        }

        if (!movieFolder.isDirectory()) {
            throw new IOException("\"" + movieFolder + "\" is not a directory.");
        }

        return new File(movieFolder,
                name + "-" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
    }

}
