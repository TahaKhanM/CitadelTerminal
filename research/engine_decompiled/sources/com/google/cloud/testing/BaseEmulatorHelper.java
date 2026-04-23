/*
 * Decompiled with CFR 0.152.
 */
package com.google.cloud.testing;

import com.google.api.core.InternalApi;
import com.google.cloud.ServiceOptions;
import com.google.cloud.testing.BlockingProcessStreamReader;
import com.google.cloud.testing.CommandWrapper;
import com.google.cloud.testing.Version;
import com.google.common.io.CharStreams;
import com.google.common.util.concurrent.SettableFuture;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.threeten.bp.Duration;

@InternalApi
public abstract class BaseEmulatorHelper<T extends ServiceOptions> {
    private final String emulator;
    private final int port;
    private final String projectId;
    private EmulatorRunner activeRunner;
    private BlockingProcessStreamReader blockingProcessReader;
    protected static final String PROJECT_ID_PREFIX = "test-project-";
    protected static final String DEFAULT_HOST = "localhost";
    protected static final int DEFAULT_PORT = 8080;

    @InternalApi(value="This class should only be extended within google-cloud-java")
    protected BaseEmulatorHelper(String emulator, int port, String projectId) {
        this.emulator = emulator;
        this.port = port > 0 ? port : 8080;
        this.projectId = projectId;
    }

    protected abstract List<EmulatorRunner> getEmulatorRunners();

    protected abstract Logger getLogger();

    protected final void startProcess(String blockUntilOutput) throws IOException, InterruptedException {
        for (EmulatorRunner runner : this.getEmulatorRunners()) {
            if (!runner.isAvailable()) continue;
            this.activeRunner = runner;
            runner.start();
            break;
        }
        if (this.activeRunner == null) {
            throw new IOException("No available emulator runner is found.");
        }
        this.blockingProcessReader = BlockingProcessStreamReader.start(this.emulator, this.activeRunner.getProcess().getInputStream(), blockUntilOutput, this.getLogger());
    }

    protected final int waitForProcess(Duration timeout) throws IOException, InterruptedException, TimeoutException {
        if (this.activeRunner != null) {
            int exitCode = this.activeRunner.waitFor(timeout);
            this.activeRunner = null;
            return exitCode;
        }
        if (this.blockingProcessReader != null) {
            this.blockingProcessReader.join();
            this.blockingProcessReader = null;
        }
        return 0;
    }

