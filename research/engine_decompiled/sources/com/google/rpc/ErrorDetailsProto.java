/*
 * Decompiled with CFR 0.152.
 */
package com.google.rpc;

import com.google.protobuf.Descriptors;
import com.google.protobuf.DurationProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

public final class ErrorDetailsProto {
    static final Descriptors.Descriptor internal_static_google_rpc_RetryInfo_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_RetryInfo_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_DebugInfo_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_DebugInfo_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_QuotaFailure_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_QuotaFailure_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_QuotaFailure_Violation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_PreconditionFailure_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_PreconditionFailure_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_BadRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_BadRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_RequestInfo_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_RequestInfo_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_ResourceInfo_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_ResourceInfo_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_Help_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_Help_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_Help_Link_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_Help_Link_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_rpc_LocalizedMessage_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_rpc_LocalizedMessage_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private ErrorDetailsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        ErrorDetailsProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n\u001egoogle/rpc/error_details.proto\u0012\ngoogle.rpc\u001a\u001egoogle/protobuf/duration.proto\";\n\tRetryInfo\u0012.\n\u000bretry_delay\u0018\u0001 \u0001(\u000b2\u0019.google.protobuf.Duration\"2\n\tDebugInfo\u0012\u0015\n\rstack_entries\u0018\u0001 \u0003(\t\u0012\u000e\n\u0006detail\u0018\u0002 \u0001(\t\"y\n\fQuotaFailure\u00126\n\nviolations\u0018\u0001 \u0003(\u000b2\".google.rpc.QuotaFailure.Violation\u001a1\n\tViolation\u0012\u000f\n\u0007subject\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"\u0095\u0001\n\u0013PreconditionFailure\u0012=\n\nviolations\u0018\u0001 \u0003(\u000b2).google.rpc.PreconditionFailure.Violation\u001a?\n\tViolation\u0012\f\n\u0004type\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007subject\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\"\u0083\u0001\n\nBadRequest\u0012?\n\u0010field_violations\u0018\u0001 \u0003(\u000b2%.google.rpc.BadRequest.FieldViolation\u001a4\n\u000eFieldViolation\u0012\r\n\u0005field\u0018\u0001 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0002 \u0001(\t\"7\n\u000bRequestInfo\u0012\u0012\n\nrequest_id\u0018\u0001 \u0001(\t\u0012\u0014\n\fserving_data\u0018\u0002 \u0001(\t\"`\n\fResourceInfo\u0012\u0015\n\rresource_type\u0018\u0001 \u0001(\t\u0012\u0015\n\rresource_name\u0018\u0002 \u0001(\t\u0012\r\n\u0005owner\u0018\u0003 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0004 \u0001(\t\"V\n\u0004Help\u0012$\n\u0005links\u0018\u0001 \u0003(\u000b2\u0015.google.rpc.Help.Link\u001a(\n\u0004Link\u0012\u0013\n\u000bdescription\u0018\u0001 \u0001(\t\u0012\u000b\n\u0003url\u0018\u0002 \u0001(\t\"3\n\u0010LocalizedMessage\u0012\u000e\n\u0006locale\u0018\u0001 \u0001(\t\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\tBl\n\u000ecom.google.rpcB\u0011ErrorDetailsProtoP\u0001Z?google.golang.org/genproto/googleapis/rpc/errdetails;errdetails\u00a2\u0002\u0003RPCb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{DurationProto.getDescriptor()}, assigner);
        internal_static_google_rpc_RetryInfo_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_rpc_RetryInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_RetryInfo_descriptor, new String[]{"RetryDelay"});
        internal_static_google_rpc_DebugInfo_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_rpc_DebugInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_DebugInfo_descriptor, new String[]{"StackEntries", "Detail"});
        internal_static_google_rpc_QuotaFailure_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_rpc_QuotaFailure_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_QuotaFailure_descriptor, new String[]{"Violations"});
        internal_static_google_rpc_QuotaFailure_Violation_descriptor = internal_static_google_rpc_QuotaFailure_descriptor.getNestedTypes().get(0);
        internal_static_google_rpc_QuotaFailure_Violation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_QuotaFailure_Violation_descriptor, new String[]{"Subject", "Description"});
        internal_static_google_rpc_PreconditionFailure_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_rpc_PreconditionFailure_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_PreconditionFailure_descriptor, new String[]{"Violations"});
        internal_static_google_rpc_PreconditionFailure_Violation_descriptor = internal_static_google_rpc_PreconditionFailure_descriptor.getNestedTypes().get(0);
        internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_PreconditionFailure_Violation_descriptor, new String[]{"Type", "Subject", "Description"});
        internal_static_google_rpc_BadRequest_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(4);
        internal_static_google_rpc_BadRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_BadRequest_descriptor, new String[]{"FieldViolations"});
        internal_static_google_rpc_BadRequest_FieldViolation_descriptor = internal_static_google_rpc_BadRequest_descriptor.getNestedTypes().get(0);
        internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_BadRequest_FieldViolation_descriptor, new String[]{"Field", "Description"});
        internal_static_google_rpc_RequestInfo_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(5);
        internal_static_google_rpc_RequestInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_RequestInfo_descriptor, new String[]{"RequestId", "ServingData"});
        internal_static_google_rpc_ResourceInfo_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(6);
        internal_static_google_rpc_ResourceInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_ResourceInfo_descriptor, new String[]{"ResourceType", "ResourceName", "Owner", "Description"});
        internal_static_google_rpc_Help_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(7);
        internal_static_google_rpc_Help_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_Help_descriptor, new String[]{"Links"});
        internal_static_google_rpc_Help_Link_descriptor = internal_static_google_rpc_Help_descriptor.getNestedTypes().get(0);
        internal_static_google_rpc_Help_Link_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_Help_Link_descriptor, new String[]{"Description", "Url"});
        internal_static_google_rpc_LocalizedMessage_descriptor = ErrorDetailsProto.getDescriptor().getMessageTypes().get(8);
        internal_static_google_rpc_LocalizedMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_rpc_LocalizedMessage_descriptor, new String[]{"Locale", "Message"});
        DurationProto.getDescriptor();
    }
}

