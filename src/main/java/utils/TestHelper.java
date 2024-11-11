package utils;

public class TestHelper {
    public static String getTestResourcePath(String fileName) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResource(fileName)
                .getPath();
    }
}
