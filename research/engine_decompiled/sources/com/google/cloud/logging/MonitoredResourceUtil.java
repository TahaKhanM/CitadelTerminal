/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.cloud.MetadataConfig;
import com.google.cloud.MonitoredResource;
import com.google.cloud.ServiceOptions;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.LoggingEnhancer;
import com.google.cloud.logging.TraceLoggingEnhancer;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMultimap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonitoredResourceUtil {
    private static final String APPENGINE_LABEL_PREFIX = "appengine.googleapis.com/";
    private static ImmutableMultimap<String, Label> resourceTypeWithLabels = ImmutableMultimap.builder().putAll(Resource.GaeAppFlex.getKey(), Label.ModuleId, Label.VersionId, Label.Zone).putAll(Resource.GaeAppStandard.getKey(), (Label[])new Label[]{Label.ModuleId, Label.VersionId}).putAll(Resource.Container.getKey(), (Label[])new Label[]{Label.ClusterName, Label.InstanceId, Label.PodId, Label.Zone}).putAll(Resource.GceInstance.getKey(), (Label[])new Label[]{Label.InstanceId, Label.Zone}).build();

    private MonitoredResourceUtil() {
    }

    public static MonitoredResource getResource(String projectId, String resourceTypeParam) {
        String resourceType = resourceTypeParam;
        if (Strings.isNullOrEmpty(resourceType)) {
            Resource detectedResourceType = MonitoredResourceUtil.getAutoDetectedResourceType();
            resourceType = detectedResourceType.getKey();
        }
        String resourceName = resourceType.startsWith("gae_app") ? "gae_app" : resourceType;
        MonitoredResource.Builder builder = MonitoredResource.newBuilder(resourceName).addLabel(Label.ProjectId.getKey(), projectId);
        for (Label label : resourceTypeWithLabels.get((Object)resourceType)) {
            String value = MonitoredResourceUtil.getValue(label);
            if (value == null) continue;
            builder.addLabel(label.getKey(), value);
        }
        return builder.build();
    }

    public static List<LoggingEnhancer> getResourceEnhancers() {
        Resource resourceType = MonitoredResourceUtil.getAutoDetectedResourceType();
        return MonitoredResourceUtil.createEnhancers(resourceType);
    }

    private static String getValue(Label label) {
        String value;
        switch (label) {
            case AppId: {
                value = ServiceOptions.getAppEngineAppId();
                break;
            }
            case ClusterName: {
                value = MetadataConfig.getClusterName();
                break;
            }
            case InstanceId: {
                value = MetadataConfig.getInstanceId();
                break;
            }
            case InstanceName: {
                value = MonitoredResourceUtil.getAppEngineInstanceName();
                break;
            }
            case ModuleId: {
                value = MonitoredResourceUtil.getAppEngineModuleId();
                break;
            }
            case PodId: {
                value = System.getenv("HOSTNAME");
                break;
            }
            case VersionId: {
                value = MonitoredResourceUtil.getAppEngineVersionId();
                break;
            }
            case Zone: {
                value = MetadataConfig.getZone();
                break;
            }
            default: {
                value = null;
            }
        }
        return value;
    }

    private static Resource getAutoDetectedResourceType() {
        if (System.getenv("GAE_INSTANCE") != null) {
            return Resource.GaeAppFlex;
        }
        if (System.getenv("KUBERNETES_SERVICE_HOST") != null) {
            return Resource.Container;
        }
        if (ServiceOptions.getAppEngineAppId() != null) {
            return Resource.GaeAppStandard;
        }
        if (MetadataConfig.getInstanceId() != null) {
            return Resource.GceInstance;
        }
        return Resource.Global;
    }

    private static String getAppEngineModuleId() {
        return System.getenv("GAE_SERVICE");
    }

    private static String getAppEngineVersionId() {
        return System.getenv("GAE_VERSION");
    }

    private static String getAppEngineInstanceName() {
        return System.getenv("GAE_INSTANCE");
    }

    private static List<LoggingEnhancer> createEnhancers(Resource resourceType) {
        ArrayList<LoggingEnhancer> enhancers = new ArrayList<LoggingEnhancer>(2);
        switch (resourceType) {
            case GaeAppFlex: {
                enhancers.add(new LabelLoggingEnhancer(APPENGINE_LABEL_PREFIX, Collections.singletonList(Label.InstanceName)));
                enhancers.add(new TraceLoggingEnhancer(APPENGINE_LABEL_PREFIX));
                break;
            }
            case GaeAppStandard: {
                enhancers.add(new TraceLoggingEnhancer(APPENGINE_LABEL_PREFIX));
                break;
            }
        }
        return enhancers;
    }

    private static class LabelLoggingEnhancer
    implements LoggingEnhancer {
        private final Map<String, String> labels = new HashMap<String, String>();

        LabelLoggingEnhancer(String prefix, List<Label> labelNames) {
            if (labelNames != null) {
                for (Label labelName : labelNames) {
                    String labelValue = MonitoredResourceUtil.getValue(labelName);
                    if (labelValue == null) continue;
                    String fullLabelName = prefix != null ? prefix + labelName.getKey() : labelName.getKey();
                    this.labels.put(fullLabelName, labelValue);
                }
            }
        }

        @Override
        public void enhanceLogEntry(LogEntry.Builder logEntry) {
            for (Map.Entry<String, String> label : this.labels.entrySet()) {
                logEntry.addLabel(label.getKey(), label.getValue());
            }
        }
    }

    private static enum Resource {
        Container("container"),
        GaeAppFlex("gae_app_flex"),
        GaeAppStandard("gae_app_standard"),
        GceInstance("gce_instance"),
        Global("global");

        private final String key;

        private Resource(String key) {
            this.key = key;
        }

        String getKey() {
            return this.key;
        }
    }

    private static enum Label {
        AppId("app_id"),
        ClusterName("cluster_name"),
        InstanceId("instance_id"),
        InstanceName("instance_name"),
        ModuleId("module_id"),
        PodId("pod_id"),
        ProjectId("project_id"),
        VersionId("version_id"),
        Zone("zone");

        private final String key;

        private Label(String key) {
            this.key = key;
        }

        String getKey() {
            return this.key;
        }
    }
}

