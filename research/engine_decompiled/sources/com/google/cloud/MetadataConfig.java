/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MetadataConfig {
    private static final String METADATA_URL = "http://metadata/computeMetadata/v1/";

    private MetadataConfig() {
    }

    public static String getProjectId() {
        return MetadataConfig.getAttribute("project/project-id");
    }

    public static String getZone() {
        String zoneId = MetadataConfig.getAttribute("instance/zone");
        if (zoneId.contains("/")) {
            return zoneId.substring(zoneId.lastIndexOf(47) + 1);
        }
        return zoneId;
    }

    public static String getInstanceId() {
        return MetadataConfig.getAttribute("instance/id");
    }

    public static String getClusterName() {
        return MetadataConfig.getAttribute("instance/attributes/cluster-name");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String getAttribute(String attributeName) {
        try {
            URL url = new URL(METADATA_URL + attributeName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Metadata-Flavor", "Google");
            InputStream input2 = connection.getInputStream();
            if (connection.getResponseCode() != 200) return null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input2, StandardCharsets.UTF_8));){
                String string2 = reader.readLine();
                return string2;
            }
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return null;
    }
}

