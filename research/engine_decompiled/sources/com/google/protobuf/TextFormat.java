/*
 * Decompiled with CFR 0.152.
 */
package com.google.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatEscaper;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.TextFormatParseLocation;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    private TextFormat() {
    }

    public static void print(MessageOrBuilder message, Appendable output) throws IOException {
        Printer.DEFAULT.print(message, TextFormat.multiLineOutput(output));
    }

    public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
        Printer.DEFAULT.printUnknownFields(fields, TextFormat.multiLineOutput(output));
    }

    public static void printUnicode(MessageOrBuilder message, Appendable output) throws IOException {
        Printer.UNICODE.print(message, TextFormat.multiLineOutput(output));
    }

    public static void printUnicode(UnknownFieldSet fields, Appendable output) throws IOException {
        Printer.UNICODE.printUnknownFields(fields, TextFormat.multiLineOutput(output));
    }

    public static String shortDebugString(MessageOrBuilder message) {
        try {
            StringBuilder text = new StringBuilder();
            Printer.DEFAULT.print(message, TextFormat.singleLineOutput(text));
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(Descriptors.FieldDescriptor field2, Object value) {
        try {
            StringBuilder text = new StringBuilder();
            Printer.DEFAULT.printField(field2, value, TextFormat.singleLineOutput(text));
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet fields) {
        try {
            StringBuilder text = new StringBuilder();
            Printer.DEFAULT.printUnknownFields(fields, TextFormat.singleLineOutput(text));
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(MessageOrBuilder message) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.print(message, (Appendable)text);
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet fields) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.print(fields, (Appendable)text);
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder message) {
        try {
            StringBuilder text = new StringBuilder();
            Printer.UNICODE.print(message, TextFormat.multiLineOutput(text));
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet fields) {
        try {
            StringBuilder text = new StringBuilder();
            Printer.UNICODE.printUnknownFields(fields, TextFormat.multiLineOutput(text));
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printField(Descriptors.FieldDescriptor field2, Object value, Appendable output) throws IOException {
        Printer.DEFAULT.printField(field2, value, TextFormat.multiLineOutput(output));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor field2, Object value) {
        try {
            StringBuilder text = new StringBuilder();
            TextFormat.printField(field2, value, text);
            return text.toString();
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printUnicodeFieldValue(Descriptors.FieldDescriptor field2, Object value, Appendable output) throws IOException {
        Printer.UNICODE.printFieldValue(field2, value, TextFormat.multiLineOutput(output));
    }

    public static void printFieldValue(Descriptors.FieldDescriptor field2, Object value, Appendable output) throws IOException {
        Printer.DEFAULT.printFieldValue(field2, value, TextFormat.multiLineOutput(output));
    }

    public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
        TextFormat.printUnknownFieldValue(tag, value, TextFormat.multiLineOutput(output));
    }

    private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
        switch (WireFormat.getTagWireType(tag)) {
            case 0: {
                generator.print(TextFormat.unsignedToString((Long)value));
                break;
            }
            case 5: {
                generator.print(String.format((Locale)null, "0x%08x", (Integer)value));
                break;
            }
            case 1: {
                generator.print(String.format((Locale)null, "0x%016x", (Long)value));
                break;
            }
            case 2: {
                try {
                    UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString)value);
                    generator.print("{");
                    generator.eol();
                    generator.indent();
                    Printer.DEFAULT.printUnknownFields(message, generator);
                    generator.outdent();
                    generator.print("}");
                }
                catch (InvalidProtocolBufferException e) {
                    generator.print("\"");
                    generator.print(TextFormat.escapeBytes((ByteString)value));
                    generator.print("\"");
                }
                break;
            }
            case 3: {
                Printer.DEFAULT.printUnknownFields((UnknownFieldSet)value, generator);
                break;
            }
            default: {
                throw new IllegalArgumentException("Bad tag: " + tag);
            }
        }
    }

    public static String unsignedToString(int value) {
        if (value >= 0) {
            return Integer.toString(value);
        }
        return Long.toString((long)value & 0xFFFFFFFFL);
    }

    public static String unsignedToString(long value) {
        if (value >= 0L) {
            return Long.toString(value);
        }
        return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
    }

    private static TextGenerator multiLineOutput(Appendable output) {
        return new TextGenerator(output, false);
    }

    private static TextGenerator singleLineOutput(Appendable output) {
        return new TextGenerator(output, true);
    }

    public static Parser getParser() {
        return PARSER;
    }

    public static void merge(Readable input2, Message.Builder builder) throws IOException {
        PARSER.merge(input2, builder);
    }

    public static void merge(CharSequence input2, Message.Builder builder) throws ParseException {
        PARSER.merge(input2, builder);
    }

    public static <T extends Message> T parse(CharSequence input2, Class<T> protoClass) throws ParseException {
        Message.Builder builder = ((Message)Internal.getDefaultInstance(protoClass)).newBuilderForType();
        TextFormat.merge(input2, builder);
        Message output = builder.build();
        return (T)output;
    }

    public static void merge(Readable input2, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(input2, extensionRegistry, builder);
    }

    public static void merge(CharSequence input2, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(input2, extensionRegistry, builder);
    }

    public static <T extends Message> T parse(CharSequence input2, ExtensionRegistry extensionRegistry, Class<T> protoClass) throws ParseException {
        Message.Builder builder = ((Message)Internal.getDefaultInstance(protoClass)).newBuilderForType();
        TextFormat.merge(input2, extensionRegistry, builder);
        Message output = builder.build();
        return (T)output;
    }

    public static String escapeBytes(ByteString input2) {
        return TextFormatEscaper.escapeBytes(input2);
    }

    public static String escapeBytes(byte[] input2) {
        return TextFormatEscaper.escapeBytes(input2);
    }

    public static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
        ByteString input2 = ByteString.copyFromUtf8(charString.toString());
        byte[] result2 = new byte[input2.size()];
        int pos = 0;
        block13: for (int i = 0; i < input2.size(); ++i) {
            byte c = input2.byteAt(i);
            if (c == 92) {
                if (i + 1 < input2.size()) {
                    int code;
                    if (TextFormat.isOctal(c = input2.byteAt(++i))) {
                        code = TextFormat.digitValue(c);
                        if (i + 1 < input2.size() && TextFormat.isOctal(input2.byteAt(i + 1))) {
                            code = code * 8 + TextFormat.digitValue(input2.byteAt(++i));
                        }
                        if (i + 1 < input2.size() && TextFormat.isOctal(input2.byteAt(i + 1))) {
                            code = code * 8 + TextFormat.digitValue(input2.byteAt(++i));
                        }
                        result2[pos++] = (byte)code;
                        continue;
                    }
                    switch (c) {
                        case 97: {
                            result2[pos++] = 7;
                            continue block13;
                        }
                        case 98: {
                            result2[pos++] = 8;
                            continue block13;
                        }
                        case 102: {
                            result2[pos++] = 12;
                            continue block13;
                        }
                        case 110: {
                            result2[pos++] = 10;
                            continue block13;
                        }
                        case 114: {
                            result2[pos++] = 13;
                            continue block13;
                        }
                        case 116: {
                            result2[pos++] = 9;
                            continue block13;
                        }
                        case 118: {
                            result2[pos++] = 11;
                            continue block13;
                        }
                        case 92: {
                            result2[pos++] = 92;
                            continue block13;
                        }
                        case 39: {
                            result2[pos++] = 39;
                            continue block13;
                        }
                        case 34: {
                            result2[pos++] = 34;
                            continue block13;
                        }
                        case 120: {
                            code = 0;
                            if (i + 1 >= input2.size() || !TextFormat.isHex(input2.byteAt(i + 1))) {
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                            }
                            code = TextFormat.digitValue(input2.byteAt(++i));
                            if (i + 1 < input2.size() && TextFormat.isHex(input2.byteAt(i + 1))) {
                                code = code * 16 + TextFormat.digitValue(input2.byteAt(++i));
                            }
                            result2[pos++] = (byte)code;
                            continue block13;
                        }
                        default: {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + (char)c + '\'');
                        }
                    }
                }
                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
            }
            result2[pos++] = c;
        }
        return result2.length == pos ? ByteString.wrap(result2) : ByteString.copyFrom(result2, 0, pos);
    }

    static String escapeText(String input2) {
        return TextFormat.escapeBytes(ByteString.copyFromUtf8(input2));
    }

    public static String escapeDoubleQuotesAndBackslashes(String input2) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input2);
    }

    static String unescapeText(String input2) throws InvalidEscapeSequenceException {
        return TextFormat.unescapeBytes(input2).toStringUtf8();
    }

    private static boolean isOctal(byte c) {
        return 48 <= c && c <= 55;
    }

    private static boolean isHex(byte c) {
        return 48 <= c && c <= 57 || 97 <= c && c <= 102 || 65 <= c && c <= 70;
    }

    private static int digitValue(byte c) {
        if (48 <= c && c <= 57) {
            return c - 48;
        }
        if (97 <= c && c <= 122) {
            return c - 97 + 10;
        }
        return c - 65 + 10;
    }

    static int parseInt32(String text) throws NumberFormatException {
        return (int)TextFormat.parseInteger(text, true, false);
    }

    static int parseUInt32(String text) throws NumberFormatException {
        return (int)TextFormat.parseInteger(text, false, false);
    }

    static long parseInt64(String text) throws NumberFormatException {
        return TextFormat.parseInteger(text, true, true);
    }

    static long parseUInt64(String text) throws NumberFormatException {
        return TextFormat.parseInteger(text, false, true);
    }

    private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
        int pos = 0;
        boolean negative = false;
        if (text.startsWith("-", pos)) {
            if (!isSigned) {
                throw new NumberFormatException("Number must be positive: " + text);
            }
            ++pos;
            negative = true;
        }
        int radix = 10;
        if (text.startsWith("0x", pos)) {
            pos += 2;
            radix = 16;
        } else if (text.startsWith("0", pos)) {
            radix = 8;
        }
        String numberText = text.substring(pos);
        long result2 = 0L;
        if (numberText.length() < 16) {
            result2 = Long.parseLong(numberText, radix);
            if (negative) {
                result2 = -result2;
            }
            if (!isLong) {
                if (isSigned) {
                    if (result2 > Integer.MAX_VALUE || result2 < Integer.MIN_VALUE) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
                    }
                } else if (result2 >= 0x100000000L || result2 < 0L) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
                }
            }
        } else {
            BigInteger bigValue = new BigInteger(numberText, radix);
            if (negative) {
                bigValue = bigValue.negate();
            }
            if (!isLong) {
                if (isSigned) {
                    if (bigValue.bitLength() > 31) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
                    }
                } else if (bigValue.bitLength() > 32) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
                }
            } else if (isSigned) {
                if (bigValue.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text);
                }
            } else if (bigValue.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
            }
            result2 = bigValue.longValue();
        }
        return result2;
    }

    public static class InvalidEscapeSequenceException
    extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String description) {
            super(description);
        }
    }

    public static class Parser {
        private final boolean allowUnknownFields;
        private final boolean allowUnknownEnumValues;
        private final SingularOverwritePolicy singularOverwritePolicy;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private static final int BUFFER_SIZE = 4096;

        private Parser(boolean allowUnknownFields, boolean allowUnknownEnumValues, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
            this.allowUnknownFields = allowUnknownFields;
            this.allowUnknownEnumValues = allowUnknownEnumValues;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = parseInfoTreeBuilder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public void merge(Readable input2, Message.Builder builder) throws IOException {
            this.merge(input2, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(CharSequence input2, Message.Builder builder) throws ParseException {
            this.merge(input2, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable input2, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            this.merge(Parser.toStringBuilder(input2), extensionRegistry, builder);
        }

        private static StringBuilder toStringBuilder(Readable input2) throws IOException {
            int n;
            StringBuilder text = new StringBuilder();
            CharBuffer buffer = CharBuffer.allocate(4096);
            while ((n = input2.read(buffer)) != -1) {
                buffer.flip();
                text.append(buffer, 0, n);
            }
            return text;
        }

        private void checkUnknownFields(List<String> unknownFields) throws ParseException {
            if (unknownFields.isEmpty()) {
                return;
            }
            StringBuilder msg = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (String field2 : unknownFields) {
                msg.append('\n').append(field2);
            }
            if (!this.allowUnknownFields) {
                String[] lineColumn = unknownFields.get(0).split(":");
                throw new ParseException(Integer.valueOf(lineColumn[0]), Integer.valueOf(lineColumn[1]), msg.toString());
            }
            logger.warning(msg.toString());
        }

        public void merge(CharSequence input2, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(input2);
            MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
            ArrayList<String> unknownFields = new ArrayList<String>();
            while (!tokenizer.atEnd()) {
                this.mergeField(tokenizer, extensionRegistry, target, unknownFields);
            }
            this.checkUnknownFields(unknownFields);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, List<String> unknownFields) throws ParseException {
            this.mergeField(tokenizer, extensionRegistry, target, this.parseInfoTreeBuilder, unknownFields);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<String> unknownFields) throws ParseException {
            CharSequence name;
            Descriptors.FieldDescriptor field2 = null;
            int startLine = tokenizer.getLine();
            int startColumn = tokenizer.getColumn();
            Descriptors.Descriptor type = target.getDescriptorForType();
            ExtensionRegistry.ExtensionInfo extension2 = null;
            if (tokenizer.tryConsume("[")) {
                name = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    ((StringBuilder)name).append('.');
                    ((StringBuilder)name).append(tokenizer.consumeIdentifier());
                }
                extension2 = target.findExtensionByName(extensionRegistry, ((StringBuilder)name).toString());
                if (extension2 == null) {
                    unknownFields.add(tokenizer.getPreviousLine() + 1 + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + ".[" + name + "]");
                } else {
                    if (extension2.descriptor.getContainingType() != type) {
                        throw tokenizer.parseExceptionPreviousToken("Extension \"" + name + "\" does not extend message type \"" + type.getFullName() + "\".");
                    }
                    field2 = extension2.descriptor;
                }
                tokenizer.consume("]");
            } else {
                String lowerName;
                name = tokenizer.consumeIdentifier();
                field2 = type.findFieldByName((String)name);
                if (field2 == null && (field2 = type.findFieldByName(lowerName = ((String)name).toLowerCase(Locale.US))) != null && field2.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                    field2 = null;
                }
                if (field2 != null && field2.getType() == Descriptors.FieldDescriptor.Type.GROUP && !field2.getMessageType().getName().equals(name)) {
                    field2 = null;
                }
                if (field2 == null) {
                    unknownFields.add(tokenizer.getPreviousLine() + 1 + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + "." + (String)name);
                }
            }
            if (field2 == null) {
                if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
                    this.skipFieldValue(tokenizer);
                } else {
                    this.skipFieldMessage(tokenizer);
                }
                return;
            }
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                tokenizer.tryConsume(":");
                if (parseTreeBuilder != null) {
                    TextFormatParseInfoTree.Builder childParseTreeBuilder = parseTreeBuilder.getBuilderForSubMessageField(field2);
                    this.consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension2, childParseTreeBuilder, unknownFields);
                } else {
                    this.consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension2, parseTreeBuilder, unknownFields);
                }
            } else {
                tokenizer.consume(":");
                this.consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension2, parseTreeBuilder, unknownFields);
            }
            if (parseTreeBuilder != null) {
                parseTreeBuilder.setLocation(field2, TextFormatParseLocation.create(startLine, startColumn));
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field2, ExtensionRegistry.ExtensionInfo extension2, TextFormatParseInfoTree.Builder parseTreeBuilder, List<String> unknownFields) throws ParseException {
            if (field2.isRepeated() && tokenizer.tryConsume("[")) {
                if (!tokenizer.tryConsume("]")) {
                    while (true) {
                        this.consumeFieldValue(tokenizer, extensionRegistry, target, field2, extension2, parseTreeBuilder, unknownFields);
                        if (!tokenizer.tryConsume("]")) {
                            tokenizer.consume(",");
                            continue;
                        }
                        break;
                    }
                }
            } else {
                this.consumeFieldValue(tokenizer, extensionRegistry, target, field2, extension2, parseTreeBuilder, unknownFields);
            }
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field2, ExtensionRegistry.ExtensionInfo extension2, TextFormatParseInfoTree.Builder parseTreeBuilder, List<String> unknownFields) throws ParseException {
            Object value = null;
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                String endToken;
                if (tokenizer.tryConsume("<")) {
                    endToken = ">";
                } else {
                    tokenizer.consume("{");
                    endToken = "}";
                }
                MessageReflection.MergeTarget subField = target.newMergeTargetForField(field2, extension2 == null ? null : extension2.defaultInstance);
                while (!tokenizer.tryConsume(endToken)) {
                    if (tokenizer.atEnd()) {
                        throw tokenizer.parseException("Expected \"" + endToken + "\".");
                    }
                    this.mergeField(tokenizer, extensionRegistry, subField, parseTreeBuilder, unknownFields);
                }
                value = subField.finish();
            } else {
                switch (field2.getType()) {
                    case INT32: 
                    case SINT32: 
                    case SFIXED32: {
                        value = tokenizer.consumeInt32();
                        break;
                    }
                    case INT64: 
                    case SINT64: 
                    case SFIXED64: {
                        value = tokenizer.consumeInt64();
                        break;
                    }
                    case UINT32: 
                    case FIXED32: {
                        value = tokenizer.consumeUInt32();
                        break;
                    }
                    case UINT64: 
                    case FIXED64: {
                        value = tokenizer.consumeUInt64();
                        break;
                    }
                    case FLOAT: {
                        value = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    }
                    case DOUBLE: {
                        value = tokenizer.consumeDouble();
                        break;
                    }
                    case BOOL: {
                        value = tokenizer.consumeBoolean();
                        break;
                    }
                    case STRING: {
                        value = tokenizer.consumeString();
                        break;
                    }
                    case BYTES: {
                        value = tokenizer.consumeByteString();
                        break;
                    }
                    case ENUM: {
                        Descriptors.EnumDescriptor enumType = field2.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int number = tokenizer.consumeInt32();
                            value = enumType.findValueByNumber(number);
                            if (value != null) break;
                            String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.';
                            if (this.allowUnknownEnumValues) {
                                logger.warning(unknownValueMsg);
                                return;
                            }
                            throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.');
                        }
                        String id = tokenizer.consumeIdentifier();
                        value = enumType.findValueByName(id);
                        if (value != null) break;
                        String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\".";
                        if (this.allowUnknownEnumValues) {
                            logger.warning(unknownValueMsg);
                            return;
                        }
                        throw tokenizer.parseExceptionPreviousToken(unknownValueMsg);
                    }
                    case MESSAGE: 
                    case GROUP: {
                        throw new RuntimeException("Can't get here.");
                    }
                }
            }
            if (field2.isRepeated()) {
                target.addRepeatedField(field2, value);
            } else {
                if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && target.hasField(field2)) {
                    throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + field2.getFullName() + "\" cannot be overwritten.");
                }
                if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && field2.getContainingOneof() != null && target.hasOneof(field2.getContainingOneof())) {
                    Descriptors.OneofDescriptor oneof = field2.getContainingOneof();
                    throw tokenizer.parseExceptionPreviousToken("Field \"" + field2.getFullName() + "\" is specified along with field \"" + target.getOneofFieldDescriptor(oneof).getFullName() + "\", another member of oneof \"" + oneof.getName() + "\".");
                }
                target.setField(field2, value);
            }
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("<") && !tokenizer.lookingAt("{")) {
                this.skipFieldValue(tokenizer);
            } else {
                this.skipFieldMessage(tokenizer);
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String delimiter;
            if (tokenizer.tryConsume("<")) {
                delimiter = ">";
            } else {
                tokenizer.consume("{");
                delimiter = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                this.skipField(tokenizer);
            }
            tokenizer.consume(delimiter);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                while (tokenizer.tryConsumeString()) {
                }
                return;
            }
            if (!(tokenizer.tryConsumeIdentifier() || tokenizer.tryConsumeInt64() || tokenizer.tryConsumeUInt64() || tokenizer.tryConsumeDouble() || tokenizer.tryConsumeFloat())) {
                throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
            }
        }

        public static class Builder {
            private boolean allowUnknownFields = false;
            private boolean allowUnknownEnumValues = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
                this.singularOverwritePolicy = p;
                return this;
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
                this.parseInfoTreeBuilder = parseInfoTreeBuilder;
                return this;
            }

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.allowUnknownEnumValues, this.singularOverwritePolicy, this.parseInfoTreeBuilder);
            }
        }

        public static enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES;

        }
    }

    public static class UnknownFieldParseException
    extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(String message) {
            this(-1, -1, "", message);
        }

        public UnknownFieldParseException(int line, int column, String unknownField, String message) {
            super(line, column, message);
            this.unknownField = unknownField;
        }

        public String getUnknownField() {
            return this.unknownField;
        }
    }

    public static class ParseException
    extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int line;
        private final int column;

        public ParseException(String message) {
            this(-1, -1, message);
        }

        public ParseException(int line, int column, String message) {
            super(Integer.toString(line) + ":" + column + ": " + message);
            this.line = line;
            this.column = column;
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    private static final class Tokenizer {
        private final CharSequence text;
        private final Matcher matcher;
        private String currentToken;
        private int pos = 0;
        private int line = 0;
        private int column = 0;
        private int previousLine = 0;
        private int previousColumn = 0;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        private Tokenizer(CharSequence text) {
            this.text = text;
            this.matcher = WHITESPACE.matcher(text);
            this.skipWhitespace();
            this.nextToken();
        }

        int getPreviousLine() {
            return this.previousLine;
        }

        int getPreviousColumn() {
            return this.previousColumn;
        }

        int getLine() {
            return this.line;
        }

        int getColumn() {
            return this.column;
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    ++this.line;
                    this.column = 0;
                } else {
                    ++this.column;
                }
                ++this.pos;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
            } else {
                this.matcher.usePattern(TOKEN);
                if (this.matcher.lookingAt()) {
                    this.currentToken = this.matcher.group();
                    this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
                } else {
                    this.currentToken = String.valueOf(this.text.charAt(this.pos));
                    this.matcher.region(this.pos + 1, this.matcher.regionEnd());
                }
                this.skipWhitespace();
            }
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean tryConsume(String token) {
            if (this.currentToken.equals(token)) {
                this.nextToken();
                return true;
            }
            return false;
        }

        public void consume(String token) throws ParseException {
            if (!this.tryConsume(token)) {
                throw this.parseException("Expected \"" + token + "\".");
            }
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char c = this.currentToken.charAt(0);
            return '0' <= c && c <= '9' || c == '-' || c == '+';
        }

        public boolean lookingAt(String text) {
            return this.currentToken.equals(text);
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); ++i) {
                char c = this.currentToken.charAt(i);
                if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || '0' <= c && c <= '9' || c == '_' || c == '.') continue;
                throw this.parseException("Expected identifier. Found '" + this.currentToken + "'");
            }
            String result2 = this.currentToken;
            this.nextToken();
            return result2;
        }

        public boolean tryConsumeIdentifier() {
            try {
                this.consumeIdentifier();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public int consumeInt32() throws ParseException {
            try {
                int result2 = TextFormat.parseInt32(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public int consumeUInt32() throws ParseException {
            try {
                int result2 = TextFormat.parseUInt32(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long result2 = TextFormat.parseInt64(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public boolean tryConsumeInt64() {
            try {
                this.consumeInt64();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long result2 = TextFormat.parseUInt64(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.integerParseException(e);
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                this.consumeUInt64();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                this.nextToken();
                return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (this.currentToken.equalsIgnoreCase("nan")) {
                this.nextToken();
                return Double.NaN;
            }
            try {
                double result2 = Double.parseDouble(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.floatParseException(e);
            }
        }

        public boolean tryConsumeDouble() {
            try {
                this.consumeDouble();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                this.nextToken();
                return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
            if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                this.nextToken();
                return Float.NaN;
            }
            try {
                float result2 = Float.parseFloat(this.currentToken);
                this.nextToken();
                return result2;
            }
            catch (NumberFormatException e) {
                throw this.floatParseException(e);
            }
        }

        public boolean tryConsumeFloat() {
            try {
                this.consumeFloat();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("True") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                this.nextToken();
                return true;
            }
            if (this.currentToken.equals("false") || this.currentToken.equals("False") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
                this.nextToken();
                return false;
            }
            throw this.parseException("Expected \"true\" or \"false\". Found \"" + this.currentToken + "\".");
        }

        public String consumeString() throws ParseException {
            return this.consumeByteString().toStringUtf8();
        }

        public boolean tryConsumeString() {
            try {
                this.consumeString();
                return true;
            }
            catch (ParseException e) {
                return false;
            }
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList<ByteString> list2 = new ArrayList<ByteString>();
            this.consumeByteString(list2);
            while (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
                this.consumeByteString(list2);
            }
            return ByteString.copyFrom(list2);
        }

        private void consumeByteString(List<ByteString> list2) throws ParseException {
            char quote;
            char c = quote = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char)'\u0000';
            if (quote != '\"' && quote != '\'') {
                throw this.parseException("Expected string.");
            }
            if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != quote) {
                throw this.parseException("String missing ending quote.");
            }
            try {
                String escaped2 = this.currentToken.substring(1, this.currentToken.length() - 1);
                ByteString result2 = TextFormat.unescapeBytes(escaped2);
                this.nextToken();
                list2.add(result2);
            }
            catch (InvalidEscapeSequenceException e) {
                throw this.parseException(e.getMessage());
            }
        }

        public ParseException parseException(String description) {
            return new ParseException(this.line + 1, this.column + 1, description);
        }

        public ParseException parseExceptionPreviousToken(String description) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
        }

        private ParseException integerParseException(NumberFormatException e) {
            return this.parseException("Couldn't parse integer: " + e.getMessage());
        }

        private ParseException floatParseException(NumberFormatException e) {
            return this.parseException("Couldn't parse number: " + e.getMessage());
        }

        public UnknownFieldParseException unknownFieldParseExceptionPreviousToken(String unknownField, String description) {
            return new UnknownFieldParseException(this.previousLine + 1, this.previousColumn + 1, unknownField, description);
        }
    }

    private static final class TextGenerator {
        private final Appendable output;
        private final StringBuilder indent = new StringBuilder();
        private final boolean singleLineMode;
        private boolean atStartOfLine = false;

        private TextGenerator(Appendable output, boolean singleLineMode) {
            this.output = output;
            this.singleLineMode = singleLineMode;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.setLength(length - 2);
        }

        public void print(CharSequence text) throws IOException {
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.singleLineMode ? " " : this.indent);
            }
            this.output.append(text);
        }

        public void eol() throws IOException {
            if (!this.singleLineMode) {
                this.output.append("\n");
            }
            this.atStartOfLine = true;
        }
    }

    private static final class Printer {
        static final Printer DEFAULT = new Printer(true);
        static final Printer UNICODE = new Printer(false);
        private final boolean escapeNonAscii;

        private Printer(boolean escapeNonAscii) {
            this.escapeNonAscii = escapeNonAscii;
        }

        private void print(MessageOrBuilder message, TextGenerator generator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> field2 : message.getAllFields().entrySet()) {
                this.printField(field2.getKey(), field2.getValue(), generator);
            }
            this.printUnknownFields(message.getUnknownFields(), generator);
        }

        private void printField(Descriptors.FieldDescriptor field2, Object value, TextGenerator generator) throws IOException {
            if (field2.isRepeated()) {
                for (Object element : (List)value) {
                    this.printSingleField(field2, element, generator);
                }
            } else {
                this.printSingleField(field2, value, generator);
            }
        }

        private void printSingleField(Descriptors.FieldDescriptor field2, Object value, TextGenerator generator) throws IOException {
            if (field2.isExtension()) {
                generator.print("[");
                if (field2.getContainingType().getOptions().getMessageSetWireFormat() && field2.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field2.isOptional() && field2.getExtensionScope() == field2.getMessageType()) {
                    generator.print(field2.getMessageType().getFullName());
                } else {
                    generator.print(field2.getFullName());
                }
                generator.print("]");
            } else if (field2.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                generator.print(field2.getMessageType().getName());
            } else {
                generator.print(field2.getName());
            }
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                generator.print(" {");
                generator.eol();
                generator.indent();
            } else {
                generator.print(": ");
            }
            this.printFieldValue(field2, value, generator);
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                generator.outdent();
                generator.print("}");
            }
            generator.eol();
        }

        private void printFieldValue(Descriptors.FieldDescriptor field2, Object value, TextGenerator generator) throws IOException {
            switch (field2.getType()) {
                case INT32: 
                case SINT32: 
                case SFIXED32: {
                    generator.print(((Integer)value).toString());
                    break;
                }
                case INT64: 
                case SINT64: 
                case SFIXED64: {
                    generator.print(((Long)value).toString());
                    break;
                }
                case BOOL: {
                    generator.print(((Boolean)value).toString());
                    break;
                }
                case FLOAT: {
                    generator.print(((Float)value).toString());
                    break;
                }
                case DOUBLE: {
                    generator.print(((Double)value).toString());
                    break;
                }
                case UINT32: 
                case FIXED32: {
                    generator.print(TextFormat.unsignedToString((Integer)value));
                    break;
                }
                case UINT64: 
                case FIXED64: {
                    generator.print(TextFormat.unsignedToString((Long)value));
                    break;
                }
                case STRING: {
                    generator.print("\"");
                    generator.print(this.escapeNonAscii ? TextFormatEscaper.escapeText((String)value) : TextFormat.escapeDoubleQuotesAndBackslashes((String)value).replace("\n", "\\n"));
                    generator.print("\"");
                    break;
                }
                case BYTES: {
                    generator.print("\"");
                    if (value instanceof ByteString) {
                        generator.print(TextFormat.escapeBytes((ByteString)value));
                    } else {
                        generator.print(TextFormat.escapeBytes((byte[])value));
                    }
                    generator.print("\"");
                    break;
                }
                case ENUM: {
                    generator.print(((Descriptors.EnumValueDescriptor)value).getName());
                    break;
                }
                case MESSAGE: 
                case GROUP: {
                    this.print((Message)value, generator);
                }
            }
        }

        private void printUnknownFields(UnknownFieldSet unknownFields, TextGenerator generator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
                int number = entry.getKey();
                UnknownFieldSet.Field field2 = entry.getValue();
                this.printUnknownField(number, 0, field2.getVarintList(), generator);
                this.printUnknownField(number, 5, field2.getFixed32List(), generator);
                this.printUnknownField(number, 1, field2.getFixed64List(), generator);
                this.printUnknownField(number, 2, field2.getLengthDelimitedList(), generator);
                for (UnknownFieldSet value : field2.getGroupList()) {
                    generator.print(entry.getKey().toString());
                    generator.print(" {");
                    generator.eol();
                    generator.indent();
                    this.printUnknownFields(value, generator);
                    generator.outdent();
                    generator.print("}");
                    generator.eol();
                }
            }
        }

        private void printUnknownField(int number, int wireType, List<?> values, TextGenerator generator) throws IOException {
            for (Object value : values) {
                generator.print(String.valueOf(number));
                generator.print(": ");
                TextFormat.printUnknownFieldValue(wireType, value, generator);
                generator.eol();
            }
        }
    }
}

