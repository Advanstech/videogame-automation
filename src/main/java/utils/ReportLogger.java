// src/main/java/utils/ReportLogger.java
package utils;

import io.qameta.allure.Allure;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportLogger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        String formattedMessage = String.format("[%s] INFO: %s",
                LocalDateTime.now().format(formatter), message);
        Allure.step(formattedMessage);
        System.out.println(formattedMessage);
    }

    public static void error(String message) {
        String formattedMessage = String.format("[%s] ERROR: %s",
                LocalDateTime.now().format(formatter), message);
        Allure.step(formattedMessage);
        System.err.println(formattedMessage);
    }

    public static void error(String message, Throwable throwable) {
        String formattedMessage = String.format("[%s] ERROR: %s - %s",
                LocalDateTime.now().format(formatter), message, throwable.getMessage());
        Allure.step(formattedMessage);
        System.err.println(formattedMessage);
        throwable.printStackTrace();
    }

    public static void startTest(String testName) {
        info("Starting test: " + testName);
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
