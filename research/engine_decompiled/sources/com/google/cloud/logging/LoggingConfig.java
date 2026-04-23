/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LoggingEnhancer;
import com.google.cloud.logging.LoggingLevel;
import com.google.cloud.logging.MonitoredResourceUtil;
import com.google.cloud.logging.Synchronicity;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.SimpleFormatter;

class LoggingConfig {
    private final LogManager manager = LogManager.getLogManager();
    private final String className;
    private static final String FLUSH_LEVEL_TAG = "flushLevel";
    private static final String LOG_FILENAME_TAG = "log";
    private static final String LOG_LEVEL_TAG = "level";
    private static final String FILTER_TAG = "filter";
    private static final String FORMATTER_TAG = "formatter";
    private static final String SYNCHRONICITY_TAG = "synchronicity";
    private static final String RESOURCE_TYPE_TAG = "resourceType";
    private static final String ENHANCERS_TAG = "enhancers";

    public LoggingConfig(String className) {
        this.className = className;
    }

    Level getFlushLevel() {
        return this.getLevelProperty(FLUSH_LEVEL_TAG, LoggingLevel.ERROR);
    }

    String getLogName() {
        return this.getProperty(LOG_FILENAME_TAG, "java.log");
    }

    Level getLogLevel() {
        return this.getLevelProperty(LOG_LEVEL_TAG, LoggingLevel.INFO);
    }

    Filter getFilter() {
        return this.getFilterProperty(FILTER_TAG, null);
    }

    Synchronicity getSynchronicity() {
        String synchronicityStr = this.getProperty(SYNCHRONICITY_TAG);
        try {
            return Synchronicity.valueOf(synchronicityStr);
        }
        catch (Exception exception) {
            return Synchronicity.ASYNC;
        }
    }

    Formatter getFormatter() {
        return this.getFormatterProperty(FORMATTER_TAG, new SimpleFormatter());
    }

    MonitoredResource getMonitoredResource(String projectId) {
        String resourceType = this.getProperty(RESOURCE_TYPE_TAG, "");
        return MonitoredResourceUtil.getResource(projectId, resourceType);
    }

    List<LoggingEnhancer> getEnhancers() {
        String list2 = this.getProperty(ENHANCERS_TAG);
        try {
            ArrayList<LoggingEnhancer> enhancers = new ArrayList<LoggingEnhancer>();
            if (list2 != null) {
                String[] items;
                for (String e_name : items = list2.split(",")) {
                    Class<?> clz = ClassLoader.getSystemClassLoader().loadClass(e_name);
                    enhancers.add((LoggingEnhancer)clz.newInstance());
                }
            }
            return enhancers;
        }
        catch (Exception exception) {
            return Collections.emptyList();
        }
    }

    private String getProperty(String name, String defaultValue) {
        return MoreObjects.firstNonNull(this.getProperty(name), defaultValue);
    }

    private Level getLevelProperty(String name, Level defaultValue) {
        String stringLevel = this.getProperty(name);
        if (stringLevel == null) {
            return defaultValue;
        }
        try {
            return Level.parse(stringLevel);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return defaultValue;
        }
    }

    private Filter getFilterProperty(String name, Filter defaultValue) {
        String stringFilter = this.getProperty(name);
        try {
            if (stringFilter != null) {
                Class<?> clz = ClassLoader.getSystemClassLoader().loadClass(stringFilter);
                return (Filter)clz.newInstance();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return defaultValue;
    }

    private Formatter getFormatterProperty(String name, Formatter defaultValue) {
        String stringFilter = this.getProperty(name);
        try {
            if (stringFilter != null) {
                Class<?> clz = ClassLoader.getSystemClassLoader().loadClass(stringFilter);
                return (Formatter)clz.newInstance();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return defaultValue;
    }

    private String getProperty(String propertyName) {
        return this.manager.getProperty(this.className + "." + propertyName);
    }
}

