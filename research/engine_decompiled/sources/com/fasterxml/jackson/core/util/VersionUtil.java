/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Version;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Pattern;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class VersionUtil {
    public static final String VERSION_FILE = "VERSION.txt";
    private static final Pattern VERSION_SEPARATOR = Pattern.compile("[-_./;:]");
    private final Version _version;

    protected VersionUtil() {
        Version v = null;
        try {
            v = VersionUtil.versionFor(this.getClass());
        }
        catch (Exception e) {
            System.err.println("ERROR: Failed to load Version information for bundle (via " + this.getClass().getName() + ").");
        }
        if (v == null) {
            v = Version.unknownVersion();
        }
        this._version = v;
    }

    public Version version() {
        return this._version;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Version versionFor(Class<?> cls) {
        Version version;
        block11: {
            version = null;
            try {
                InputStream in = cls.getResourceAsStream(VERSION_FILE);
                if (in == null) break block11;
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String groupStr = null;
                    String artifactStr = null;
                    String versionStr = br.readLine();
                    if (versionStr != null && (groupStr = br.readLine()) != null) {
                        groupStr = groupStr.trim();
                        artifactStr = br.readLine();
                        if (artifactStr != null) {
                            artifactStr = artifactStr.trim();
                        }
                    }
                    version = VersionUtil.parseVersion(versionStr, groupStr, artifactStr);
                }
                finally {
                    try {
                        in.close();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return version == null ? Version.unknownVersion() : version;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Version mavenVersionFor(ClassLoader classLoader, String groupId, String artifactId) {
        InputStream pomPoperties = classLoader.getResourceAsStream("META-INF/maven/" + groupId.replaceAll("\\.", "/") + "/" + artifactId + "/pom.properties");
        if (pomPoperties != null) {
            try {
                Properties props = new Properties();
                props.load(pomPoperties);
                String versionStr = props.getProperty("version");
                String pomPropertiesArtifactId = props.getProperty("artifactId");
                String pomPropertiesGroupId = props.getProperty("groupId");
                Version version = VersionUtil.parseVersion(versionStr, pomPropertiesGroupId, pomPropertiesArtifactId);
                return version;
            }
            catch (IOException e) {
            }
            finally {
                try {
                    pomPoperties.close();
                }
                catch (IOException e) {}
            }
        }
        return Version.unknownVersion();
    }

    @Deprecated
    public static Version parseVersion(String versionStr) {
        return VersionUtil.parseVersion(versionStr, null, null);
    }

    public static Version parseVersion(String versionStr, String groupId, String artifactId) {
        if (versionStr == null) {
            return null;
        }
        if ((versionStr = versionStr.trim()).length() == 0) {
            return null;
        }
        String[] parts = VERSION_SEPARATOR.split(versionStr);
        int major = VersionUtil.parseVersionPart(parts[0]);
        int minor = parts.length > 1 ? VersionUtil.parseVersionPart(parts[1]) : 0;
        int patch2 = parts.length > 2 ? VersionUtil.parseVersionPart(parts[2]) : 0;
        String snapshot = parts.length > 3 ? parts[3] : null;
        return new Version(major, minor, patch2, snapshot, groupId, artifactId);
    }

    protected static int parseVersionPart(String partStr) {
        char c;
        partStr = partStr.toString();
        int len = partStr.length();
        int number = 0;
        for (int i = 0; i < len && (c = partStr.charAt(i)) <= '9' && c >= '0'; ++i) {
            number = number * 10 + (c - 48);
        }
        return number;
    }
}

