/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class AbstractParser<MessageType extends MessageLite>
implements Parser<MessageType> {
    private static final ExtensionRegistryLite EMPTY_REGISTRY = ExtensionRegistryLite.getEmptyRegistry();

    private UninitializedMessageException newUninitializedMessageException(MessageType message) {
        if (message instanceof AbstractMessageLite) {
            return ((AbstractMessageLite)message).newUninitializedMessageException();
        }
        return new UninitializedMessageException((MessageLite)message);
    }

    private MessageType checkMessageInitialized(MessageType message) throws InvalidProtocolBufferException {
        if (message != null && !message.isInitialized()) {
            throw this.newUninitializedMessageException(message).asInvalidProtocolBufferException().setUnfinishedMessage((MessageLite)message);
        }
        return message;
    }

    @Override
    public MessageType parsePartialFrom(CodedInputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)((MessageLite)this.parsePartialFrom(input2, EMPTY_REGISTRY));
    }

    @Override
    public MessageType parseFrom(CodedInputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.checkMessageInitialized((MessageLite)this.parsePartialFrom(input2, extensionRegistry));
    }

    @Override
    public MessageType parseFrom(CodedInputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(input2, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parsePartialFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream input2 = data.newCodedInput();
        MessageLite message = (MessageLite)this.parsePartialFrom(input2, extensionRegistry);
        try {
            input2.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return (MessageType)message;
    }

    @Override
    public MessageType parsePartialFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialFrom(data, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.checkMessageInitialized(this.parsePartialFrom(data, extensionRegistry));
    }

    @Override
    public MessageType parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(data, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream input2 = CodedInputStream.newInstance(data);
        MessageLite message = (MessageLite)this.parsePartialFrom(input2, extensionRegistry);
        try {
            input2.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return (MessageType)this.checkMessageInitialized(message);
    }

    @Override
    public MessageType parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(data, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parsePartialFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream input2 = CodedInputStream.newInstance(data, off, len);
        MessageLite message = (MessageLite)this.parsePartialFrom(input2, extensionRegistry);
        try {
            input2.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return (MessageType)message;
    }

    @Override
    public MessageType parsePartialFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialFrom(data, off, len, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parsePartialFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialFrom(data, 0, data.length, extensionRegistry);
    }

    @Override
    public MessageType parsePartialFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialFrom(data, 0, data.length, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseFrom(byte[] data, int off, int len, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.checkMessageInitialized(this.parsePartialFrom(data, off, len, extensionRegistry));
    }

    @Override
    public MessageType parseFrom(byte[] data, int off, int len) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(data, off, len, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(data, 0, data.length, extensionRegistry);
    }

    @Override
    public MessageType parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(data, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parsePartialFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        CodedInputStream codedInput = CodedInputStream.newInstance(input2);
        MessageLite message = (MessageLite)this.parsePartialFrom(codedInput, extensionRegistry);
        try {
            codedInput.checkLastTagWas(0);
        }
        catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(message);
        }
        return (MessageType)message;
    }

    @Override
    public MessageType parsePartialFrom(InputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialFrom(input2, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.checkMessageInitialized(this.parsePartialFrom(input2, extensionRegistry));
    }

    @Override
    public MessageType parseFrom(InputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)this.parseFrom(input2, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parsePartialDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        int size2;
        try {
            int firstByte = input2.read();
            if (firstByte == -1) {
                return null;
            }
            size2 = CodedInputStream.readRawVarint32(firstByte, input2);
        }
        catch (IOException e) {
            throw new InvalidProtocolBufferException(e);
        }
        AbstractMessageLite.Builder.LimitedInputStream limitedInput = new AbstractMessageLite.Builder.LimitedInputStream(input2, size2);
        return (MessageType)this.parsePartialFrom(limitedInput, extensionRegistry);
    }

    @Override
    public MessageType parsePartialDelimitedFrom(InputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)this.parsePartialDelimitedFrom(input2, EMPTY_REGISTRY);
    }

    @Override
    public MessageType parseDelimitedFrom(InputStream input2, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (MessageType)this.checkMessageInitialized(this.parsePartialDelimitedFrom(input2, extensionRegistry));
    }

    @Override
    public MessageType parseDelimitedFrom(InputStream input2) throws InvalidProtocolBufferException {
        return (MessageType)this.parseDelimitedFrom(input2, EMPTY_REGISTRY);
    }
}

