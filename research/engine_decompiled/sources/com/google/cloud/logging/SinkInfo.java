/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.logging;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.logging.v2.LogSink;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SinkInfo
implements Serializable {
    private static final long serialVersionUID = 6652676315712662729L;
    private final String name;
    private final Destination destination;
    private final String filter;
    private final VersionFormat versionFormat;

    SinkInfo(BuilderImpl builder) {
        this.name = Preconditions.checkNotNull(builder.name);
        this.destination = Preconditions.checkNotNull(builder.destination);
        this.filter = builder.filter;
        this.versionFormat = builder.versionFormat;
    }

    public String getName() {
        return this.name;
    }

    public <T extends Destination> T getDestination() {
        return (T)this.destination;
    }

    public String getFilter() {
        return this.filter;
    }

    public VersionFormat getVersionFormat() {
        return this.versionFormat;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.name).add("destination", this.destination).add("filter", this.filter).add("versionFormat", (Object)this.versionFormat).toString();
    }

    final boolean baseEquals(SinkInfo sinkInfo) {
        return Objects.equals(this.name, sinkInfo.name) && Objects.equals(this.destination, sinkInfo.destination) && Objects.equals(this.filter, sinkInfo.filter) && Objects.equals((Object)this.versionFormat, (Object)sinkInfo.versionFormat);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(SinkInfo.class)) {
            return false;
        }
        return this.baseEquals((SinkInfo)obj);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.destination, this.filter, this.versionFormat});
    }

    public Builder toBuilder() {
        return new BuilderImpl(this);
    }

    public static Builder newBuilder(String name, Destination destination) {
        return new BuilderImpl(name, destination);
    }

    public static SinkInfo of(String name, Destination destination) {
        return new BuilderImpl(name, destination).build();
    }

    LogSink toPb(String projectId) {
        LogSink.Builder builder = LogSink.newBuilder().setName(this.name).setDestination(this.destination.toPb(projectId)).setOutputVersionFormat(this.versionFormat == null ? LogSink.VersionFormat.VERSION_FORMAT_UNSPECIFIED : this.versionFormat.toPb());
        if (this.filter != null) {
            builder.setFilter(this.filter);
        }
        return builder.build();
    }

    static SinkInfo fromPb(LogSink sinkPb) {
        Builder builder = SinkInfo.newBuilder(sinkPb.getName(), Destination.fromPb(sinkPb.getDestination())).setVersionFormat(VersionFormat.fromPb(sinkPb.getOutputVersionFormat()));
        if (!sinkPb.getFilter().equals("")) {
            builder.setFilter(sinkPb.getFilter());
        }
        return builder.build();
    }

    static final class BuilderImpl
    extends Builder {
        private String name;
        private Destination destination;
        private String filter;
        private VersionFormat versionFormat;

        BuilderImpl(String name, Destination destination) {
            this.name = name;
            this.destination = destination;
        }

        BuilderImpl(SinkInfo sink) {
            this.name = sink.name;
            this.destination = sink.destination;
            this.filter = sink.filter;
            this.versionFormat = sink.versionFormat;
        }

        @Override
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public Builder setDestination(Destination destination) {
            this.destination = destination;
            return this;
        }

        @Override
        public Builder setFilter(String filter2) {
            this.filter = filter2;
            return this;
        }

        @Override
        public Builder setVersionFormat(VersionFormat versionFormat) {
            this.versionFormat = versionFormat;
            return this;
        }

        @Override
        public SinkInfo build() {
            return new SinkInfo(this);
        }
    }

    public static abstract class Builder {
        public abstract Builder setName(String var1);

        public abstract Builder setDestination(Destination var1);

        public abstract Builder setFilter(String var1);

        public abstract Builder setVersionFormat(VersionFormat var1);

        public abstract SinkInfo build();
    }

    public static enum VersionFormat {
        V1(LogSink.VersionFormat.V1),
        V2(LogSink.VersionFormat.V2);

        private LogSink.VersionFormat versionPb;

        private VersionFormat(LogSink.VersionFormat versionPb) {
            this.versionPb = versionPb;
        }

        LogSink.VersionFormat toPb() {
            return this.versionPb;
        }

        static VersionFormat fromPb(LogSink.VersionFormat versionPb) {
            switch (versionPb) {
                case V1: {
                    return V1;
                }
                case V2: {
                    return V2;
                }
                case VERSION_FORMAT_UNSPECIFIED: {
                    return null;
                }
            }
            throw new IllegalArgumentException(versionPb + " is not a valid version");
        }
    }

    public static abstract class Destination
    implements Serializable {
        private static final long serialVersionUID = 5257964588379880017L;
        private final Type type;

        Destination(Type type) {
            this.type = Preconditions.checkNotNull(type);
        }

        public Type getType() {
            return this.type;
        }

        final boolean baseEquals(Destination other) {
            return this.type.equals((Object)other.type);
        }

        final int baseHashCode() {
            return Objects.hash(new Object[]{this.type});
        }

        abstract String toPb(String var1);

        static <T extends Destination> T fromPb(String destinationPb) {
            if (BucketDestination.matchesDestination(destinationPb)) {
                return (T)BucketDestination.fromPb(destinationPb);
            }
            if (DatasetDestination.matchesDestination(destinationPb)) {
                return (T)DatasetDestination.fromPb(destinationPb);
            }
            if (TopicDestination.matchesDestination(destinationPb)) {
                return (T)TopicDestination.fromPb(destinationPb);
            }
            throw new IllegalArgumentException(destinationPb + " is not a valid sink destination");
        }

        public static final class TopicDestination
        extends Destination {
            private static final long serialVersionUID = -8252473597084887048L;
            private static final String BASE_NAME = "pubsub.googleapis.com/";
            private static final String REGEX = "pubsub.googleapis.com/projects/([^/]+)/topics/([^/]+)";
            private static final Pattern PATTERN = Pattern.compile("pubsub.googleapis.com/projects/([^/]+)/topics/([^/]+)");
            private final String project;
            private final String topic;

            TopicDestination(String project, String topic) {
                super(Type.TOPIC);
                this.project = project;
                this.topic = Preconditions.checkNotNull(topic);
            }

            public String getProject() {
                return this.project;
            }

            public String getTopic() {
                return this.topic;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj == null || !(obj instanceof TopicDestination)) {
                    return false;
                }
                TopicDestination other = (TopicDestination)obj;
                return this.baseEquals(other) && Objects.equals(this.project, other.project) && Objects.equals(this.topic, other.topic);
            }

            public int hashCode() {
                return Objects.hash(this.baseHashCode(), this.project, this.topic);
            }

            public String toString() {
                return MoreObjects.toStringHelper(this).add("project", this.project).add("topic", this.topic).toString();
            }

            @Override
            String toPb(String projectId) {
                String project = this.project == null ? projectId : this.project;
                return "pubsub.googleapis.com/projects/" + project + "/topics/" + this.topic;
            }

            public static TopicDestination of(String project, String topic) {
                return new TopicDestination(project, topic);
            }

            public static TopicDestination of(String topic) {
                return new TopicDestination(null, topic);
            }

            static boolean matchesDestination(String destinationPb) {
                return PATTERN.matcher(destinationPb).matches();
            }

            static TopicDestination fromPb(String destinationPb) {
                Matcher matcher = PATTERN.matcher(destinationPb);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException(destinationPb + " is not a valid sink destination");
                }
                return new TopicDestination(matcher.group(1), matcher.group(2));
            }
        }

        public static final class DatasetDestination
        extends Destination {
            private static final long serialVersionUID = 6952354643801154411L;
            private static final String BASE_NAME = "bigquery.googleapis.com/";
            private static final String REGEX = "bigquery.googleapis.com/projects/([^/]+)/datasets/([^/]+)";
            private static final Pattern PATTERN = Pattern.compile("bigquery.googleapis.com/projects/([^/]+)/datasets/([^/]+)");
            private final String project;
            private final String dataset;

            DatasetDestination(String project, String dataset) {
                super(Type.DATASET);
                this.project = project;
                this.dataset = Preconditions.checkNotNull(dataset);
            }

            public String getProject() {
                return this.project;
            }

            public String getDataset() {
                return this.dataset;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj == null || !(obj instanceof DatasetDestination)) {
                    return false;
                }
                DatasetDestination other = (DatasetDestination)obj;
                return this.baseEquals(other) && Objects.equals(this.project, other.project) && Objects.equals(this.dataset, other.dataset);
            }

            public int hashCode() {
                return Objects.hash(this.baseHashCode(), this.project, this.dataset);
            }

            public String toString() {
                return MoreObjects.toStringHelper(this).add("project", this.project).add("dataset", this.dataset).toString();
            }

            @Override
            String toPb(String projectId) {
                String project = this.project == null ? projectId : this.project;
                return "bigquery.googleapis.com/projects/" + project + "/datasets/" + this.dataset;
            }

            public static DatasetDestination of(String project, String dataset) {
                return new DatasetDestination(project, dataset);
            }

            public static DatasetDestination of(String dataset) {
                return new DatasetDestination(null, dataset);
            }

            static boolean matchesDestination(String destinationPb) {
                return PATTERN.matcher(destinationPb).matches();
            }

            static DatasetDestination fromPb(String destinationPb) {
                Matcher matcher = PATTERN.matcher(destinationPb);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException(destinationPb + " is not a valid sink destination");
                }
                return new DatasetDestination(matcher.group(1), matcher.group(2));
            }
        }

        public static final class BucketDestination
        extends Destination {
            private static final long serialVersionUID = -7614931032119779091L;
            private static final String BASE_NAME = "storage.googleapis.com/";
            private static final String REGEX = "storage.googleapis.com/([^/]+)";
            private static final Pattern PATTERN = Pattern.compile("storage.googleapis.com/([^/]+)");
            private final String bucket;

            BucketDestination(String bucket) {
                super(Type.BUCKET);
                this.bucket = Preconditions.checkNotNull(bucket);
            }

            public String getBucket() {
                return this.bucket;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj == null || !(obj instanceof BucketDestination)) {
                    return false;
                }
                BucketDestination other = (BucketDestination)obj;
                return this.baseEquals(other) && this.bucket.equals(other.bucket);
            }

            public int hashCode() {
                return Objects.hash(this.baseHashCode(), this.bucket);
            }

            static boolean matchesDestination(String destinationPb) {
                return PATTERN.matcher(destinationPb).matches();
            }

            public String toString() {
                return MoreObjects.toStringHelper(this).add("bucket", this.bucket).toString();
            }

            @Override
            String toPb(String projectId) {
                return BASE_NAME + this.bucket;
            }

            public static BucketDestination of(String bucket) {
                return new BucketDestination(bucket);
            }

            static BucketDestination fromPb(String destinationPb) {
                Matcher matcher = PATTERN.matcher(destinationPb);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException(destinationPb + " is not a valid sink destination");
                }
                return new BucketDestination(matcher.group(1));
            }
        }

        public static enum Type {
            BUCKET,
            DATASET,
            TOPIC;

        }
    }
}

