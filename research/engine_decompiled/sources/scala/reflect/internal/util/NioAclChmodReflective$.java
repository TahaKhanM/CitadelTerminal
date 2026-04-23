/*
 * Decompiled with CFR 0.152.
 */
package scala.reflect.internal.util;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class NioAclChmodReflective$ {
    public static final NioAclChmodReflective$ MODULE$;
    private final Method file_toPath;
    private final Class<?> files;
    private final Class<?> path_class;
    private final Method getFileAttributeView;
    private final Object linkOptionEmptyArray;
    private final Class<?> aclFileAttributeView_class;
    private final Class<?> aclEntry_class;
    private final Class<?> aclEntryBuilder_class;
    private final Method newBuilder;
    private final Method aclEntryBuilder_build;
    private final Class<?> userPrinciple_class;
    private final Method setPrincipal;
    private final Method setPermissions;
    private final Class<?> aclEntryType_class;
    private final Method setType;
    private final Class<?> aclEntryPermission_class;
    private final Method aclEntryPermissionValues;
    private final Field aclEntryType_ALLOW;

    static {
        new NioAclChmodReflective$();
    }

    public Method file_toPath() {
        return this.file_toPath;
    }

    public Class<?> files() {
        return this.files;
    }

    public Class<?> path_class() {
        return this.path_class;
    }

    public Method getFileAttributeView() {
        return this.getFileAttributeView;
    }

    public Object linkOptionEmptyArray() {
        return this.linkOptionEmptyArray;
    }

    public Class<?> aclFileAttributeView_class() {
        return this.aclFileAttributeView_class;
    }

    public Class<?> aclEntry_class() {
        return this.aclEntry_class;
    }

    public Class<?> aclEntryBuilder_class() {
        return this.aclEntryBuilder_class;
    }

    public Method newBuilder() {
        return this.newBuilder;
    }

    public Method aclEntryBuilder_build() {
        return this.aclEntryBuilder_build;
    }

    public Class<?> userPrinciple_class() {
        return this.userPrinciple_class;
    }

    public Method setPrincipal() {
        return this.setPrincipal;
    }

    public Method setPermissions() {
        return this.setPermissions;
    }

    public Class<?> aclEntryType_class() {
        return this.aclEntryType_class;
    }

    public Method setType() {
        return this.setType;
    }

    public Class<?> aclEntryPermission_class() {
        return this.aclEntryPermission_class;
    }

    public Method aclEntryPermissionValues() {
        return this.aclEntryPermissionValues;
    }

    public Field aclEntryType_ALLOW() {
        return this.aclEntryType_ALLOW;
    }

    private NioAclChmodReflective$() {
        MODULE$ = this;
        this.file_toPath = File.class.getMethod("toPath", new Class[0]);
        this.files = Class.forName("java.nio.file.Files");
        this.path_class = Class.forName("java.nio.file.Path");
        this.getFileAttributeView = this.files().getMethod("getFileAttributeView", this.path_class(), Class.class, Class.forName("[Ljava.nio.file.LinkOption;"));
        this.linkOptionEmptyArray = Array.newInstance(Class.forName("java.nio.file.LinkOption"), 0);
        this.aclFileAttributeView_class = Class.forName("java.nio.file.attribute.AclFileAttributeView");
        this.aclEntry_class = Class.forName("java.nio.file.attribute.AclEntry");
        this.aclEntryBuilder_class = Class.forName("java.nio.file.attribute.AclEntry$Builder");
        this.newBuilder = this.aclEntry_class().getMethod("newBuilder", new Class[0]);
        this.aclEntryBuilder_build = this.aclEntryBuilder_class().getMethod("build", new Class[0]);
        this.userPrinciple_class = Class.forName("java.nio.file.attribute.UserPrincipal");
        this.setPrincipal = this.aclEntryBuilder_class().getMethod("setPrincipal", this.userPrinciple_class());
        this.setPermissions = this.aclEntryBuilder_class().getMethod("setPermissions", Class.forName("[Ljava.nio.file.attribute.AclEntryPermission;"));
        this.aclEntryType_class = Class.forName("java.nio.file.attribute.AclEntryType");
        this.setType = this.aclEntryBuilder_class().getMethod("setType", this.aclEntryType_class());
        this.aclEntryPermission_class = Class.forName("java.nio.file.attribute.AclEntryPermission");
        this.aclEntryPermissionValues = this.aclEntryPermission_class().getDeclaredMethod("values", new Class[0]);
        this.aclEntryType_ALLOW = this.aclEntryType_class().getDeclaredField("ALLOW");
    }
}

