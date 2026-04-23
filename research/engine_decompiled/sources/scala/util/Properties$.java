/*
 * Decompiled with CFR 0.152.
 */
package scala.util;

import java.util.Properties;
import java.util.jar.Attributes;
import scala.Option;
import scala.util.PropertiesTrait;
import scala.util.PropertiesTrait$class;

public final class Properties$
implements PropertiesTrait {
    public static final Properties$ MODULE$;
    private final Attributes.Name ScalaCompilerVersion;
    private final String propFilename;
    private final Properties scalaProps;
    private final Option<String> releaseVersion;
    private final Option<String> developmentVersion;
    private final String versionString;
    private final String copyrightString;
    private volatile boolean bitmap$0;

    static {
        new Properties$();
    }

    @Override
    public String propFilename() {
        return this.propFilename;
    }

    private Properties scalaProps$lzycompute() {
        Properties$ properties$ = this;
        synchronized (properties$) {
            if (!this.bitmap$0) {
                this.scalaProps = PropertiesTrait$class.scalaProps(this);
                this.bitmap$0 = true;
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.scalaProps;
        }
    }

    @Override
    public Properties scalaProps() {
        return this.bitmap$0 ? this.scalaProps : this.scalaProps$lzycompute();
    }

    @Override
    public Option<String> releaseVersion() {
        return this.releaseVersion;
    }

    @Override
    public Option<String> developmentVersion() {
        return this.developmentVersion;
    }

    @Override
    public String versionString() {
        return this.versionString;
    }

    @Override
    public String copyrightString() {
        return this.copyrightString;
    }

    @Override
    public void scala$util$PropertiesTrait$_setter_$propFilename_$eq(String x$1) {
        this.propFilename = x$1;
    }

    @Override
    public void scala$util$PropertiesTrait$_setter_$releaseVersion_$eq(Option x$1) {
        this.releaseVersion = x$1;
    }

    @Override
    public void scala$util$PropertiesTrait$_setter_$developmentVersion_$eq(Option x$1) {
        this.developmentVersion = x$1;
    }

    @Override
    public void scala$util$PropertiesTrait$_setter_$versionString_$eq(String x$1) {
        this.versionString = x$1;
    }

    @Override
    public void scala$util$PropertiesTrait$_setter_$copyrightString_$eq(String x$1) {
        this.copyrightString = x$1;
    }

    @Override
    public boolean propIsSet(String name) {
        return PropertiesTrait$class.propIsSet(this, name);
    }

    @Override
    public boolean propIsSetTo(String name, String value) {
        return PropertiesTrait$class.propIsSetTo(this, name, value);
    }

    @Override
    public String propOrElse(String name, String alt) {
        return PropertiesTrait$class.propOrElse(this, name, alt);
    }

    @Override
    public String propOrEmpty(String name) {
        return PropertiesTrait$class.propOrEmpty(this, name);
    }

    @Override
    public String propOrNull(String name) {
        return PropertiesTrait$class.propOrNull(this, name);
    }

    @Override
    public Option<String> propOrNone(String name) {
        return PropertiesTrait$class.propOrNone(this, name);
    }

    @Override
    public boolean propOrFalse(String name) {
        return PropertiesTrait$class.propOrFalse(this, name);
    }

    @Override
    public String setProp(String name, String value) {
        return PropertiesTrait$class.setProp(this, name, value);
    }

    @Override
    public String clearProp(String name) {
        return PropertiesTrait$class.clearProp(this, name);
    }

    @Override
    public String envOrElse(String name, String alt) {
        return PropertiesTrait$class.envOrElse(this, name, alt);
    }

    @Override
    public Option<String> envOrNone(String name) {
        return PropertiesTrait$class.envOrNone(this, name);
    }

    @Override
    public Option<String> envOrSome(String name, Option<String> alt) {
        return PropertiesTrait$class.envOrSome(this, name, alt);
    }

    @Override
    public String scalaPropOrElse(String name, String alt) {
        return PropertiesTrait$class.scalaPropOrElse(this, name, alt);
    }

    @Override
    public String scalaPropOrEmpty(String name) {
        return PropertiesTrait$class.scalaPropOrEmpty(this, name);
    }

    @Override
    public Option<String> scalaPropOrNone(String name) {
        return PropertiesTrait$class.scalaPropOrNone(this, name);
    }

    @Override
    public String versionNumberString() {
        return PropertiesTrait$class.versionNumberString(this);
    }

    @Override
    public String sourceEncoding() {
        return PropertiesTrait$class.sourceEncoding(this);
    }

    @Override
    public String sourceReader() {
        return PropertiesTrait$class.sourceReader(this);
    }

    @Override
    public String encodingString() {
        return PropertiesTrait$class.encodingString(this);
    }

    @Override
    public String lineSeparator() {
        return PropertiesTrait$class.lineSeparator(this);
    }

    @Override
    public String javaClassPath() {
        return PropertiesTrait$class.javaClassPath(this);
    }

    @Override
    public String javaHome() {
        return PropertiesTrait$class.javaHome(this);
    }

    @Override
    public String javaVendor() {
        return PropertiesTrait$class.javaVendor(this);
    }

    @Override
    public String javaVersion() {
        return PropertiesTrait$class.javaVersion(this);
    }

    @Override
    public String javaVmInfo() {
        return PropertiesTrait$class.javaVmInfo(this);
    }

    @Override
    public String javaVmName() {
        return PropertiesTrait$class.javaVmName(this);
    }

    @Override
    public String javaVmVendor() {
        return PropertiesTrait$class.javaVmVendor(this);
    }

    @Override
    public String javaVmVersion() {
        return PropertiesTrait$class.javaVmVersion(this);
    }

    @Override
    public String javaSpecVersion() {
        return PropertiesTrait$class.javaSpecVersion(this);
    }

    @Override
    public String javaSpecVendor() {
        return PropertiesTrait$class.javaSpecVendor(this);
    }

    @Override
    public String javaSpecName() {
        return PropertiesTrait$class.javaSpecName(this);
    }

    @Override
    public String osName() {
        return PropertiesTrait$class.osName(this);
    }

    @Override
    public String scalaHome() {
        return PropertiesTrait$class.scalaHome(this);
    }

    @Override
    public String tmpDir() {
        return PropertiesTrait$class.tmpDir(this);
    }

    @Override
    public String userDir() {
        return PropertiesTrait$class.userDir(this);
    }

    @Override
    public String userHome() {
        return PropertiesTrait$class.userHome(this);
    }

    @Override
    public String userName() {
        return PropertiesTrait$class.userName(this);
    }

    @Override
    public boolean isWin() {
        return PropertiesTrait$class.isWin(this);
    }

    @Override
    public boolean isMac() {
        return PropertiesTrait$class.isMac(this);
    }

    @Override
    public boolean isAvian() {
        return PropertiesTrait$class.isAvian(this);
    }

    @Override
    public String jdkHome() {
        return PropertiesTrait$class.jdkHome(this);
    }

    @Override
    public String versionMsg() {
        return PropertiesTrait$class.versionMsg(this);
    }

    @Override
    public String scalaCmd() {
        return PropertiesTrait$class.scalaCmd(this);
    }

    @Override
    public String scalacCmd() {
        return PropertiesTrait$class.scalacCmd(this);
    }

    @Override
    public boolean isJavaAtLeast(String version) {
        return PropertiesTrait$class.isJavaAtLeast(this, version);
    }

    @Override
    public void main(String[] args) {
        PropertiesTrait$class.main(this, args);
    }

    @Override
    public String propCategory() {
        return "library";
    }

    public Class<Option<?>> pickJarBasedOn() {
        return Option.class;
    }

    public Attributes.Name ScalaCompilerVersion() {
        return this.ScalaCompilerVersion;
    }

    private Properties$() {
        MODULE$ = this;
        PropertiesTrait$class.$init$(this);
        this.ScalaCompilerVersion = new Attributes.Name("Scala-Compiler-Version");
    }
}

