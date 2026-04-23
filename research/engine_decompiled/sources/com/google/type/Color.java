/*
 * Decompiled with CFR 0.152.
 */
package com.google.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import com.google.type.ColorOrBuilder;
import com.google.type.ColorProto;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Color
extends GeneratedMessageV3
implements ColorOrBuilder {
    private static final long serialVersionUID = 0L;
    public static final int RED_FIELD_NUMBER = 1;
    private float red_;
    public static final int GREEN_FIELD_NUMBER = 2;
    private float green_;
    public static final int BLUE_FIELD_NUMBER = 3;
    private float blue_;
    public static final int ALPHA_FIELD_NUMBER = 4;
    private FloatValue alpha_;
    private byte memoizedIsInitialized = (byte)-1;
    private static final Color DEFAULT_INSTANCE = new Color();
    private static final Parser<Color> PARSER = new AbstractParser<Color>(){

        @Override
        public Color parsePartialFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Color(input2, extensionRegistry);
        }
    };

    private Color(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private Color() {
        this.red_ = 0.0f;
        this.green_ = 0.0f;
        this.blue_ = 0.0f;
    }

    @Override
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Color(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        boolean mutable_bitField0_ = false;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            block13: while (!done) {
                int tag = input2.readTag();
                switch (tag) {
                    case 0: {
                        done = true;
                        continue block13;
                    }
                    default: {
                        if (this.parseUnknownFieldProto3(input2, unknownFields, extensionRegistry, tag)) continue block13;
                        done = true;
                        continue block13;
                    }
                    case 13: {
                        this.red_ = input2.readFloat();
                        continue block13;
                    }
                    case 21: {
                        this.green_ = input2.readFloat();
                        continue block13;
                    }
                    case 29: {
                        this.blue_ = input2.readFloat();
                        continue block13;
                    }
                    case 34: 
                }
                FloatValue.Builder subBuilder = null;
                if (this.alpha_ != null) {
                    subBuilder = this.alpha_.toBuilder();
                }
                this.alpha_ = input2.readMessage(FloatValue.parser(), extensionRegistry);
                if (subBuilder == null) continue;
                subBuilder.mergeFrom(this.alpha_);
                this.alpha_ = subBuilder.buildPartial();
            }
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        }
        finally {
            this.unknownFields = unknownFields.build();
            this.makeExtensionsImmutable();
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ColorProto.internal_static_google_type_Color_descriptor;
    }

    @Override
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
    }

    @Override
    public float getRed() {
        return this.red_;
    }

    @Override
    public float getGreen() {
        return this.green_;
    }

    @Override
    public float getBlue() {
        return this.blue_;
    }

    @Override
    public boolean hasAlpha() {
        return this.alpha_ != null;
    }

    @Override
    public FloatValue getAlpha() {
        return this.alpha_ == null ? FloatValue.getDefaultInstance() : this.alpha_;
    }

    @Override
    public FloatValueOrBuilder getAlphaOrBuilder() {
        return this.getAlpha();
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
        if (this.red_ != 0.0f) {
            output.writeFloat(1, this.red_);
        }
        if (this.green_ != 0.0f) {
            output.writeFloat(2, this.green_);
        }
        if (this.blue_ != 0.0f) {
            output.writeFloat(3, this.blue_);
        }
        if (this.alpha_ != null) {
            output.writeMessage(4, this.getAlpha());
        }
        this.unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size2 = this.memoizedSize;
        if (size2 != -1) {
            return size2;
        }
        size2 = 0;
        if (this.red_ != 0.0f) {
            size2 += CodedOutputStream.computeFloatSize(1, this.red_);
        }
        if (this.green_ != 0.0f) {
            size2 += CodedOutputStream.computeFloatSize(2, this.green_);
        }
        if (this.blue_ != 0.0f) {
            size2 += CodedOutputStream.computeFloatSize(3, this.blue_);
        }
        if (this.alpha_ != null) {
            size2 += CodedOutputStream.computeMessageSize(4, this.getAlpha());
        }
        this.memoizedSize = size2 += this.unknownFields.getSerializedSize();
        return size2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return super.equals(obj);
        }
        Color other = (Color)obj;
        boolean result2 = true;
        result2 = result2 && Float.floatToIntBits(this.getRed()) == Float.floatToIntBits(other.getRed());
        result2 = result2 && Float.floatToIntBits(this.getGreen()) == Float.floatToIntBits(other.getGreen());
        result2 = result2 && Float.floatToIntBits(this.getBlue()) == Float.floatToIntBits(other.getBlue());
        boolean bl = result2 = result2 && this.hasAlpha() == other.hasAlpha();
        if (this.hasAlpha()) {
            result2 = result2 && this.getAlpha().equals(other.getAlpha());
        }
        result2 = result2 && this.unknownFields.equals(other.unknownFields);
        return result2;
    }

    @Override
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = 41;
        hash = 19 * hash + Color.getDescriptor().hashCode();
        hash = 37 * hash + 1;
        hash = 53 * hash + Float.floatToIntBits(this.getRed());
        hash = 37 * hash + 2;
        hash = 53 * hash + Float.floatToIntBits(this.getGreen());
        hash = 37 * hash + 3;
        hash = 53 * hash + Float.floatToIntBits(this.getBlue());
        if (this.hasAlpha()) {
            hash = 37 * hash + 4;
            hash = 53 * hash + this.getAlpha().hashCode();
        }
        this.memoizedHashCode = hash = 29 * hash + this.unknownFields.hashCode();
        return hash;
    }

    public static Color parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Color parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Color parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Color parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Color parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static Color parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static Color parseFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Color parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Color parseDelimitedFrom(InputStream input2) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2);
    }

    public static Color parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input2, extensionRegistry);
    }

    public static Color parseFrom(CodedInputStream input2) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2);
    }

    public static Color parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input2, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() {
        return Color.newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Color prototype) {
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

    public static Color getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Color> parser() {
        return PARSER;
    }

    public Parser<Color> getParserForType() {
        return PARSER;
    }

    @Override
    public Color getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public static final class Builder
    extends GeneratedMessageV3.Builder<Builder>
    implements ColorOrBuilder {
        private float red_;
        private float green_;
        private float blue_;
        private FloatValue alpha_ = null;
        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> alphaBuilder_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
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
            this.red_ = 0.0f;
            this.green_ = 0.0f;
            this.blue_ = 0.0f;
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        @Override
        public Descriptors.Descriptor getDescriptorForType() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override
        public Color getDefaultInstanceForType() {
            return Color.getDefaultInstance();
        }

        @Override
        public Color build() {
            Color result2 = this.buildPartial();
            if (!result2.isInitialized()) {
                throw Builder.newUninitializedMessageException(result2);
            }
            return result2;
        }

        @Override
        public Color buildPartial() {
            Color result2 = new Color(this);
            result2.red_ = this.red_;
            result2.green_ = this.green_;
            result2.blue_ = this.blue_;
            if (this.alphaBuilder_ == null) {
                result2.alpha_ = this.alpha_;
            } else {
                result2.alpha_ = this.alphaBuilder_.build();
            }
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
            if (other instanceof Color) {
                return this.mergeFrom((Color)other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Color other) {
            if (other == Color.getDefaultInstance()) {
                return this;
            }
            if (other.getRed() != 0.0f) {
                this.setRed(other.getRed());
            }
            if (other.getGreen() != 0.0f) {
                this.setGreen(other.getGreen());
            }
            if (other.getBlue() != 0.0f) {
                this.setBlue(other.getBlue());
            }
            if (other.hasAlpha()) {
                this.mergeAlpha(other.getAlpha());
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
            Color parsedMessage = null;
            try {
                parsedMessage = (Color)PARSER.parsePartialFrom(input2, extensionRegistry);
            }
            catch (InvalidProtocolBufferException e) {
                parsedMessage = (Color)e.getUnfinishedMessage();
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
        public float getRed() {
            return this.red_;
        }

        public Builder setRed(float value) {
            this.red_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearRed() {
            this.red_ = 0.0f;
            this.onChanged();
            return this;
        }

        @Override
        public float getGreen() {
            return this.green_;
        }

        public Builder setGreen(float value) {
            this.green_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearGreen() {
            this.green_ = 0.0f;
            this.onChanged();
            return this;
        }

        @Override
        public float getBlue() {
            return this.blue_;
        }

        public Builder setBlue(float value) {
            this.blue_ = value;
            this.onChanged();
            return this;
        }

        public Builder clearBlue() {
            this.blue_ = 0.0f;
            this.onChanged();
            return this;
        }

        @Override
        public boolean hasAlpha() {
            return this.alphaBuilder_ != null || this.alpha_ != null;
        }

        @Override
        public FloatValue getAlpha() {
            if (this.alphaBuilder_ == null) {
                return this.alpha_ == null ? FloatValue.getDefaultInstance() : this.alpha_;
            }
            return this.alphaBuilder_.getMessage();
        }

        public Builder setAlpha(FloatValue value) {
            if (this.alphaBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.alpha_ = value;
                this.onChanged();
            } else {
                this.alphaBuilder_.setMessage(value);
            }
            return this;
        }

        public Builder setAlpha(FloatValue.Builder builderForValue) {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = builderForValue.build();
                this.onChanged();
            } else {
                this.alphaBuilder_.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeAlpha(FloatValue value) {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = this.alpha_ != null ? FloatValue.newBuilder(this.alpha_).mergeFrom(value).buildPartial() : value;
                this.onChanged();
            } else {
                this.alphaBuilder_.mergeFrom(value);
            }
            return this;
        }

        public Builder clearAlpha() {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
                this.onChanged();
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        public FloatValue.Builder getAlphaBuilder() {
            this.onChanged();
            return this.getAlphaFieldBuilder().getBuilder();
        }

        @Override
        public FloatValueOrBuilder getAlphaOrBuilder() {
            if (this.alphaBuilder_ != null) {
                return this.alphaBuilder_.getMessageOrBuilder();
            }
            return this.alpha_ == null ? FloatValue.getDefaultInstance() : this.alpha_;
        }

        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> getAlphaFieldBuilder() {
            if (this.alphaBuilder_ == null) {
                this.alphaBuilder_ = new SingleFieldBuilderV3(this.getAlpha(), this.getParentForChildren(), this.isClean());
                this.alpha_ = null;
            }
            return this.alphaBuilder_;
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

