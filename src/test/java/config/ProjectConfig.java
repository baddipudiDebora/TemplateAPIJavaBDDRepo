
package config;

import java.net.URL;
import java.util.Objects;

public final class ProjectConfig {
    private static volatile String baseUrl;
    private static volatile int timeout;
    private static volatile boolean logRequests;
    private static final Object lock = new Object();

    private ProjectConfig() {}

    public static void load() {
        synchronized (lock) {
            AppConfig.load();
            String url = AppConfig.get("baseUrl");
            validateUrl(url);
            baseUrl = url;

            try {
                timeout = Integer.parseInt(AppConfig.get("timeout"));
                if (timeout <= 0) {
                    throw new IllegalArgumentException("Timeout must be positive");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid timeout value", e);
            }

            String logRequestsStr = AppConfig.get("logRequests");
            if (!logRequestsStr.equalsIgnoreCase("true") && !logRequestsStr.equalsIgnoreCase("false")) {
                throw new IllegalArgumentException("logRequests must be 'true' or 'false'");
            }
            logRequests = Boolean.parseBoolean(logRequestsStr);
        }
    }

    private static void validateUrl(String url) {
        try {
            new URL(url);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid base URL: " + url, e);
        }
    }

    public static String baseUrl() {
        return Objects.requireNonNull(baseUrl, "ProjectConfig not loaded");
    }

    public static int timeout() {
        if (timeout == 0) {
            throw new IllegalStateException("ProjectConfig not loaded");
        }
        return timeout;
    }

    public static boolean logRequests() {
        return logRequests;
    }
}