/*
 * Decompiled with CFR 0.152.
 */
package com.google.type;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.PostalAddressOrBuilder;
import com.google.type.PostalAddressProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class PostalAddress
extends GeneratedMessageV3
implements PostalAddressOrBuilder {
    private static final long serialVersionUID = 0L;
    private int bitField0_;
    public static final int REVISION_FIELD_NUMBER = 1;
    private int revision_;
    public static final int REGION_CODE_FIELD_NUMBER = 2;
    private volatile Object regionCode_;
    public static final int LANGUAGE_CODE_FIELD_NUMBER = 3;
    private volatile Object languageCode_;
    public static final int POSTAL_CODE_FIELD_NUMBER = 4;
    private volatile Object postalCode_;
    public static final int SORTING_CODE_FIELD_NUMBER = 5;
    private volatile Object sortingCode_;
    public static final int ADMINISTRATIVE_AREA_FIELD_NUMBER = 6;
    private volatile Object administrativeArea_;
    public static final int LOCALITY_FIELD_NUMBER = 7;
    private volatile Object locality_;
    public static final int SUBLOCALITY_FIELD_NUMBER = 8;
    private volatile Object sublocality_;
    public static final int ADDRESS_LINES_FIELD_NUMBER = 9;
    private LazyStringList addressLines_;
    public static final int RECIPIENTS_FIELD_NUMBER = 10;
    private LazyStringList recipients_;
    public static final int ORGANIZATION_FIELD_NUMBER = 11;
    private volatile Object organization_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final PostalAddress DEFAULT_INSTANCE = new PostalAddress();
    private static final Parser<PostalAddress> PARSER = new AbstractParser<PostalAddress>(){

        @Override
        public PostalAddress parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new PostalAddress(input2, extensionRegistry);
        }
    };

    private PostalAddress(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private PostalAddress() {
        this.revision_ = 0;
        this.regionCode_ = "";
        this.languageCode_ = "";
        this.postalCode_ = "";
        this.sortingCode_ = "";
        this.administrativeArea_ = "";
        this.locality_ = "";
        this.sublocality_ = "";
        this.addressLines_ = LazyStringArrayList.EMPTY;
        this.recipients_ = LazyStringArrayList.EMPTY;
        this.organization_ = "";
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PostalAddress(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block20: while (!done) {
                String s2;
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block20;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block20;
                        done = true;
                        continue block20;
                    }
                    case 8: {
                        this.revision_ = input2.readInt32();
                        continue block20;
                    }
                    case 18: {
                        s2 = input2.readStringRequireUtf8();
                        this.regionCode_ = s2;
                        continue block20;
                    }
                    case 26: {
                        s2 = input2.readStringRequireUtf8();
                        this.languageCode_ = s2;
                        continue block20;
                    }
                    case 34: {
                        s2 = input2.readStringRequireUtf8();
                        this.postalCode_ = s2;
                        continue block20;
                    }
                    case 42: {
                        s2 = input2.readStringRequireUtf8();
                        this.sortingCode_ = s2;
                        continue block20;
                    }
                    case 50: {
                        s2 = input2.readStringRequireUtf8();
                        this.administrativeArea_ = s2;
                        continue block20;
                    }
                    case 58: {
                        s2 = input2.readStringRequireUtf8();
                        this.locality_ = s2;
                        continue block20;
                    }
                    case 66: {
                        s2 = input2.readStringRequireUtf8();
                        this.sublocality_ = s2;
                        continue block20;
                    }
                    case 74: {
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 0x100) != 256) {
                            this.addressLines_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 0x100;
                        }
                        this.addressLines_.add(s2);
                        continue block20;
                    }
                    case 82: {
                        s2 = input2.readStringRequireUtf8();
                        if ((mutable_bitField0_ & 0x200) != 512) {
                            this.recipients_ = new LazyStringArrayList();
                            mutable_bitField0_ |= 0x200;
                        }
                        this.recipients_.add(s2);
                        continue block20;
                    }
                    case 90: 
                }
                s2 = input2.readStringRequireUtf8();
                this.organization_ = s2;
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            if ((mutable_bitField0_ & 0x100) == 256) {
                this.addressLines_ = this.addressLines_.getUnmodifiableView();
            }
            if ((mutable_bitField0_ & 0x200) == 512) {
                this.recipients_ = this.recipients_.getUnmodifiableView();
            }
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
    }

    @Override
    public int getRevision() {
        return this.revision_;
    }

    @Override
    public String getRegionCode() {
        Object ref = this.regionCode_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.regionCode_ = s2;
        return s2;
    }

    @Override
    public ByteString getRegionCodeBytes() {
        Object ref = this.regionCode_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.regionCode_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getLanguageCode() {
        Object ref = this.languageCode_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.languageCode_ = s2;
        return s2;
    }

    @Override
    public ByteString getLanguageCodeBytes() {
        Object ref = this.languageCode_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.languageCode_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getPostalCode() {
        Object ref = this.postalCode_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.postalCode_ = s2;
        return s2;
    }

    @Override
    public ByteString getPostalCodeBytes() {
        Object ref = this.postalCode_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.postalCode_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getSortingCode() {
        Object ref = this.sortingCode_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.sortingCode_ = s2;
        return s2;
    }

    @Override
    public ByteString getSortingCodeBytes() {
        Object ref = this.sortingCode_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.sortingCode_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getAdministrativeArea() {
        Object ref = this.administrativeArea_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.administrativeArea_ = s2;
        return s2;
    }

    @Override
    public ByteString getAdministrativeAreaBytes() {
        Object ref = this.administrativeArea_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.administrativeArea_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getLocality() {
        Object ref = this.locality_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.locality_ = s2;
        return s2;
    }

    @Override
    public ByteString getLocalityBytes() {
        Object ref = this.locality_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.locality_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public String getSublocality() {
        Object ref = this.sublocality_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.sublocality_ = s2;
        return s2;
    }

    @Override
    public ByteString getSublocalityBytes() {
        Object ref = this.sublocality_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.sublocality_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    public ProtocolStringList getAddressLinesList() {
        return this.addressLines_;
    }

    @Override
    public int getAddressLinesCount() {
        return this.addressLines_.size();
    }

    @Override
    public String getAddressLines(int index) {
        return (String)this.addressLines_.get(index);
    }

    @Override
    public ByteString getAddressLinesBytes(int index) {
        return this.addressLines_.getByteString(index);
    }

    public ProtocolStringList getRecipientsList() {
        return this.recipients_;
    }

    @Override
    public int getRecipientsCount() {
        return this.recipients_.size();
    }

    @Override
    public String getRecipients(int index) {
        return (String)this.recipients_.get(index);
    }

    @Override
    public ByteString getRecipientsBytes(int index) {
        return this.recipients_.getByteString(index);
    }

    @Override
    public String getOrganization() {
        Object ref = this.organization_;
        if (ref instanceof String) {
            return (String)ref;
        }
        ByteString bs = (ByteString)ref;
        String s2 = bs.toStringUtf8();
        this.organization_ = s2;
        return s2;
    }

    @Override
    public ByteString getOrganizationBytes() {
        Object ref = this.organization_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String)ref);
            this.organization_ = b;
            return b;
        }
        return (ByteString)ref;
    }

    @Override
    public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(CodedOutputStream output) throws IOException {
        int i;
        if (this.revision_ != 0) {
            output.writeInt32(1, this.revision_);
        }
        if (!this.getRegionCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.regionCode_);
        }
        if (!this.getLanguageCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 3, this.languageCode_);
        }
        if (!this.getPostalCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 4, this.postalCode_);
        }
        if (!this.getSortingCodeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.sortingCode_);
        }
        if (!this.getAdministrativeAreaBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 6, this.administrativeArea_);
        }
        if (!this.getLocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 7, this.locality_);
        }
        if (!this.getSublocalityBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 8, this.sublocality_);
        }
        for (i = 0; i < this.addressLines_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 9, this.addressLines_.getRaw(i));
        }
        for (i = 0; i < this.recipients_.size(); ++i) {
            GeneratedMessageV3.writeString(output, 10, this.recipients_.getRaw(i));
        }
        if (!this.getOrganizationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 11, this.organization_);
        }
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int i;
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (this.revision_ != 0) {
            size2 += CodedOutputStream.computeInt32Size(1, this.revision_);
        }
        if (!this.getRegionCodeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.regionCode_);
        }
        if (!this.getLanguageCodeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(3, this.languageCode_);
        }
        if (!this.getPostalCodeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(4, this.postalCode_);
        }
        if (!this.getSortingCodeBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.sortingCode_);
        }
        if (!this.getAdministrativeAreaBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(6, this.administrativeArea_);
        }
        if (!this.getLocalityBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(7, this.locality_);
        }
        if (!this.getSublocalityBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(8, this.sublocality_);
        }
        int dataSize = 0;
        for (i = 0; i < this.addressLines_.size(); ++i) {
            dataSize += PostalAddress.computeStringSizeNoTag(this.addressLines_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getAddressLinesList().size();
        dataSize = 0;
        for (i = 0; i < this.recipients_.size(); ++i) {
            dataSize += PostalAddress.computeStringSizeNoTag(this.recipients_.getRaw(i));
        }
        size2 += dataSize;
        size2 += 1 * this.getRecipientsList().size();
        if (!this.getOrganizationBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.organization_);
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PostalAddress)) {
            return super.equals(obj);
        }
        PostalAddress other = (PostalAddress)obj;
        boolean result2 = true;
        result2 = result2 && this.getRevision() == other.getRevision();
        result2 = result2 && this.getRegionCode().equals(other.getRegionCode());
        result2 = result2 && this.getLanguageCode().equals(other.getLanguageCode());
        result2 = result2 && this.getPostalCode().equals(other.getPostalCode());
        result2 = result2 && this.getSortingCode().equals(other.getSortingCode());
        result2 = result2 && this.getAdministrativeArea().equals(other.getAdministrativeArea());
        result2 = result2 && this.getLocality().equals(other.getLocality());
        result2 = result2 && this.getSublocality().equals(other.getSublocality());
        result2 = result2 && this.getAddressLinesList().equals(other.getAddressLinesList());
        result2 = result2 && this.getRecipientsList().equals(other.getRecipientsList());
        result2 = result2 && this.getOrganization().equals(other.getOrganization());
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + PostalAddress.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + this.getRevision();
        hash = 37 * hash + 2;
        hash = 53 * hash + this.getRegionCode().hashCode();
        hash = 37 * hash + 3;
        hash = 53 * hash + this.getLanguageCode().hashCode();
        hash = 37 * hash + 4;
        hash = 53 * hash + this.getPostalCode().hashCode();
        hash = 37 * hash + 5;
        hash = 53 * hash + this.getSortingCode().hashCode();
        hash = 37 * hash + 6;
        hash = 53 * hash + this.getAdministrativeArea().hashCode();
        hash = 37 * hash + 7;
        hash = 53 * hash + this.getLocality().hashCode();
        hash = 37 * hash + 8;
        hash = 53 * hash + this.getSublocality().hashCode();
        if (this.getAddressLinesCount() > 0) {
            hash = 37 * hash + 9;
            hash = 53 * hash + this.getAddressLinesList().hashCode();
        }
        if (this.getRecipientsCount() > 0) {
            hash = 37 * hash + 10;
            hash = 53 * hash + this.getRecipientsList().hashCode();
        }
        hash = 37 * hash + 11;
        hash = 53 * hash + this.getOrganization().hashCode();
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static PostalAddress parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PostalAddress parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PostalAddress parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PostalAddress parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PostalAddress parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static PostalAddress parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static PostalAddress parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static PostalAddress parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static PostalAddress parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static PostalAddress parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static PostalAddress parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static PostalAddress parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return PostalAddress.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PostalAddress prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    public static PostalAddress getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PostalAddress> parser() {
        return PARSER;
    }

    public Parser<PostalAddress> getParserForType() {
        return PARSER;
    }

    @Override
    public PostalAddress getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements PostalAddressOrBuilder {
        private int bitField0_;
        private int revision_;
        private Object regionCode_ = "";
        private Object languageCode_ = "";
        private Object postalCode_ = "";
        private Object sortingCode_ = "";
        private Object administrativeArea_ = "";
        private Object locality_ = "";
        private Object sublocality_ = "";
        private LazyStringList addressLines_ = LazyStringArrayList.EMPTY;
        private LazyStringList recipients_ = LazyStringArrayList.EMPTY;
        private Object organization_ = "";

        public static final Descriptors.Descriptor getDescriptor() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_fieldAccessorTable.ensureFieldAccessorsInitialized(PostalAddress.class, Builder.class);
        }

        private Builder() {
            this.maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (alwaysUseFieldBuilders) {
                // empty if block
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            this.revision_ = 0;
            this.regionCode_ = "";
            this.languageCode_ = "";
            this.postalCode_ = "";
            this.sortingCode_ = "";
            this.administrativeArea_ = "";
            this.locality_ = "";
            this.sublocality_ = "";
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFEFF;
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFDFF;
            this.organization_ = "";
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return PostalAddressProto.internal_static_google_type_PostalAddress_descriptor;
        }

        @Override
        public PostalAddress getDefaultInstanceForType() {
            return PostalAddress.getDefaultInstance();
        }

        @Override
        public PostalAddress build() {
            PostalAddress result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public PostalAddress buildPartial() {
            PostalAddress result2 = new PostalAddress(this);
            int from_bitField0_ = this.bitField0_;
            int to_bitField0_ = 0;
            result2.revision_ = this.revision_;
            result2.regionCode_ = this.regionCode_;
            result2.languageCode_ = this.languageCode_;
            result2.postalCode_ = this.postalCode_;
            result2.sortingCode_ = this.sortingCode_;
            result2.administrativeArea_ = this.administrativeArea_;
            result2.locality_ = this.locality_;
            result2.sublocality_ = this.sublocality_;
            if ((this.bitField0_ & 0x100) == 256) {
                this.addressLines_ = this.addressLines_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFEFF;
            }
            result2.addressLines_ = this.addressLines_;
            if ((this.bitField0_ & 0x200) == 512) {
                this.recipients_ = this.recipients_.getUnmodifiableView();
                this.bitField0_ &= 0xFFFFFDFF;
            }
            result2.recipients_ = this.recipients_;
            result2.organization_ = this.organization_;
            result2.bitField0_ = to_bitField0_;
            this.onBuilt();
            return result2;
        }

        @Override
        public Builder clone() {
            return (Builder)super.clone();
        }

        @Override
        public Builder setField(Descriptors.FieldDescriptor field2, Object value) {
            return (Builder)super.setField(field2, value);
        }

        @Override
        public Builder clearField(Descriptors.FieldDescriptor field2) {
            return (Builder)super.clearField(field2);
        }

        @Override
        public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder)super.clearOneof(oneof);
        }

        @Override
        public Builder setRepeatedField(Descriptors.FieldDescriptor field2, int index, Object value) {
            return (Builder)super.setRepeatedField(field2, index, value);
        }

        @Override
        public Builder addRepeatedField(Descriptors.FieldDescriptor field2, Object value) {
            return (Builder)super.addRepeatedField(field2, value);
        }

        @Override
        public Builder mergeFrom(Message other) {
            if (other instanceof PostalAddress) {
                return this.mergeFrom((PostalAddress)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(PostalAddress other) {
            if (other == PostalAddress.getDefaultInstance()) {
                return this;
            }
            if (other.getRevision() != 0) {
                this.setRevision(other.getRevision());
            }
            if (!other.getRegionCode().isEmpty()) {
                this.regionCode_ = other.regionCode_;
                this.onChanged();
            }
            if (!other.getLanguageCode().isEmpty()) {
                this.languageCode_ = other.languageCode_;
                this.onChanged();
            }
            if (!other.getPostalCode().isEmpty()) {
                this.postalCode_ = other.postalCode_;
                this.onChanged();
            }
            if (!other.getSortingCode().isEmpty()) {
                this.sortingCode_ = other.sortingCode_;
                this.onChanged();
            }
            if (!other.getAdministrativeArea().isEmpty()) {
                this.administrativeArea_ = other.administrativeArea_;
                this.onChanged();
            }
            if (!other.getLocality().isEmpty()) {
                this.locality_ = other.locality_;
                this.onChanged();
            }
            if (!other.getSublocality().isEmpty()) {
                this.sublocality_ = other.sublocality_;
                this.onChanged();
            }
            if (!other.addressLines_.isEmpty()) {
                if (this.addressLines_.isEmpty()) {
                    this.addressLines_ = other.addressLines_;
                    this.bitField0_ &= 0xFFFFFEFF;
                } else {
                    this.ensureAddressLinesIsMutable();
                    this.addressLines_.addAll(other.addressLines_);
                }
                this.onChanged();
            }
            if (!other.recipients_.isEmpty()) {
                if (this.recipients_.isEmpty()) {
                    this.recipients_ = other.recipients_;
                    this.bitField0_ &= 0xFFFFFDFF;
                } else {
                    this.ensureRecipientsIsMutable();
                    this.recipients_.addAll(other.recipients_);
                }
                this.onChanged();
            }
            if (!other.getOrganization().isEmpty()) {
                this.organization_ = other.organization_;
                this.onChanged();
            }
            this.mergeUnknownFields(other.unknownFields);
            this.onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
            PostalAddress parsedMessage = null;
            try {
                parsedMessage = (PostalAddress)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (PostalAddress)e.getUnfinishedMessage();
                throw e.unwrapIOException();
            }
            finally {
                if (parsedMessage != null) {
                    this.mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        @Override
        public int getRevision() {
            return this.revision_;
        }

        public Builder setRevision(int value) {
            this.revision_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearRevision() {
            this.revision_ = 0;
            this.onChanged();
            return this;
        }

        @Override
        public String getRegionCode() {
            Object ref = this.regionCode_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.regionCode_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getRegionCodeBytes() {
            Object ref = this.regionCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.regionCode_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setRegionCode(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.regionCode_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearRegionCode() {
            this.regionCode_ = PostalAddress.getDefaultInstance().getRegionCode();
            this.onChanged();
            return this;
        }

        public Builder setRegionCodeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.regionCode_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getLanguageCode() {
            Object ref = this.languageCode_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.languageCode_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getLanguageCodeBytes() {
            Object ref = this.languageCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.languageCode_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setLanguageCode(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.languageCode_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLanguageCode() {
            this.languageCode_ = PostalAddress.getDefaultInstance().getLanguageCode();
            this.onChanged();
            return this;
        }

        public Builder setLanguageCodeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.languageCode_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getPostalCode() {
            Object ref = this.postalCode_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.postalCode_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getPostalCodeBytes() {
            Object ref = this.postalCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.postalCode_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setPostalCode(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.postalCode_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearPostalCode() {
            this.postalCode_ = PostalAddress.getDefaultInstance().getPostalCode();
            this.onChanged();
            return this;
        }

        public Builder setPostalCodeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.postalCode_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getSortingCode() {
            Object ref = this.sortingCode_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.sortingCode_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSortingCodeBytes() {
            Object ref = this.sortingCode_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.sortingCode_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSortingCode(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sortingCode_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSortingCode() {
            this.sortingCode_ = PostalAddress.getDefaultInstance().getSortingCode();
            this.onChanged();
            return this;
        }

        public Builder setSortingCodeBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.sortingCode_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getAdministrativeArea() {
            Object ref = this.administrativeArea_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.administrativeArea_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getAdministrativeAreaBytes() {
            Object ref = this.administrativeArea_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.administrativeArea_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setAdministrativeArea(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.administrativeArea_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearAdministrativeArea() {
            this.administrativeArea_ = PostalAddress.getDefaultInstance().getAdministrativeArea();
            this.onChanged();
            return this;
        }

        public Builder setAdministrativeAreaBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.administrativeArea_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getLocality() {
            Object ref = this.locality_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.locality_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getLocalityBytes() {
            Object ref = this.locality_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.locality_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setLocality(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.locality_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearLocality() {
            this.locality_ = PostalAddress.getDefaultInstance().getLocality();
            this.onChanged();
            return this;
        }

        public Builder setLocalityBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.locality_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public String getSublocality() {
            Object ref = this.sublocality_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.sublocality_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getSublocalityBytes() {
            Object ref = this.sublocality_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.sublocality_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setSublocality(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.sublocality_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearSublocality() {
            this.sublocality_ = PostalAddress.getDefaultInstance().getSublocality();
            this.onChanged();
            return this;
        }

        public Builder setSublocalityBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.sublocality_ = value;
            this.onChanged();
            return this;
        }

        private void ensureAddressLinesIsMutable() {
            if ((this.bitField0_ & 0x100) != 256) {
                this.addressLines_ = new LazyStringArrayList(this.addressLines_);
                this.bitField0_ |= 0x100;
            }
        }

        public ProtocolStringList getAddressLinesList() {
            return this.addressLines_.getUnmodifiableView();
        }

        @Override
        public int getAddressLinesCount() {
            return this.addressLines_.size();
        }

        @Override
        public String getAddressLines(int index) {
            return (String)this.addressLines_.get(index);
        }

        @Override
        public ByteString getAddressLinesBytes(int index) {
            return this.addressLines_.getByteString(index);
        }

        public Builder setAddressLines(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureAddressLinesIsMutable();
            this.addressLines_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addAddressLines(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureAddressLinesIsMutable();
            this.addressLines_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllAddressLines(Iterable<String> values) {
            this.ensureAddressLinesIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.addressLines_);
            this.onChanged();
            return this;
        }

        public Builder clearAddressLines() {
            this.addressLines_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFEFF;
            this.onChanged();
            return this;
        }

        public Builder addAddressLinesBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.ensureAddressLinesIsMutable();
            this.addressLines_.add(value);
            this.onChanged();
            return this;
        }

        private void ensureRecipientsIsMutable() {
            if ((this.bitField0_ & 0x200) != 512) {
                this.recipients_ = new LazyStringArrayList(this.recipients_);
                this.bitField0_ |= 0x200;
            }
        }

        public ProtocolStringList getRecipientsList() {
            return this.recipients_.getUnmodifiableView();
        }

        @Override
        public int getRecipientsCount() {
            return this.recipients_.size();
        }

        @Override
        public String getRecipients(int index) {
            return (String)this.recipients_.get(index);
        }

        @Override
        public ByteString getRecipientsBytes(int index) {
            return this.recipients_.getByteString(index);
        }

        public Builder setRecipients(int index, String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRecipientsIsMutable();
            this.recipients_.set(index, value);
            this.onChanged();
            return this;
        }

        public Builder addRecipients(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.ensureRecipientsIsMutable();
            this.recipients_.add(value);
            this.onChanged();
            return this;
        }

        public Builder addAllRecipients(Iterable<String> values) {
            this.ensureRecipientsIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.recipients_);
            this.onChanged();
            return this;
        }

        public Builder clearRecipients() {
            this.recipients_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= 0xFFFFFDFF;
            this.onChanged();
            return this;
        }

        public Builder addRecipientsBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.ensureRecipientsIsMutable();
            this.recipients_.add(value);
            this.onChanged();
            return this;
        }

        @Override
        public String getOrganization() {
            Object ref = this.organization_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString)ref;
                String s2 = bs.toStringUtf8();
                this.organization_ = s2;
                return s2;
            }
            return (String)ref;
        }

        @Override
        public ByteString getOrganizationBytes() {
            Object ref = this.organization_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String)ref);
                this.organization_ = b;
                return b;
            }
            return (ByteString)ref;
        }

        public Builder setOrganization(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.organization_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearOrganization() {
            this.organization_ = PostalAddress.getDefaultInstance().getOrganization();
            this.onChanged();
            return this;
        }

        public Builder setOrganizationBytes(ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            PostalAddress.checkByteStringIsUtf8(value);
            this.organization_ = value;
            this.onChanged();
            return this;
        }

        @Override
        public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.setUnknownFieldsProto3(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder)super.mergeUnknownFields(unknownFields);
        }
    }
}

