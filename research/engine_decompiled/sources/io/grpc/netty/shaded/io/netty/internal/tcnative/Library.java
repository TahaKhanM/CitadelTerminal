/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.internal.tcnative;

import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import java.io.File;

public final class Library {
    private static final String[] NAMES = new String[]{"netty_tcnative", "libnetty_tcnative"};
    private static final String PROVIDED = "provided";
    private static Library _instance = null;

    private Library() throws Exception {
        boolean loaded = false;
        String path = System.getProperty("java.library.path");
        String[] paths = path.split(File.pathSeparator);
        StringBuilder err = new StringBuilder();
        for (int i = 0; i < NAMES.length; ++i) {
            try {
                Library.loadLibrary(NAMES[i]);
                loaded = true;
            }
            catch (ThreadDeath t) {
                throw t;
            }
            catch (VirtualMachineError t) {
                throw t;
            }
            catch (Throwable t) {
                String name = System.mapLibraryName(NAMES[i]);
                for (int j = 0; j < paths.length; ++j) {
                    File fd = new File(paths[j], name);
                    if (!fd.exists()) continue;
                    throw new RuntimeException(t);
                }
                if (i > 0) {
                    err.append(", ");
                }
                err.append(t.getMessage());
            }
            if (loaded) break;
        }
        if (!loaded) {
            throw new UnsatisfiedLinkError(err.toString());
        }
    }

    private Library(String libraryName) {
        if (!PROVIDED.equals(libraryName)) {
            Library.loadLibrary(libraryName);
        }
    }

    private static void loadLibrary(String libraryName) {
        System.loadLibrary(Library.calculatePackagePrefix().replace('.', '_') + libraryName);
    }

    private static String calculatePackagePrefix() {
        String expected;
        String maybeShaded = Library.class.getName();
        if (!maybeShaded.endsWith(expected = "io!netty!internal!tcnative!Library".replace('!', '.'))) {
            throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", expected, maybeShaded));
        }
        return maybeShaded.substring(0, maybeShaded.length() - expected.length());
    }

    private static native boolean initialize0();

    private static native boolean aprHasThreads();

    private static native int aprMajorVersion();

    private static native String aprVersionString();

    public static boolean initialize() throws Exception {
        return Library.initialize(PROVIDED, null);
    }

    public static boolean initialize(String libraryName, String engine) throws Exception {
        if (_instance == null) {
            Library library = _instance = libraryName == null ? new Library() : new Library(libraryName);
            if (Library.aprMajorVersion() < 1) {
                throw new UnsatisfiedLinkError("Unsupported APR Version (" + Library.aprVersionString() + ")");
            }
            if (!Library.aprHasThreads()) {
                throw new UnsatisfiedLinkError("Missing APR_HAS_THREADS");
            }
        }
        return Library.initialize0() && SSL.initialize(engine) == 0;
    }
}

