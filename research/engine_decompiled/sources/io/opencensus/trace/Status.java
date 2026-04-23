/*
 * Decompiled with CFR 0.152.
 */
package io.opencensus.trace;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class Status {
    private static final List<Status> STATUS_LIST = Status.buildStatusList();
    public static final Status OK = CanonicalCode.OK.toStatus();
    public static final Status CANCELLED = CanonicalCode.CANCELLED.toStatus();
    public static final Status UNKNOWN = CanonicalCode.UNKNOWN.toStatus();
    public static final Status INVALID_ARGUMENT = CanonicalCode.INVALID_ARGUMENT.toStatus();
    public static final Status DEADLINE_EXCEEDED = CanonicalCode.DEADLINE_EXCEEDED.toStatus();
    public static final Status NOT_FOUND = CanonicalCode.NOT_FOUND.toStatus();
    public static final Status ALREADY_EXISTS = CanonicalCode.ALREADY_EXISTS.toStatus();
    public static final Status PERMISSION_DENIED = CanonicalCode.PERMISSION_DENIED.toStatus();
    public static final Status UNAUTHENTICATED = CanonicalCode.UNAUTHENTICATED.toStatus();
    public static final Status RESOURCE_EXHAUSTED = CanonicalCode.RESOURCE_EXHAUSTED.toStatus();
    public static final Status FAILED_PRECONDITION = CanonicalCode.FAILED_PRECONDITION.toStatus();
    public static final Status ABORTED = CanonicalCode.ABORTED.toStatus();
    public static final Status OUT_OF_RANGE = CanonicalCode.OUT_OF_RANGE.toStatus();
    public static final Status UNIMPLEMENTED = CanonicalCode.UNIMPLEMENTED.toStatus();
    public static final Status INTERNAL = CanonicalCode.INTERNAL.toStatus();
    public static final Status UNAVAILABLE = CanonicalCode.UNAVAILABLE.toStatus();
    public static final Status DATA_LOSS = CanonicalCode.DATA_LOSS.toStatus();
    private final CanonicalCode canonicalCode;
    @Nullable
    private final String description;

    private static List<Status> buildStatusList() {
        TreeMap<Integer, Status> canonicalizer = new TreeMap<Integer, Status>();
        for (CanonicalCode code : CanonicalCode.values()) {
            Status replaced = canonicalizer.put(code.value(), new Status(code, null));
            if (replaced == null) continue;
            throw new IllegalStateException("Code value duplication between " + replaced.getCanonicalCode().name() + " & " + code.name());
        }
        return Collections.unmodifiableList(new ArrayList(canonicalizer.values()));
    }

    private Status(CanonicalCode canonicalCode, @Nullable String description) {
        this.canonicalCode = Preconditions.checkNotNull(canonicalCode, "canonicalCode");
        this.description = description;
    }

    public Status withDescription(String description) {
        if (Objects.equal(this.description, description)) {
            return this;
        }
        return new Status(this.canonicalCode, description);
    }

    public CanonicalCode getCanonicalCode() {
        return this.canonicalCode;
    }

    @Nullable
    public String getDescription() {
        return this.description;
    }

    public boolean isOk() {
        return CanonicalCode.OK == this.canonicalCode;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Status)) {
            return false;
        }
        Status that = (Status)obj;
        return this.canonicalCode == that.canonicalCode && Objects.equal(this.description, that.description);
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.canonicalCode, this.description});
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("canonicalCode", (Object)this.canonicalCode).add("description", this.description).toString();
    }

    public static enum CanonicalCode {
        OK(0),
        CANCELLED(1),
        UNKNOWN(2),
        INVALID_ARGUMENT(3),
        DEADLINE_EXCEEDED(4),
        NOT_FOUND(5),
        ALREADY_EXISTS(6),
        PERMISSION_DENIED(7),
        RESOURCE_EXHAUSTED(8),
        FAILED_PRECONDITION(9),
        ABORTED(10),
        OUT_OF_RANGE(11),
        UNIMPLEMENTED(12),
        INTERNAL(13),
        UNAVAILABLE(14),
        DATA_LOSS(15),
        UNAUTHENTICATED(16);

        private final int value;

        private CanonicalCode(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        @VisibleForTesting
        public Status toStatus() {
            return (Status)STATUS_LIST.get(this.value);
        }
    }
}

