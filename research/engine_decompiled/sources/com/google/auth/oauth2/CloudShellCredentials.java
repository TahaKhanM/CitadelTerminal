/*
 * Decompiled with CFR 0.152.
 */
package com.google.auth.oauth2;

import com.google.api.client.json.JsonParser;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.OAuth2Utils;
import com.google.common.base.MoreObjects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CloudShellCredentials
extends GoogleCredentials {
    private static final long serialVersionUID = -2133257318957488451L;
    private static final int ACCESS_TOKEN_INDEX = 2;
    private static final int READ_TIMEOUT_MS = 5000;
    protected static final String GET_AUTH_TOKEN_REQUEST = "2\n[]";
    private final int authPort;

    @Deprecated
    public static CloudShellCredentials of(int authPort) {
        return CloudShellCredentials.create(authPort);
    }

    public static CloudShellCredentials create(int authPort) {
        return CloudShellCredentials.newBuilder().setAuthPort(authPort).build();
    }

    @Deprecated
    public CloudShellCredentials(int authPort) {
        this.authPort = authPort;
    }

    protected int getAuthPort() {
        return this.authPort;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public AccessToken refreshAccessToken() throws IOException {
        AccessToken token;
        socket.setSoTimeout(5000);
        try (Socket socket = new Socket("localhost", this.getAuthPort());){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(GET_AUTH_TOKEN_REQUEST);
            BufferedReader input2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            input2.readLine();
            JsonParser parser = OAuth2Utils.JSON_FACTORY.createJsonParser(input2);
            List messageArray = (List)parser.parseArray(ArrayList.class, Object.class);
            String accessToken = messageArray.get(2).toString();
            token = new AccessToken(accessToken, null);
        }
        return token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.authPort);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("authPort", this.authPort).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CloudShellCredentials)) {
            return false;
        }
        CloudShellCredentials other = (CloudShellCredentials)obj;
        return this.authPort == other.authPort;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder
    extends GoogleCredentials.Builder {
        private int authPort;

        protected Builder() {
        }

        protected Builder(CloudShellCredentials credentials) {
            this.authPort = credentials.authPort;
        }

        public Builder setAuthPort(int authPort) {
            this.authPort = authPort;
            return this;
        }

        public int getAuthPort() {
            return this.authPort;
        }

        @Override
        public CloudShellCredentials build() {
            return new CloudShellCredentials(this.authPort);
        }
    }
}

