/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.auth.http.HttpTransportFactory;
import com.google.auth.oauth2.AppEngineCredentials;
import com.google.auth.oauth2.CloudShellCredentials;
import com.google.auth.oauth2.ComputeEngineCredentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.UserCredentials;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

class DefaultCredentialsProvider {
    static final DefaultCredentialsProvider DEFAULT = new DefaultCredentialsProvider();
    static final String CREDENTIAL_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS";
    static final String WELL_KNOWN_CREDENTIALS_FILE = "application_default_credentials.json";
    static final String CLOUDSDK_CONFIG_DIRECTORY = "gcloud";
    static final String HELP_PERMALINK = "https://developers.google.com/accounts/docs/application-default-credentials";
    static final String APP_ENGINE_SIGNAL_CLASS = "com.google.appengine.api.utils.SystemProperty";
    static final String CLOUD_SHELL_ENV_VAR = "DEVSHELL_CLIENT_PORT";
    static final String SKIP_APP_ENGINE_ENV_VAR = "GOOGLE_APPLICATION_CREDENTIALS_SKIP_APP_ENGINE";
    static final String SPECIFICATION_VERSION = System.getProperty("java.specification.version");
    static final String GAE_RUNTIME_VERSION = System.getProperty("com.google.appengine.runtime.version");
    static final String RUNTIME_JETTY_LOGGER = System.getProperty("org.eclipse.jetty.util.log.class");
    static final Logger LOGGER = Logger.getLogger(DefaultCredentialsProvider.class.getName());
    static final String NO_GCE_CHECK_ENV_VAR = "NO_GCE_CHECK";
    static final String GCE_METADATA_HOST_ENV_VAR = "GCE_METADATA_HOST";
    static final String CLOUDSDK_CLIENT_ID = "764086051850-6qr4p6gpi6hn506pt8ejuq83di341hur.apps.googleusercontent.com";
    static final String CLOUDSDK_CREDENTIALS_WARNING = "Your application has authenticated using end user credentials from Google Cloud SDK. We recommend that most server applications use service accounts instead. If your application continues to use end user credentials from Cloud SDK, you might receive a \"quota exceeded\" or \"API not enabled\" error. For more information about service accounts, see https://cloud.google.com/docs/authentication/.";
    private GoogleCredentials cachedCredentials = null;
    private boolean checkedAppEngine = false;
    private boolean checkedComputeEngine = false;