    private static int waitForProcess(final Process process, Duration timeout) throws InterruptedException, TimeoutException {
        if (process == null) {
            return 0;
        }
        final SettableFuture exitValue = SettableFuture.create();
        Thread waiter = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    exitValue.set(process.waitFor());
                }
                catch (InterruptedException e) {
                    exitValue.setException(e);
                }
            }
        });
        waiter.start();
        try {
            int n = (Integer)exitValue.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            return n;
        }
        catch (ExecutionException e) {
            if (e.getCause() instanceof InterruptedException) {
                throw (InterruptedException)e.getCause();
            }
            throw new UncheckedExecutionException(e);
        }
        finally {
            waiter.interrupt();
        }
    }

    public int getPort() {
        return this.port;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public abstract T getOptions();

    public abstract void start() throws IOException, InterruptedException;

    public abstract void stop(Duration var1) throws IOException, InterruptedException, TimeoutException;

    public abstract void reset() throws IOException;

    protected final String sendPostRequest(String request) throws IOException {
        URL url = new URL("http", DEFAULT_HOST, this.port, request);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        OutputStream out = con.getOutputStream();
        out.write("".getBytes());
        out.flush();
        InputStream in = con.getInputStream();
        String response = CharStreams.toString(new InputStreamReader(con.getInputStream()));
        in.close();
        return response;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static int findAvailablePort(int defaultPort) {
        try (ServerSocket tempSocket = new ServerSocket(0);){
            int n = tempSocket.getLocalPort();
            return n;
        }
        catch (IOException e) {
            return defaultPort;
        }
    }

    protected static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows");
    }

    protected static class DownloadableEmulatorRunner
    implements EmulatorRunner {
        private final List<String> commandText;
        private final String md5CheckSum;
        private final URL downloadUrl;
        private final String fileName;
        private Process process;
        private static final Logger log = Logger.getLogger(DownloadableEmulatorRunner.class.getName());

        public DownloadableEmulatorRunner(List<String> commandText, URL downloadUrl, String md5CheckSum) {
            this.commandText = commandText;
            this.md5CheckSum = md5CheckSum;
            this.downloadUrl = downloadUrl;
            String[] splitUrl = downloadUrl.toString().split("/");
            this.fileName = splitUrl[splitUrl.length - 1];
        }

        @Override
        public boolean isAvailable() {
            try {
                this.downloadZipFile();
                return true;
            }
            catch (IOException e) {
                return false;
            }
        }

        @Override
        public void start() throws IOException {
            Path emulatorPath = this.downloadEmulator();
            this.process = CommandWrapper.create().setCommand(this.commandText).setDirectory(emulatorPath).setRedirectErrorStream().start();
        }

        @Override
        public int waitFor(Duration timeout) throws InterruptedException, TimeoutException {
            return BaseEmulatorHelper.waitForProcess(this.process, timeout);
        }

        @Override
        public Process getProcess() {
            return this.process;
        }

        private Path downloadEmulator() throws IOException {
            String[] splittedUrl = this.downloadUrl.toString().split("/");
            String fileName = splittedUrl[splittedUrl.length - 1];
            Path emulatorPath = Files.createTempDirectory(fileName.split("\\.")[0], new FileAttribute[0]);
            File emulatorFolder = emulatorPath.toFile();
            emulatorFolder.deleteOnExit();
            File zipFile = this.downloadZipFile();
            try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile));){
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Unzipping emulator");
                }
                ZipEntry entry = zipIn.getNextEntry();
                while (entry != null) {
                    File filePath = new File(emulatorFolder, entry.getName());
                    String canonicalEmulatorFolderPath = emulatorFolder.getCanonicalPath();
                    String canonicalFilePath = filePath.getCanonicalPath();
                    if (!canonicalFilePath.startsWith(canonicalEmulatorFolderPath + File.separator)) {
                        throw new IllegalStateException("Entry is outside of the target dir: " + entry.getName());
                    }
                    if (!entry.isDirectory()) {
                        this.extractFile(zipIn, filePath);
                    } else {
                        filePath.mkdir();
                    }
                    zipIn.closeEntry();
                    entry = zipIn.getNextEntry();
                }
            }
            return emulatorPath;
        }

        private File downloadZipFile() throws IOException {
            File zipFile = new File(System.getProperty("java.io.tmpdir"), this.fileName);
            if (!zipFile.exists() || this.md5CheckSum != null && !this.md5CheckSum.equals(DownloadableEmulatorRunner.md5(zipFile))) {
                if (log.isLoggable(Level.FINE)) {
                    log.fine("Fetching emulator");
                }
                ReadableByteChannel rbc = Channels.newChannel(this.downloadUrl.openStream());
                try (FileOutputStream fos = new FileOutputStream(zipFile);){
                    fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
                }
            } else if (log.isLoggable(Level.FINE)) {
                log.fine("Using cached emulator");
            }
            return zipFile;
        }

        private void extractFile(ZipInputStream zipIn, File filePath) throws IOException {
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));){
                int read;
                byte[] bytesIn = new byte[1024];
                while ((read = zipIn.read(bytesIn)) != -1) {
                    bos.write(bytesIn, 0, read);
                }
            }
        }

        private static String md5(File zipFile) throws IOException {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(zipFile));){
                    int len;
                    byte[] bytes2 = new byte[0x400000];
                    while ((len = ((InputStream)is).read(bytes2)) >= 0) {
                        md5.update(bytes2, 0, len);
                    }
                }
                return String.format("%032x", new BigInteger(1, md5.digest()));
            }
            catch (NoSuchAlgorithmException e) {
                throw new IOException(e);
            }
        }
    }

    protected static class GcloudEmulatorRunner
    implements EmulatorRunner {
        private final List<String> commandText;
        private final String versionPrefix;
        private final Version minVersion;
        private Process process;
        private static final Logger log = Logger.getLogger(GcloudEmulatorRunner.class.getName());

        public GcloudEmulatorRunner(List<String> commandText, String versionPrefix, String minVersion) {
            this.commandText = commandText;
            this.versionPrefix = versionPrefix;
            this.minVersion = Version.fromString(minVersion);
        }

        @Override
        public boolean isAvailable() {
            try {
                return this.isGcloudInstalled() && this.isEmulatorUpToDate() && !this.commandText.isEmpty();
            }
            catch (IOException | InterruptedException e) {
                e.printStackTrace(System.err);
                return false;
            }
        }

        @Override
        public void start() throws IOException {
            log.fine("Starting emulator via Google Cloud SDK");
            this.process = CommandWrapper.create().setCommand(this.commandText).setRedirectErrorStream().start();
        }

        @Override
        public int waitFor(Duration timeout) throws InterruptedException, TimeoutException {
            return BaseEmulatorHelper.waitForProcess(this.process, timeout);
        }

        @Override
        public Process getProcess() {
            return this.process;
        }

        private boolean isGcloudInstalled() {
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                if (!"PATH".equals(envName)) continue;
                return env.get(envName).contains("google-cloud-sdk");
            }
            return false;
        }

        private boolean isEmulatorUpToDate() throws IOException, InterruptedException {
            Version currentVersion = this.getInstalledEmulatorVersion(this.versionPrefix);
            return currentVersion != null && currentVersion.compareTo(this.minVersion) >= 0;
        }

        private Version getInstalledEmulatorVersion(String versionPrefix) throws IOException, InterruptedException {
            Process process = CommandWrapper.create().setCommand(Arrays.asList("gcloud", "version")).setRedirectErrorStream().start();
            process.waitFor();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));){
                String line = reader.readLine();
                while (line != null) {
                    String[] lineComponents;
                    if (line.startsWith(versionPrefix) && (lineComponents = line.split(" ")).length > 1) {
                        Version version = Version.fromString(lineComponents[1]);
                        return version;
                    }
                    line = reader.readLine();
                }
                Version version = null;
                return version;
            }
        }
    }

    protected static interface EmulatorRunner {
        public boolean isAvailable();

        public void start() throws IOException;

        public int waitFor(Duration var1) throws InterruptedException, TimeoutException;

        public Process getProcess();
    }
}

