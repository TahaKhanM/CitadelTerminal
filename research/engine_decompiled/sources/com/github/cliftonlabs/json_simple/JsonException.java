/*
 * Decompiled with CFR 0.152.
 */
package com.github.cliftonlabs.json_simple;

public class JsonException
extends Exception {
    private static final long serialVersionUID = 1L;
    private final int position;
    private final Problems problemType;
    private final Object unexpectedObject;

    public JsonException(int position, Problems problemType, Object unexpectedObject) {
        this.position = position;
        this.problemType = problemType;
        this.unexpectedObject = unexpectedObject;
        if ((Problems.IOEXCEPTION.equals((Object)problemType) || Problems.UNEXPECTED_EXCEPTION.equals((Object)problemType)) && unexpectedObject instanceof Throwable) {
            this.initCause((Throwable)unexpectedObject);
        }
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        switch (this.problemType) {
            case DISALLOWED_TOKEN: {
                sb.append("The disallowed token (").append(this.unexpectedObject).append(") was found at position ").append(this.position).append(". If this is in error, try again with a deserialization method in Jsoner that allows the token instead. Otherwise, fix the parsable string and try again.");
                break;
            }
            case IOEXCEPTION: {
                sb.append("An IOException was encountered, ensure the reader is properly instantiated, isn't closed, or that it is ready before trying again.\n").append(this.unexpectedObject);
                break;
            }
            case UNEXPECTED_CHARACTER: {
                sb.append("The unexpected character (").append(this.unexpectedObject).append(") was found at position ").append(this.position).append(". Fix the parsable string and try again.");
                break;
            }
            case UNEXPECTED_TOKEN: {
                sb.append("The unexpected token ").append(this.unexpectedObject).append(" was found at position ").append(this.position).append(". Fix the parsable string and try again.");
                break;
            }
            case UNEXPECTED_EXCEPTION: {
                sb.append("Please report this to the library's maintainer. The unexpected exception that should be addressed before trying again occurred at position ").append(this.position).append(":\n").append(this.unexpectedObject);
                break;
            }
            default: {
                sb.append("Please report this to the library's maintainer. An error at position ").append(this.position).append(" occurred. There are no recovery recommendations available.");
            }
        }
        return sb.toString();
    }

    public int getPosition() {
        return this.position;
    }

    public Problems getProblemType() {
        return this.problemType;
    }

    public Object getUnexpectedObject() {
        return this.unexpectedObject;
    }

    public static enum Problems {
        DISALLOWED_TOKEN,
        IOEXCEPTION,
        UNEXPECTED_CHARACTER,
        UNEXPECTED_EXCEPTION,
        UNEXPECTED_TOKEN;

    }
}