    DefaultCredentialsProvider() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    final GoogleCredentials getDefaultCredentials(HttpTransportFactory transportFactory) throws IOException {
        DefaultCredentialsProvider defaultCredentialsProvider = this;
        synchronized (defaultCredentialsProvider) {
            if (this.cachedCredentials == null) {
                this.cachedCredentials = this.getDefaultCredentialsUnsynchronized(transportFactory);
            }
            if (this.cachedCredentials != null) {
                return this.cachedCredentials;
            }
        }
        throw new IOException(String.format("The Application Default Credentials are not available. They are available if running in Google Compute Engine. Otherwise, the environment variable %s must be defined pointing to a file defining the credentials. See %s for more information.", CREDENTIAL_ENV_VAR, HELP_PERMALINK));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final GoogleCredentials getDefaultCredentialsUnsynchronized(HttpTransportFactory transportFactory) throws IOException {
        GoogleCredentials credentials = null;
        String credentialsPath = this.getEnv(CREDENTIAL_ENV_VAR);
        if (credentialsPath != null && credentialsPath.length() > 0) {
            try (InputStream credentialsStream = null;){
                File credentialsFile = new File(credentialsPath);
                if (!this.isFile(credentialsFile)) {
                    throw new IOException("File does not exist.");
                }
                credentialsStream = this.readStream(credentialsFile);
                credentials = GoogleCredentials.fromStream(credentialsStream, transportFactory);
            }
        }
        if (credentials == null) {
            File wellKnownFileLocation = this.getWellKnownCredentialsFile();
            try (InputStream credentialsStream = null;){
                if (this.isFile(wellKnownFileLocation)) {
                    credentialsStream = this.readStream(wellKnownFileLocation);
                    credentials = GoogleCredentials.fromStream(credentialsStream, transportFactory);
                }
            }
            this.warnAboutProblematicCredentials(credentials);
        }
        if (credentials == null && this.isOnGAEStandard7() && !this.skipAppEngineCredentialsCheck()) {
            credentials = this.tryGetAppEngineCredential();
        }
        if (credentials == null) {
            credentials = this.tryGetCloudShellCredentials();
        }
        if (credentials == null) {
            credentials = this.tryGetComputeCredentials(transportFactory);
        }
        return credentials;
    }

    private void warnAboutProblematicCredentials(GoogleCredentials credentials) {
        if (credentials instanceof UserCredentials && ((UserCredentials)credentials).getClientId().equals(CLOUDSDK_CLIENT_ID)) {
            LOGGER.log(Level.WARNING, CLOUDSDK_CREDENTIALS_WARNING);
        }
    }

    private final File getWellKnownCredentialsFile() {
        File cloudConfigPath;
        String os = this.getProperty("os.name", "").toLowerCase(Locale.US);
        String envPath = this.getEnv("CLOUDSDK_CONFIG");
        if (envPath != null) {
            cloudConfigPath = new File(envPath);
        } else if (os.indexOf("windows") >= 0) {
            File appDataPath = new File(this.getEnv("APPDATA"));
            cloudConfigPath = new File(appDataPath, CLOUDSDK_CONFIG_DIRECTORY);
        } else {
            File configPath = new File(this.getProperty("user.home", ""), ".config");
            cloudConfigPath = new File(configPath, CLOUDSDK_CONFIG_DIRECTORY);
        }
        return new File(cloudConfigPath, WELL_KNOWN_CREDENTIALS_FILE);
    }

    private boolean runningOnAppEngine() {
        Class<?> systemPropertyClass = null;
        try {
            systemPropertyClass = this.forName(APP_ENGINE_SIGNAL_CLASS);
        }
        catch (ClassNotFoundException expected) {
            return false;
        }
        try {
            Field environmentField = systemPropertyClass.getField("environment");
            Object environmentValue = environmentField.get(null);
            Class<?> environmentType = environmentField.getType();
            Method valueMethod = environmentType.getMethod("value", new Class[0]);
            Object environmentValueValue = valueMethod.invoke(environmentValue, new Object[0]);
            return environmentValueValue != null;
        }
        catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException exception) {
            Exception cause = exception;
            throw new RuntimeException(String.format("Unexpected error trying to determine if runnning on Google App Engine: %s", cause.getMessage()), cause);
        }
    }

    private GoogleCredentials tryGetCloudShellCredentials() {
        String port = this.getEnv(CLOUD_SHELL_ENV_VAR);
        if (port != null) {
            return CloudShellCredentials.create(Integer.parseInt(port));
        }
        return null;
    }

    private GoogleCredentials tryGetAppEngineCredential() throws IOException {
        if (this.checkedAppEngine) {
            return null;
        }
        boolean onAppEngine = this.runningOnAppEngine();
        this.checkedAppEngine = true;
        if (!onAppEngine) {
            return null;
        }
        return new AppEngineCredentials(Collections.emptyList());
    }

    private final GoogleCredentials tryGetComputeCredentials(HttpTransportFactory transportFactory) {
        if (this.checkedComputeEngine) {
            return null;
        }
        boolean runningOnComputeEngine = ComputeEngineCredentials.runningOnComputeEngine(transportFactory, this);
        this.checkedComputeEngine = true;
        if (runningOnComputeEngine) {
            return ComputeEngineCredentials.newBuilder().setHttpTransportFactory(transportFactory).build();
        }
        return null;
    }

    private boolean skipAppEngineCredentialsCheck() {
        boolean skip = false;
        String value = this.getEnv(SKIP_APP_ENGINE_ENV_VAR);
        if (value != null) {
            skip = value.equalsIgnoreCase("true") || value.equals("1");
        }
        return skip;
    }

    protected boolean isOnGAEStandard7() {
        return GAE_RUNTIME_VERSION != null && (SPECIFICATION_VERSION.equals("1.7") || RUNTIME_JETTY_LOGGER == null);
    }

    Class<?> forName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    String getEnv(String name) {
        return System.getenv(name);
    }

    String getProperty(String property, String def) {
        return System.getProperty(property, def);
    }

    boolean isFile(File file) {
        return file.isFile();
    }

    InputStream readStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
}

