/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.util;

import com.c1games.terminal.Terminal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.NoSuchElementException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.http.ssl.SSLContextBuilder;

public class ManagerDownloader {
    private static String managerAddress = System.getenv("BASE_URL");
    private String[] toIgnore = new String[]{".DS_Store", "__MACOSX"};
    public static final String configSaveLocation = "./configs/";
    public static SSLContext sslContext = null;
    public static final Gson GSON = new GsonBuilder().create();

    public ManagerDownloader() {
        SSLContextBuilder sslContextBuilder = SSLContextBuilder.create();
        try {
            if (Terminal.config.sslKeystore != null) {
                sslContextBuilder.loadTrustMaterial(new File(Terminal.config.sslKeystore), Terminal.config.sslPassword.toCharArray());
                sslContext = sslContextBuilder.build();
                System.out.println("Built manager downloader ssl context");
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (KeyManagementException e) {
            e.printStackTrace();
        }
        catch (CertificateException e) {
            e.printStackTrace();
        }
        catch (KeyStoreException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] downloadAlgo(String name) {
        byte[] binaryData;
        URL getEndpoint;
        int id;
        System.out.println(name);
        boolean boss = false;
        if (name.contains("bossAlgo")) {
            id = Integer.parseInt(name.substring(8));
            System.out.println(id);
            boss = true;
        } else if (name.contains("uploadedAlgo")) {
            id = Integer.parseInt(name.substring(12));
            System.out.println(id);
        } else {
            return null;
        }
        String k = System.getenv("WORKER_KEY");
        if (k == null) {
            k = "TEST_KEY";
        }
        String api2 = boss ? "/api/game/algo/bosses/" + id + "?worker_key=" + k : "/api/game/algo/" + id + "/compiled?worker_key=" + k;
        try {
            getEndpoint = new URL(managerAddress + api2);
        }
        catch (MalformedURLException e) {
            System.err.println("unexpected malformed URL exception:");
            throw new RuntimeException(e);
        }
        System.out.println("endpoint = " + String.valueOf(getEndpoint));
        try {
            int read;
            HttpURLConnection downloadConnection = ManagerDownloader.establishHttpConnection(getEndpoint);
            downloadConnection.setRequestMethod("GET");
            BufferedInputStream downloadStream = new BufferedInputStream(downloadConnection.getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            while ((read = ((InputStream)downloadStream).read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
            binaryData = baos.toByteArray();
        }
        catch (IOException e) {
            System.err.println("IOException downloading algo data:");
            e.printStackTrace();
            return null;
        }
        return binaryData;
    }

    private static HttpURLConnection establishHttpConnection(String urlString) throws IOException {
        URL url = new URL(urlString);
        return ManagerDownloader.establishHttpConnection(url);
    }

    private static HttpURLConnection establishHttpConnection(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        if (sslContext != null && connection instanceof HttpsURLConnection) {
            System.out.println("Got SSL Context");
            HttpsURLConnection secure = (HttpsURLConnection)connection;
            secure.setSSLSocketFactory(sslContext.getSocketFactory());
        }
        return connection;
    }

    private boolean shouldExclude(ZipEntry entry) {
        for (String s2 : this.toIgnore) {
            if (!entry.getName().contains(s2)) continue;
            return true;
        }
        return false;
    }

    public File downloadUnzipFileFromWeb(String urlString, String fileDenoter) throws IOException, NoSuchElementException {
        ZipEntry curr;
        int i;
        ZipEntry curr2;
        BufferedInputStream in = new BufferedInputStream(ManagerDownloader.establishHttpConnection(urlString).getInputStream());
        ZipInputStream zin = new ZipInputStream(in);
        while ((curr2 = zin.getNextEntry()) != null && this.shouldExclude(curr2)) {
            System.out.println("ignoring zip entry: " + String.valueOf(curr2));
        }
        if (curr2 == null) {
            throw new NoSuchElementException("no suitable zip entry found");
        }
        System.out.println("selecting zip entry: " + String.valueOf(curr2));
        File file = new File(fileDenoter);
        file.getParentFile().mkdirs();
        file.createNewFile();
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buffer = new byte[4096];
        while ((i = zin.read(buffer)) != -1) {
            out.write(buffer, 0, i);
        }
        out.close();
        while ((curr = zin.getNextEntry()) != null) {
            if (this.shouldExclude(curr)) continue;
            System.err.println("warning: multiple suitable zip entries found: " + String.valueOf(curr));
        }
        return file;
    }

    public File getConfig(String configNumber, String configType, String saveName) throws IOException {
        String k = System.getenv("WORKER_KEY");
        if (k == null) {
            k = "TEST_KEY";
        }
        String endpoint = "/api/game/config/";
        if (configType.equals("user")) {
            endpoint = "/api/game/config/user/";
        }
        File gameConfig = this.downloadUnzipFileFromWeb(managerAddress + endpoint + configNumber + "?worker_key=" + k, configSaveLocation + saveName);
        return gameConfig;
    }

    public static LatestVariant getLatestVariant() throws Exception {
        return GSON.fromJson(ManagerDownloader.requestLatestVariant(), LatestVariant.class);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String requestLatestVariant() throws Exception {
        HttpURLConnection connection = null;
        try {
            String responseString;
            String url = managerAddress + "/api/game/variant/latest";
            System.out.println("Making request to: " + url);
            connection = ManagerDownloader.establishHttpConnection(url);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int result2 = connection.getResponseCode();
            if (result2 != 200) {
                throw new IOException("Failed response code: " + result2);
            }
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));){
                responseString = ManagerDownloader.collectStringFromReader(responseReader);
            }
            String string2 = responseString;
            return string2;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String collectStringFromReader(Reader reader) throws IOException {
        int i;
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[4096];
        while ((i = reader.read(buffer, 0, buffer.length)) > 0) {
            builder.append(buffer, 0, i);
        }
        return builder.toString();
    }

    public class LatestVariant {
        public int engine;
        public int config;

        public String toString() {
            return "LatestVariant{engine=" + this.engine + ", config=" + this.config + "}";
        }
    }
}

