package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportLogger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Logger logger = Logger.getLogger(ReportLogger.class.getName());

    private static String formatMessage(String level, String message) {
        return String.format("[%s] %s: %s", LocalDateTime.now().format(formatter), level, message);
    }

    public static void info(String message) {
        String formattedMessage = formatMessage("INFO", message);
        Allure.step(formattedMessage, Status.PASSED);
        logger.log(Level.INFO, formattedMessage);
    }

    public static void error(String message) {
        String formattedMessage = formatMessage("ERROR", message);
        Allure.step(formattedMessage, Status.FAILED);
        logger.log(Level.SEVERE, formattedMessage);
    }

    public static void error(String message, Throwable throwable) {
        String formattedMessage = formatMessage("ERROR", message + " - " + throwable.getMessage());
        Allure.step(formattedMessage, Status.FAILED);
        logger.log(Level.SEVERE, formattedMessage, throwable);
    }

    public static void startTest(String testName) {
        info("Starting test: " + testName);
        Allure.label("testName", testName);
    }

    public static void endTest(String testName) {
        info("Finished test: " + testName);
    }

    public static void logRequest(String endpoint, String method) {
        info(String.format("Making %s request to: %s", method, endpoint));
    }

    public static void logResponse(int statusCode, String responseBody) {
        info(String.format("Received response with status code: %d", statusCode));
        info("Response body: " + responseBody);
    }
}