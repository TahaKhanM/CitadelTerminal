/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Charsets;
import com.google.api.core.ApiClock;
import com.google.api.core.BetaApi;
import com.google.api.core.CurrentMillisClock;
import com.google.api.core.InternalApi;
import com.google.api.gax.core.GaxProperties;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.FixedHeaderProvider;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.NoHeaderProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.MetadataConfig;
import com.google.cloud.PlatformInformation;
import com.google.cloud.Service;
import com.google.cloud.ServiceDefaults;
import com.google.cloud.ServiceFactory;
import com.google.cloud.ServiceRpc;
import com.google.cloud.TransportOptions;
import com.google.cloud.spi.ServiceRpcFactory;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.threeten.bp.Duration;

public abstract class ServiceOptions<ServiceT extends Service<OptionsT>, OptionsT extends ServiceOptions<ServiceT, OptionsT>>
implements Serializable {
    public static final String CREDENTIAL_ENV_NAME = "GOOGLE_APPLICATION_CREDENTIALS";
    private static final String DEFAULT_HOST = "https://www.googleapis.com";
    private static final String LEGACY_PROJECT_ENV_NAME = "GCLOUD_PROJECT";
    private static final String PROJECT_ENV_NAME = "GOOGLE_CLOUD_PROJECT";
    private static final RetrySettings DEFAULT_RETRY_SETTINGS = ServiceOptions.getDefaultRetrySettingsBuilder().build();
    private static final RetrySettings NO_RETRY_SETTINGS = ServiceOptions.getDefaultRetrySettingsBuilder().setMaxAttempts(1).build();
    private static final long serialVersionUID = 9198896031667942014L;
    private final String projectId;
    private final String host;
    private final RetrySettings retrySettings;
    private final String serviceRpcFactoryClassName;
    private final String serviceFactoryClassName;
    private final ApiClock clock;
    protected Credentials credentials;
    private final TransportOptions transportOptions;
    private final HeaderProvider headerProvider;
    private transient ServiceRpcFactory<OptionsT> serviceRpcFactory;
    private transient ServiceFactory<ServiceT, OptionsT> serviceFactory;
    private transient ServiceT service;
    private transient ServiceRpc rpc;

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected ServiceOptions(Class<? extends ServiceFactory<ServiceT, OptionsT>> serviceFactoryClass, Class<? extends ServiceRpcFactory<OptionsT>> rpcFactoryClass, Builder<ServiceT, OptionsT, ?> builder, ServiceDefaults<ServiceT, OptionsT> serviceDefaults) {
        String string2 = this.projectId = ((Builder)builder).projectId != null ? ((Builder)builder).projectId : this.getDefaultProject();
        if (this.projectIdRequired()) {
            Preconditions.checkArgument(this.projectId != null, "A project ID is required for this service but could not be determined from the builder or the environment.  Please set a project ID using the builder.");
        }
        this.host = MoreObjects.firstNonNull(((Builder)builder).host, this.getDefaultHost());
        this.credentials = builder.credentials != null ? builder.credentials : ServiceOptions.defaultCredentials();
        this.retrySettings = MoreObjects.firstNonNull(((Builder)builder).retrySettings, ServiceOptions.getDefaultRetrySettings());
        this.serviceFactory = MoreObjects.firstNonNull(((Builder)builder).serviceFactory, ServiceOptions.getFromServiceLoader(serviceFactoryClass, serviceDefaults.getDefaultServiceFactory()));
        this.serviceFactoryClassName = this.serviceFactory.getClass().getName();
        this.serviceRpcFactory = MoreObjects.firstNonNull(((Builder)builder).serviceRpcFactory, ServiceOptions.getFromServiceLoader(rpcFactoryClass, serviceDefaults.getDefaultRpcFactory()));
        this.serviceRpcFactoryClassName = this.serviceRpcFactory.getClass().getName();
        this.clock = MoreObjects.firstNonNull(((Builder)builder).clock, CurrentMillisClock.getDefaultClock());
        this.transportOptions = MoreObjects.firstNonNull(((Builder)builder).transportOptions, serviceDefaults.getDefaultTransportOptions());
        this.headerProvider = MoreObjects.firstNonNull(((Builder)builder).headerProvider, new NoHeaderProvider());
    }

    protected boolean projectIdRequired() {
        return true;
    }

    private static GoogleCredentials defaultCredentials() {
        try {
            return GoogleCredentials.getApplicationDefault();
        }
        catch (Exception ex) {
            return null;
        }
    }

    protected String getDefaultHost() {
        return DEFAULT_HOST;
    }

    protected String getDefaultProject() {
        return ServiceOptions.getDefaultProjectId();
    }

    public static String getDefaultProjectId() {
        String projectId = System.getProperty(PROJECT_ENV_NAME, System.getenv(PROJECT_ENV_NAME));
        if (projectId == null) {
            projectId = System.getProperty(LEGACY_PROJECT_ENV_NAME, System.getenv(LEGACY_PROJECT_ENV_NAME));
        }
        if (projectId == null) {
            projectId = ServiceOptions.getAppEngineProjectId();
        }
        if (projectId == null) {
            projectId = ServiceOptions.getServiceAccountProjectId();
        }
        return projectId != null ? projectId : ServiceOptions.getGoogleCloudProjectId();
    }

    public static String getAppEngineAppId() {
        return System.getProperty("com.google.appengine.application.id");
    }

    private static String getActiveGoogleCloudConfig(File configDir) {
        String activeGoogleCloudConfig = null;
        try {
            activeGoogleCloudConfig = Files.readFirstLine(new File(configDir, "active_config"), Charset.defaultCharset());
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return MoreObjects.firstNonNull(activeGoogleCloudConfig, "default");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static String getGoogleCloudProjectId() {
        File configDir = System.getenv().containsKey("CLOUDSDK_CONFIG") ? new File(System.getenv("CLOUDSDK_CONFIG")) : (ServiceOptions.isWindows() && System.getenv().containsKey("APPDATA") ? new File(System.getenv("APPDATA"), "gcloud") : new File(System.getProperty("user.home"), ".config/gcloud"));
        String activeConfig = ServiceOptions.getActiveGoogleCloudConfig(configDir);
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(configDir, "configurations/config_" + activeConfig));
        }
        catch (FileNotFoundException newConfigFileNotFoundEx) {
            try {
                fileReader = new FileReader(new File(configDir, "properties"));
            }
            catch (FileNotFoundException fileNotFoundException) {
                // empty catch block
            }
        }
        if (fileReader == null) return MetadataConfig.getProjectId();
        try (BufferedReader reader = new BufferedReader(fileReader);){
            Matcher matcher;
            block24: {
                String line;
                String section = null;
                Pattern projectPattern = Pattern.compile("^project\\s*=\\s*(.*)$");
                Pattern sectionPattern = Pattern.compile("^\\[(.*)\\]$");
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty() || line.startsWith(";")) continue;
                    matcher = sectionPattern.matcher(line = line.trim());
                    if (matcher.matches()) {
                        section = matcher.group(1);
                        continue;
                    }
                    if (section != null && !section.equals("core") || !(matcher = projectPattern.matcher(line)).matches()) {
                        continue;
                    }
                    break block24;
                }
                return MetadataConfig.getProjectId();
            }
            String string2 = matcher.group(1);
            return string2;
        }
        catch (IOException iOException) {
            // empty catch block
        }
        return MetadataConfig.getProjectId();
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows");
    }

    protected static String getAppEngineProjectId() {
        String projectId = null;
        if (PlatformInformation.isOnGAEStandard7()) {
            projectId = ServiceOptions.getAppEngineProjectIdFromAppId();
        } else {
            projectId = System.getenv(PROJECT_ENV_NAME);
            if (projectId == null) {
                projectId = System.getenv(LEGACY_PROJECT_ENV_NAME);
            }
            if (projectId == null) {
                projectId = ServiceOptions.getAppEngineProjectIdFromAppId();
            }
            if (projectId == null) {
                try {
                    projectId = ServiceOptions.getAppEngineProjectIdFromMetadataServer();
                }
                catch (IOException ignore) {
                    projectId = null;
                }
            }
        }
        return projectId;
    }

    protected static String getAppEngineProjectIdFromAppId() {
        String projectId = ServiceOptions.getAppEngineAppId();
        if (projectId != null && projectId.contains(":")) {
            int colonIndex = projectId.indexOf(":");
            projectId = projectId.substring(colonIndex + 1);
        }
        return projectId;
    }

    private static String getAppEngineProjectIdFromMetadataServer() throws IOException {
        String metadata = "http://metadata.google.internal";
        String projectIdURL = "/computeMetadata/v1/project/project-id";
        GenericUrl url = new GenericUrl(metadata + projectIdURL);
        NetHttpTransport netHttpTransport = new NetHttpTransport();
        HttpRequestFactory requestFactory = netHttpTransport.createRequestFactory();
        HttpRequest request = requestFactory.buildGetRequest(url).setConnectTimeout(500).setReadTimeout(500).setHeaders(new HttpHeaders().set("Metadata-Flavor", "Google"));
        HttpResponse response = request.execute();
        return ServiceOptions.headerContainsMetadataFlavor(response) ? response.parseAsString() : null;
    }

    @InternalApi(value="Visible for testing")
    static boolean headerContainsMetadataFlavor(HttpResponse response) {
        String metadataFlavorValue = response.getHeaders().getFirstHeaderStringValue("Metadata-Flavor");
        return "Google".equals(metadataFlavorValue);
    }

    protected static String getServiceAccountProjectId() {
        return ServiceOptions.getServiceAccountProjectId(System.getenv(CREDENTIAL_ENV_NAME));
    }

    @InternalApi(value="Visible for testing")
    static String getServiceAccountProjectId(String credentialsPath) {
        String project = null;
        if (credentialsPath != null) {
            try (FileInputStream credentialsStream = new FileInputStream(credentialsPath);){
                JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
                JsonObjectParser parser = new JsonObjectParser(jsonFactory);
                GenericJson fileContents = parser.parseAndClose((InputStream)credentialsStream, Charsets.UTF_8, GenericJson.class);
                project = (String)fileContents.get("project_id");
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        return project;
    }

    public ServiceT getService() {
        if (this.service == null) {
            this.service = this.serviceFactory.create(this);
        }
        return this.service;
    }

    public ServiceRpc getRpc() {
        if (this.rpc == null) {
            this.rpc = this.serviceRpcFactory.create(this);
        }
        return this.rpc;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public String getHost() {
        return this.host;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public Credentials getScopedCredentials() {
        Credentials credentialsToReturn = this.credentials;
        if (this.credentials instanceof GoogleCredentials && ((GoogleCredentials)this.credentials).createScopedRequired()) {
            credentialsToReturn = ((GoogleCredentials)this.credentials).createScoped(this.getScopes());
        }
        return credentialsToReturn;
    }

    public RetrySettings getRetrySettings() {
        return this.retrySettings;
    }

    public ApiClock getClock() {
        return this.clock;
    }

    public TransportOptions getTransportOptions() {
        return this.transportOptions;
    }

    public String getApplicationName() {
        String libraryVersion = this.getLibraryVersion();
        StringBuilder sb = new StringBuilder();
        String customUserAgentValue = this.getUserAgent();
        if (customUserAgentValue != null) {
            sb.append(customUserAgentValue).append(' ');
        }
        if (libraryVersion == null) {
            sb.append(ServiceOptions.getLibraryName());
        } else {
            sb.append(ServiceOptions.getLibraryName()).append('/').append(libraryVersion);
        }
        return sb.toString();
    }

    public static String getLibraryName() {
        return "gcloud-java";
    }

    public static String getGoogApiClientLibName() {
        return "gccl";
    }

    public String getLibraryVersion() {
        return GaxProperties.getLibraryVersion(this.getClass());
    }

    @InternalApi
    public final HeaderProvider getMergedHeaderProvider(HeaderProvider internalHeaderProvider) {
        ImmutableMap<String, String> mergedHeaders = ImmutableMap.builder().putAll(internalHeaderProvider.getHeaders()).putAll(this.headerProvider.getHeaders()).build();
        return FixedHeaderProvider.create(mergedHeaders);
    }

    @InternalApi
    public final String getUserAgent() {
        if (this.headerProvider != null) {
            for (Map.Entry<String, String> entry : this.headerProvider.getHeaders().entrySet()) {
                if (!"user-agent".equals(entry.getKey().toLowerCase())) continue;
                return entry.getValue();
            }
        }
        return null;
    }

    protected int baseHashCode() {
        return Objects.hash(this.projectId, this.host, this.credentials, this.retrySettings, this.serviceFactoryClassName, this.serviceRpcFactoryClassName, this.clock);
    }

    protected boolean baseEquals(ServiceOptions<?, ?> other) {
        return Objects.equals(this.projectId, other.projectId) && Objects.equals(this.host, other.host) && Objects.equals(this.credentials, other.credentials) && Objects.equals(this.retrySettings, other.retrySettings) && Objects.equals(this.serviceFactoryClassName, other.serviceFactoryClassName) && Objects.equals(this.serviceRpcFactoryClassName, other.serviceRpcFactoryClassName) && Objects.equals(this.clock, this.clock);
    }

    private void readObject(ObjectInputStream input2) throws IOException, ClassNotFoundException {
        input2.defaultReadObject();
        this.serviceFactory = (ServiceFactory)ServiceOptions.newInstance(this.serviceFactoryClassName);
        this.serviceRpcFactory = (ServiceRpcFactory)ServiceOptions.newInstance(this.serviceRpcFactoryClassName);
    }

    @InternalApi
    public static <T> T newInstance(String className) throws IOException, ClassNotFoundException {
        try {
            return (T)Class.forName(className).newInstance();
        }
        catch (IllegalAccessException | InstantiationException e) {
            throw new IOException(e);
        }
    }

    public static RetrySettings getDefaultRetrySettings() {
        return DEFAULT_RETRY_SETTINGS;
    }

    public static RetrySettings getNoRetrySettings() {
        return NO_RETRY_SETTINGS;
    }

    private static RetrySettings.Builder getDefaultRetrySettingsBuilder() {
        return RetrySettings.newBuilder().setMaxAttempts(6).setInitialRetryDelay(Duration.ofMillis(1000L)).setMaxRetryDelay(Duration.ofMillis(32000L)).setRetryDelayMultiplier(2.0).setTotalTimeout(Duration.ofMillis(50000L)).setInitialRpcTimeout(Duration.ofMillis(50000L)).setRpcTimeoutMultiplier(1.0).setMaxRpcTimeout(Duration.ofMillis(50000L));
    }

    protected abstract Set<String> getScopes();

    public abstract <B extends Builder<ServiceT, OptionsT, B>> B toBuilder();

    protected RetrySettings defaultRetrySettings() {
        return ServiceOptions.getDefaultRetrySettings();
    }

    @InternalApi
    public static <T> T getFromServiceLoader(Class<? extends T> clazz, T defaultInstance) {
        return Iterables.getFirst(ServiceLoader.load(clazz), defaultInstance);
    }

    public static abstract class Builder<ServiceT extends Service<OptionsT>, OptionsT extends ServiceOptions<ServiceT, OptionsT>, B extends Builder<ServiceT, OptionsT, B>> {
        private String projectId;
        private String host;
        protected Credentials credentials;
        private RetrySettings retrySettings;
        private ServiceFactory<ServiceT, OptionsT> serviceFactory;
        private ServiceRpcFactory<OptionsT> serviceRpcFactory;
        private ApiClock clock;
        private TransportOptions transportOptions;
        private HeaderProvider headerProvider;

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected Builder() {
        }

        @InternalApi(value="This class should only be extended within google-cloud-java")
        protected Builder(ServiceOptions<ServiceT, OptionsT> options) {
            this.projectId = ((ServiceOptions)options).projectId;
            this.host = ((ServiceOptions)options).host;
            this.credentials = options.credentials;
            this.retrySettings = ((ServiceOptions)options).retrySettings;
            this.serviceFactory = ((ServiceOptions)options).serviceFactory;
            this.serviceRpcFactory = ((ServiceOptions)options).serviceRpcFactory;
            this.clock = ((ServiceOptions)options).clock;
            this.transportOptions = ((ServiceOptions)options).transportOptions;
        }

        protected abstract ServiceOptions<ServiceT, OptionsT> build();

        protected B self() {
            return (B)this;
        }

        public B setServiceFactory(ServiceFactory<ServiceT, OptionsT> serviceFactory) {
            this.serviceFactory = serviceFactory;
            return this.self();
        }

        public B setClock(ApiClock clock) {
            this.clock = clock;
            return this.self();
        }

        public B setProjectId(String projectId) {
            this.projectId = projectId;
            return this.self();
        }

        public B setHost(String host) {
            this.host = host;
            return this.self();
        }

        public B setCredentials(Credentials credentials) {
            this.credentials = Preconditions.checkNotNull(credentials);
            if (this.projectId == null && credentials instanceof ServiceAccountCredentials) {
                this.projectId = ((ServiceAccountCredentials)credentials).getProjectId();
            }
            return this.self();
        }

        public B setRetrySettings(RetrySettings retrySettings) {
            this.retrySettings = retrySettings;
            return this.self();
        }

        public B setServiceRpcFactory(ServiceRpcFactory<OptionsT> serviceRpcFactory) {
            this.serviceRpcFactory = serviceRpcFactory;
            return this.self();
        }

        public B setTransportOptions(TransportOptions transportOptions) {
            this.transportOptions = transportOptions;
            return this.self();
        }

        @BetaApi
        public B setHeaderProvider(HeaderProvider headerProvider) {
            this.headerProvider = headerProvider;
            return this.self();
        }
    }
}

