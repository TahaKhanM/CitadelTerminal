/*
 * Decompiled with CFR 0.152.
 */
package com.google.longrunning;

import com.google.api.AnnotationsProto;
import com.google.protobuf.AnyProto;
import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.rpc.StatusProto;

public final class OperationsProto {
    static final Descriptors.Descriptor internal_static_google_longrunning_Operation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_Operation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_longrunning_GetOperationRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_GetOperationRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_longrunning_ListOperationsRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_longrunning_ListOperationsResponse_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_longrunning_CancelOperationRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_longrunning_DeleteOperationRequest_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_longrunning_DeleteOperationRequest_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    private OperationsProto() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        OperationsProto.registerAllExtensions((ExtensionRegistryLite)registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = new String[]{"\n#google/longrunning/operations.proto\u0012\u0012google.longrunning\u001a\u001cgoogle/api/annotations.proto\u001a\u0019google/protobuf/any.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a\u0017google/rpc/status.proto\"\u00a8\u0001\n\tOperation\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012&\n\bmetadata\u0018\u0002 \u0001(\u000b2\u0014.google.protobuf.Any\u0012\f\n\u0004done\u0018\u0003 \u0001(\b\u0012#\n\u0005error\u0018\u0004 \u0001(\u000b2\u0012.google.rpc.StatusH\u0000\u0012(\n\bresponse\u0018\u0005 \u0001(\u000b2\u0014.google.protobuf.AnyH\u0000B\b\n\u0006result\"#\n\u0013GetOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"\\\n\u0015ListOperationsRequest\u0012\f\n\u0004name\u0018\u0004 \u0001(\t\u0012\u000e\n\u0006filter\u0018\u0001 \u0001(\t\u0012\u0011\n\tpage_size\u0018\u0002 \u0001(\u0005\u0012\u0012\n\npage_token\u0018\u0003 \u0001(\t\"d\n\u0016ListOperationsResponse\u00121\n\noperations\u0018\u0001 \u0003(\u000b2\u001d.google.longrunning.Operation\u0012\u0017\n\u000fnext_page_token\u0018\u0002 \u0001(\t\"&\n\u0016CancelOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\"&\n\u0016DeleteOperationRequest\u0012\f\n\u0004name\u0018\u0001 \u0001(\t2\u008c\u0004\n\nOperations\u0012\u0086\u0001\n\u000eListOperations\u0012).google.longrunning.ListOperationsRequest\u001a*.google.longrunning.ListOperationsResponse\"\u001d\u0082\u00d3\u00e4\u0093\u0002\u0017\u0012\u0015/v1/{name=operations}\u0012x\n\fGetOperation\u0012'.google.longrunning.GetOperationRequest\u001a\u001d.google.longrunning.Operation\" \u0082\u00d3\u00e4\u0093\u0002\u001a\u0012\u0018/v1/{name=operations/**}\u0012w\n\u000fDeleteOperation\u0012*.google.longrunning.DeleteOperationRequest\u001a\u0016.google.protobuf.Empty\" \u0082\u00d3\u00e4\u0093\u0002\u001a*\u0018/v1/{name=operations/**}\u0012\u0081\u0001\n\u000fCancelOperation\u0012*.google.longrunning.CancelOperationRequest\u001a\u0016.google.protobuf.Empty\"*\u0082\u00d3\u00e4\u0093\u0002$\"\u001f/v1/{name=operations/**}:cancel:\u0001*B\u0094\u0001\n\u0016com.google.longrunningB\u000fOperationsProtoP\u0001Z=google.golang.org/genproto/googleapis/longrunning;longrunning\u00aa\u0002\u0012Google.LongRunning\u00ca\u0002\u0012Google\\LongRunningb\u0006proto3"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner(){

            @Override
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{AnnotationsProto.getDescriptor(), AnyProto.getDescriptor(), EmptyProto.getDescriptor(), StatusProto.getDescriptor()}, assigner);
        internal_static_google_longrunning_Operation_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(0);
        internal_static_google_longrunning_Operation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_Operation_descriptor, new String[]{"Name", "Metadata", "Done", "Error", "Response", "Result"});
        internal_static_google_longrunning_GetOperationRequest_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(1);
        internal_static_google_longrunning_GetOperationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_GetOperationRequest_descriptor, new String[]{"Name"});
        internal_static_google_longrunning_ListOperationsRequest_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(2);
        internal_static_google_longrunning_ListOperationsRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_ListOperationsRequest_descriptor, new String[]{"Name", "Filter", "PageSize", "PageToken"});
        internal_static_google_longrunning_ListOperationsResponse_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(3);
        internal_static_google_longrunning_ListOperationsResponse_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_ListOperationsResponse_descriptor, new String[]{"Operations", "NextPageToken"});
        internal_static_google_longrunning_CancelOperationRequest_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(4);
        internal_static_google_longrunning_CancelOperationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_CancelOperationRequest_descriptor, new String[]{"Name"});
        internal_static_google_longrunning_DeleteOperationRequest_descriptor = OperationsProto.getDescriptor().getMessageTypes().get(5);
        internal_static_google_longrunning_DeleteOperationRequest_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_google_longrunning_DeleteOperationRequest_descriptor, new String[]{"Name"});
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(AnnotationsProto.http);
        Descriptors.FileDescriptor.internalUpdateFileDescriptor(descriptor, registry);
        AnnotationsProto.getDescriptor();
        AnyProto.getDescriptor();
        EmptyProto.getDescriptor();
        StatusProto.getDescriptor();
    }
}

