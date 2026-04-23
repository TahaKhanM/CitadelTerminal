/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpRequest;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.Attribute;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.DiskAttribute;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.DiskFileUpload;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.FileUpload;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpDataFactory;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.MemoryFileUpload;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.MixedAttribute;
import io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.MixedFileUpload;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefaultHttpDataFactory
implements HttpDataFactory {
    public static final long MINSIZE = 16384L;
    public static final long MAXSIZE = -1L;
    private final boolean useDisk;
    private final boolean checkSize;
    private long minSize;
    private long maxSize = -1L;
    private Charset charset = HttpConstants.DEFAULT_CHARSET;
    private final Map<HttpRequest, List<HttpData>> requestFileDeleteMap = Collections.synchronizedMap(new IdentityHashMap());

    public DefaultHttpDataFactory() {
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = 16384L;
    }

    public DefaultHttpDataFactory(Charset charset) {
        this();
        this.charset = charset;
    }

    public DefaultHttpDataFactory(boolean useDisk) {
        this.useDisk = useDisk;
        this.checkSize = false;
    }

    public DefaultHttpDataFactory(boolean useDisk, Charset charset) {
        this(useDisk);
        this.charset = charset;
    }

    public DefaultHttpDataFactory(long minSize) {
        this.useDisk = false;
        this.checkSize = true;
        this.minSize = minSize;
    }

    public DefaultHttpDataFactory(long minSize, Charset charset) {
        this(minSize);
        this.charset = charset;
    }

    @Override
    public void setMaxLimit(long maxSize) {
        this.maxSize = maxSize;
    }

    private List<HttpData> getList(HttpRequest request) {
        List<HttpData> list2 = this.requestFileDeleteMap.get(request);
        if (list2 == null) {
            list2 = new ArrayList<HttpData>();
            this.requestFileDeleteMap.put(request, list2);
        }
        return list2;
    }

    @Override
    public Attribute createAttribute(HttpRequest request, String name) {
        if (this.useDisk) {
            DiskAttribute attribute = new DiskAttribute(name, this.charset);
            attribute.setMaxSize(this.maxSize);
            List<HttpData> list2 = this.getList(request);
            list2.add(attribute);
            return attribute;
        }
        if (this.checkSize) {
            MixedAttribute attribute = new MixedAttribute(name, this.minSize, this.charset);
            attribute.setMaxSize(this.maxSize);
            List<HttpData> list3 = this.getList(request);
            list3.add(attribute);
            return attribute;
        }
        MemoryAttribute attribute = new MemoryAttribute(name);
        attribute.setMaxSize(this.maxSize);
        return attribute;
    }

    @Override
    public Attribute createAttribute(HttpRequest request, String name, long definedSize) {
        if (this.useDisk) {
            DiskAttribute attribute = new DiskAttribute(name, definedSize, this.charset);
            attribute.setMaxSize(this.maxSize);
            List<HttpData> list2 = this.getList(request);
            list2.add(attribute);
            return attribute;
        }
        if (this.checkSize) {
            MixedAttribute attribute = new MixedAttribute(name, definedSize, this.minSize, this.charset);
            attribute.setMaxSize(this.maxSize);
            List<HttpData> list3 = this.getList(request);
            list3.add(attribute);
            return attribute;
        }
        MemoryAttribute attribute = new MemoryAttribute(name, definedSize);
        attribute.setMaxSize(this.maxSize);
        return attribute;
    }

    private static void checkHttpDataSize(HttpData data) {
        try {
            data.checkSize(data.length());
        }
        catch (IOException ignored) {
            throw new IllegalArgumentException("Attribute bigger than maxSize allowed");
        }
    }

    @Override
    public Attribute createAttribute(HttpRequest request, String name, String value) {
        if (this.useDisk) {
            Attribute attribute;
            try {
                attribute = new DiskAttribute(name, value, this.charset);
                attribute.setMaxSize(this.maxSize);
            }
            catch (IOException e) {
                attribute = new MixedAttribute(name, value, this.minSize, this.charset);
                attribute.setMaxSize(this.maxSize);
            }
            DefaultHttpDataFactory.checkHttpDataSize(attribute);
            List<HttpData> list2 = this.getList(request);
            list2.add(attribute);
            return attribute;
        }
        if (this.checkSize) {
            MixedAttribute attribute = new MixedAttribute(name, value, this.minSize, this.charset);
            attribute.setMaxSize(this.maxSize);
            DefaultHttpDataFactory.checkHttpDataSize(attribute);
            List<HttpData> list3 = this.getList(request);
            list3.add(attribute);
            return attribute;
        }
        try {
            MemoryAttribute attribute = new MemoryAttribute(name, value, this.charset);
            attribute.setMaxSize(this.maxSize);
            DefaultHttpDataFactory.checkHttpDataSize(attribute);
            return attribute;
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public FileUpload createFileUpload(HttpRequest request, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size2) {
        if (this.useDisk) {
            DiskFileUpload fileUpload = new DiskFileUpload(name, filename, contentType, contentTransferEncoding, charset, size2);
            fileUpload.setMaxSize(this.maxSize);
            DefaultHttpDataFactory.checkHttpDataSize(fileUpload);
            List<HttpData> list2 = this.getList(request);
            list2.add(fileUpload);
            return fileUpload;
        }
        if (this.checkSize) {
            MixedFileUpload fileUpload = new MixedFileUpload(name, filename, contentType, contentTransferEncoding, charset, size2, this.minSize);
            fileUpload.setMaxSize(this.maxSize);
            DefaultHttpDataFactory.checkHttpDataSize(fileUpload);
            List<HttpData> list3 = this.getList(request);
            list3.add(fileUpload);
            return fileUpload;
        }
        MemoryFileUpload fileUpload = new MemoryFileUpload(name, filename, contentType, contentTransferEncoding, charset, size2);
        fileUpload.setMaxSize(this.maxSize);
        DefaultHttpDataFactory.checkHttpDataSize(fileUpload);
        return fileUpload;
    }

    @Override
    public void removeHttpDataFromClean(HttpRequest request, InterfaceHttpData data) {
        if (!(data instanceof HttpData)) {
            return;
        }
        List<HttpData> list2 = this.requestFileDeleteMap.get(request);
        if (list2 == null) {
            return;
        }
        Iterator<HttpData> i = list2.iterator();
        while (i.hasNext()) {
            HttpData n = i.next();
            if (n != data) continue;
            i.remove();
            if (list2.isEmpty()) {
                this.requestFileDeleteMap.remove(request);
            }
            return;
        }
    }

    @Override
    public void cleanRequestHttpData(HttpRequest request) {
        List<HttpData> list2 = this.requestFileDeleteMap.remove(request);
        if (list2 != null) {
            for (HttpData data : list2) {
                data.release();
            }
        }
    }

    @Override
    public void cleanAllHttpData() {
        Iterator<Map.Entry<HttpRequest, List<HttpData>>> i = this.requestFileDeleteMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<HttpRequest, List<HttpData>> e = i.next();
            List<HttpData> list2 = e.getValue();
            for (HttpData data : list2) {
                data.release();
            }
            i.remove();
        }
    }

    @Override
    public void cleanRequestHttpDatas(HttpRequest request) {
        this.cleanRequestHttpData(request);
    }

    @Override
    public void cleanAllHttpDatas() {
        this.cleanAllHttpData();
    }
}

