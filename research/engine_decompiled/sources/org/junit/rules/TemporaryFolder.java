/*
 * Decompiled with CFR 0.152.
 */
package org.junit.rules;

import java.io.File;
import java.io.IOException;
import org.junit.rules.ExternalResource;

public class TemporaryFolder
extends ExternalResource {
    private File folder;

    protected void before() throws Throwable {
        this.create();
    }

    protected void after() {
        this.delete();
    }

    public void create() throws IOException {
        this.folder = this.newFolder();
    }

    public File newFile(String fileName) throws IOException {
        File file = new File(this.getRoot(), fileName);
        file.createNewFile();
        return file;
    }

    public File newFile() throws IOException {
        return File.createTempFile("junit", null, this.folder);
    }

    public File newFolder(String ... folderNames) {
        File file = this.getRoot();
        for (String folderName : folderNames) {
            file = new File(file, folderName);
            file.mkdir();
        }
        return file;
    }

    public File newFolder() throws IOException {
        File createdFolder = File.createTempFile("junit", "", this.folder);
        createdFolder.delete();
        createdFolder.mkdir();
        return createdFolder;
    }

    public File getRoot() {
        if (this.folder == null) {
            throw new IllegalStateException("the temporary folder has not yet been created");
        }
        return this.folder;
    }

    public void delete() {
        this.recursiveDelete(this.folder);
    }

    private void recursiveDelete(File file) {
        File[] files2 = file.listFiles();
        if (files2 != null) {
            for (File each : files2) {
                this.recursiveDelete(each);
            }
        }
        file.delete();
    }
}

