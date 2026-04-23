/*
 * Decompiled with CFR 0.152.
 */
package towersocket;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {
    public static boolean DEBUG = false;
    public static final int WEB_PORT = 3047;
    public static final int HEADER_NEW_CONNECTION = 110;
    public static final int HEADER_ACCEPT_CONNECTION = 97;
    public static final int HEADER_REJECT_CONNECTION = 114;
    public static final int HEADER_MESSAGE = 109;
    public static final int HEADER_REQUEST = 103;
    public static final int HEADER_RESPOND = 112;
    public static final Charset CHARSET = StandardCharsets.UTF_8;
    public static final String CHARSET_NAME = "UTF-8";
}

