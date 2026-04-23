/*
 * Decompiled with CFR 0.152.
 */
package org.threeten.bp.zone;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.Year;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.zone.Ser;
import org.threeten.bp.zone.ZoneOffsetTransitionRule;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesBuilder;

final class TzdbZoneRulesCompiler {
    private static final DateTimeFormatter TIME_PARSER = new DateTimeFormatterBuilder().appendValue(ChronoField.HOUR_OF_DAY).optionalStart().appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
    private final Map<String, List<TZDBRule>> rules = new HashMap<String, List<TZDBRule>>();
    private final Map<String, List<TZDBZone>> zones = new HashMap<String, List<TZDBZone>>();
    private final Map<String, String> links = new HashMap<String, String>();
    private final SortedMap<String, ZoneRules> builtZones = new TreeMap<String, ZoneRules>();
    private Map<Object, Object> deduplicateMap = new HashMap<Object, Object>();
    private final SortedMap<LocalDate, Byte> leapSeconds = new TreeMap<LocalDate, Byte>();
    private final String version;
    private final List<File> sourceFiles;
    private final File leapSecondsFile;
    private final boolean verbose;

    public static void main(String[] args) {
        String arg;
        int i;
        if (args.length < 2) {
            TzdbZoneRulesCompiler.outputHelp();
            return;
        }
        String version = null;
        File baseSrcDir = null;
        File dstDir = null;
        boolean unpacked = false;
        boolean verbose = false;
        for (i = 0; i < args.length && (arg = args[i]).startsWith("-"); ++i) {
            if ("-srcdir".equals(arg)) {
                if (baseSrcDir == null && ++i < args.length) {
                    baseSrcDir = new File(args[i]);
                    continue;
                }
            } else if ("-dstdir".equals(arg)) {
                if (dstDir == null && ++i < args.length) {
                    dstDir = new File(args[i]);
                    continue;
                }
            } else if ("-version".equals(arg)) {
                if (version == null && ++i < args.length) {
                    version = args[i];
                    continue;
                }
            } else if ("-unpacked".equals(arg)) {
                if (!unpacked) {
                    unpacked = true;
                    continue;
                }
            } else if ("-verbose".equals(arg)) {
                if (!verbose) {
                    verbose = true;
                    continue;
                }
            } else if (!"-help".equals(arg)) {
                System.out.println("Unrecognised option: " + arg);
            }
            TzdbZoneRulesCompiler.outputHelp();
            return;
        }
        if (baseSrcDir == null) {
            System.out.println("Source directory must be specified using -srcdir: " + baseSrcDir);
            return;
        }
        if (!baseSrcDir.isDirectory()) {
            System.out.println("Source does not exist or is not a directory: " + baseSrcDir);
            return;
        }
        dstDir = dstDir != null ? dstDir : baseSrcDir;
        List<String> srcFileNames = Arrays.asList(Arrays.copyOfRange(args, i, args.length));
        if (srcFileNames.isEmpty()) {
            System.out.println("Source filenames not specified, using default set");
            System.out.println("(africa antarctica asia australasia backward etcetera europe northamerica southamerica)");
            srcFileNames = Arrays.asList("africa", "antarctica", "asia", "australasia", "backward", "etcetera", "europe", "northamerica", "southamerica");
        }
        ArrayList<File> srcDirs = new ArrayList<File>();
        if (version != null) {
            File srcDir = new File(baseSrcDir, version);
            if (!srcDir.isDirectory()) {
                System.out.println("Version does not represent a valid source directory : " + srcDir);
                return;
            }
            srcDirs.add(srcDir);
        } else {
            File[] dirs2;
            for (File dir : dirs2 = baseSrcDir.listFiles()) {
                if (!dir.isDirectory() || !dir.getName().matches("[12][0-9][0-9][0-9][A-Za-z0-9._-]+")) continue;
                srcDirs.add(dir);
            }
        }
        if (srcDirs.isEmpty()) {
            System.out.println("Source directory contains no valid source folders: " + baseSrcDir);
            return;
        }
        if (!dstDir.exists() && !dstDir.mkdirs()) {
            System.out.println("Destination directory could not be created: " + dstDir);
            return;
        }
        if (!dstDir.isDirectory()) {
            System.out.println("Destination is not a directory: " + dstDir);
            return;
        }
        TzdbZoneRulesCompiler.process(srcDirs, srcFileNames, dstDir, unpacked, verbose);
    }

    private static void outputHelp() {
        System.out.println("Usage: TzdbZoneRulesCompiler <options> <tzdb source filenames>");
        System.out.println("where options include:");
        System.out.println("   -srcdir <directory>   Where to find source directories (required)");
        System.out.println("   -dstdir <directory>   Where to output generated files (default srcdir)");
        System.out.println("   -version <version>    Specify the version, such as 2009a (optional)");
        System.out.println("   -unpacked             Generate dat files without jar files");
        System.out.println("   -help                 Print this usage message");
        System.out.println("   -verbose              Output verbose information during compilation");
        System.out.println(" There must be one directory for each version in srcdir");
        System.out.println(" Each directory must have the name of the version, such as 2009a");
        System.out.println(" Each directory must contain the unpacked tzdb files, such as asia or europe");
        System.out.println(" Directories must match the regex [12][0-9][0-9][0-9][A-Za-z0-9._-]+");
        System.out.println(" There will be one jar file for each version and one combined jar in dstdir");
        System.out.println(" If the version is specified, only that version is processed");
    }

    private static void process(List<File> srcDirs, List<String> srcFileNames, File dstDir, boolean unpacked, boolean verbose) {
        HashMap<Object, Object> deduplicateMap = new HashMap<Object, Object>();
        TreeMap<String, SortedMap<String, ZoneRules>> allBuiltZones = new TreeMap<String, SortedMap<String, ZoneRules>>();
        TreeSet<String> allRegionIds = new TreeSet<String>();
        HashSet<ZoneRules> allRules = new HashSet<ZoneRules>();
        SortedMap<LocalDate, Byte> bestLeapSeconds = null;
        for (File srcDir : srcDirs) {
            ArrayList<File> srcFiles = new ArrayList<File>();
            for (String srcFileName : srcFileNames) {
                File file = new File(srcDir, srcFileName);
                if (!file.exists()) continue;
                srcFiles.add(file);
            }
            if (srcFiles.isEmpty()) continue;
            File leapSecondsFile = new File(srcDir, "leapseconds");
            if (!leapSecondsFile.exists()) {
                System.out.println("Version " + srcDir.getName() + " does not include leap seconds information.");
                leapSecondsFile = null;
            }
            String loopVersion = srcDir.getName();
            TzdbZoneRulesCompiler compiler = new TzdbZoneRulesCompiler(loopVersion, srcFiles, leapSecondsFile, verbose);
            compiler.setDeduplicateMap(deduplicateMap);
            try {
                compiler.compile();
                SortedMap<String, ZoneRules> builtZones = compiler.getZones();
                SortedMap<LocalDate, Byte> parsedLeapSeconds = compiler.getLeapSeconds();
                if (!unpacked) {
                    File dstFile = new File(dstDir, "threeten-TZDB-" + loopVersion + ".jar");
                    if (verbose) {
                        System.out.println("Outputting file: " + dstFile);
                    }
                    TzdbZoneRulesCompiler.outputFile(dstFile, loopVersion, builtZones, parsedLeapSeconds);
                }
                allBuiltZones.put(loopVersion, builtZones);
                allRegionIds.addAll(builtZones.keySet());
                allRules.addAll(builtZones.values());
                if (compiler.getMostRecentLeapSecond() == null || bestLeapSeconds != null && compiler.getMostRecentLeapSecond().compareTo(bestLeapSeconds.lastKey()) <= 0) continue;
                bestLeapSeconds = parsedLeapSeconds;
            }
            catch (Exception ex) {
                System.out.println("Failed: " + ex.toString());
                ex.printStackTrace();
                System.exit(1);
            }
        }
        if (unpacked) {
            if (verbose) {
                System.out.println("Outputting combined files: " + dstDir);
            }
            TzdbZoneRulesCompiler.outputFilesDat(dstDir, allBuiltZones, allRegionIds, allRules, bestLeapSeconds);
        } else {
            File dstFile = new File(dstDir, "threeten-TZDB-all.jar");
            if (verbose) {
                System.out.println("Outputting combined file: " + dstFile);
            }
            TzdbZoneRulesCompiler.outputFile(dstFile, allBuiltZones, allRegionIds, allRules, bestLeapSeconds);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void outputFilesDat(File dstDir, Map<String, SortedMap<String, ZoneRules>> allBuiltZones, Set<String> allRegionIds, Set<ZoneRules> allRules, SortedMap<LocalDate, Byte> leapSeconds) {
        File tzdbFile = new File(dstDir, "TZDB.dat");
        tzdbFile.delete();
        try {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(tzdbFile);
                TzdbZoneRulesCompiler.outputTzdbDat(fos, allBuiltZones, allRegionIds, allRules);
            }
            finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Failed: " + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void outputFile(File dstFile, String version, SortedMap<String, ZoneRules> builtZones, SortedMap<LocalDate, Byte> leapSeconds) {
        TreeMap<String, SortedMap<String, ZoneRules>> loopAllBuiltZones = new TreeMap<String, SortedMap<String, ZoneRules>>();
        loopAllBuiltZones.put(version, builtZones);
        TreeSet<String> loopAllRegionIds = new TreeSet<String>(builtZones.keySet());
        HashSet<ZoneRules> loopAllRules = new HashSet<ZoneRules>(builtZones.values());
        TzdbZoneRulesCompiler.outputFile(dstFile, loopAllBuiltZones, loopAllRegionIds, loopAllRules, leapSeconds);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void outputFile(File dstFile, Map<String, SortedMap<String, ZoneRules>> allBuiltZones, Set<String> allRegionIds, Set<ZoneRules> allRules, SortedMap<LocalDate, Byte> leapSeconds) {
        ZipOutputStream jos = null;
        try {
            jos = new JarOutputStream(new FileOutputStream(dstFile));
            TzdbZoneRulesCompiler.outputTzdbEntry((JarOutputStream)jos, allBuiltZones, allRegionIds, allRules);
        }
        catch (Exception ex) {
            System.out.println("Failed: " + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        }
        finally {
            if (jos != null) {
                try {
                    jos.close();
                }
                catch (IOException ex) {}
            }
        }
    }

    private static void outputTzdbEntry(JarOutputStream jos, Map<String, SortedMap<String, ZoneRules>> allBuiltZones, Set<String> allRegionIds, Set<ZoneRules> allRules) {
        try {
            jos.putNextEntry(new ZipEntry("org/threeten/bp/TZDB.dat"));
            TzdbZoneRulesCompiler.outputTzdbDat(jos, allBuiltZones, allRegionIds, allRules);
            jos.closeEntry();
        }
        catch (Exception ex) {
            System.out.println("Failed: " + ex.toString());
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void outputTzdbDat(OutputStream jos, Map<String, SortedMap<String, ZoneRules>> allBuiltZones, Set<String> allRegionIds, Set<ZoneRules> allRules) throws IOException {
        DataOutputStream out = new DataOutputStream(jos);
        out.writeByte(1);
        out.writeUTF("TZDB");
        String[] versionArray = allBuiltZones.keySet().toArray(new String[allBuiltZones.size()]);
        out.writeShort(versionArray.length);
        for (String version : versionArray) {
            out.writeUTF(version);
        }
        Object[] regionArray = allRegionIds.toArray(new String[allRegionIds.size()]);
        out.writeShort(regionArray.length);
        for (String string2 : regionArray) {
            out.writeUTF(string2);
        }
        ArrayList<ZoneRules> rulesList = new ArrayList<ZoneRules>(allRules);
        out.writeShort(rulesList.size());
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        for (ZoneRules zoneRules : rulesList) {
            baos.reset();
            DataOutputStream dataos = new DataOutputStream(baos);
            Ser.write(zoneRules, dataos);
            dataos.close();
            byte[] bytes2 = baos.toByteArray();
            out.writeShort(bytes2.length);
            out.write(bytes2);
        }
        for (String string3 : allBuiltZones.keySet()) {
            out.writeShort(allBuiltZones.get(string3).size());
            for (Map.Entry<String, ZoneRules> entry : allBuiltZones.get(string3).entrySet()) {
                int regionIndex = Arrays.binarySearch(regionArray, entry.getKey());
                int rulesIndex = rulesList.indexOf(entry.getValue());
                out.writeShort(regionIndex);
                out.writeShort(rulesIndex);
            }
        }
        out.flush();
    }

    public TzdbZoneRulesCompiler(String version, List<File> sourceFiles, File leapSecondsFile, boolean verbose) {
        this.version = version;
        this.sourceFiles = sourceFiles;
        this.leapSecondsFile = leapSecondsFile;
        this.verbose = verbose;
    }

    public void compile() throws Exception {
        this.printVerbose("Compiling TZDB version " + this.version);
        this.parseFiles();
        this.parseLeapSecondsFile();
        this.buildZoneRules();
        this.printVerbose("Compiled TZDB version " + this.version);
    }

    public SortedMap<String, ZoneRules> getZones() {
        return this.builtZones;
    }

    public SortedMap<LocalDate, Byte> getLeapSeconds() {
        return this.leapSeconds;
    }

    private LocalDate getMostRecentLeapSecond() {
        return this.leapSeconds.isEmpty() ? null : this.leapSeconds.lastKey();
    }

    void setDeduplicateMap(Map<Object, Object> deduplicateMap) {
        this.deduplicateMap = deduplicateMap;
    }

    private void parseFiles() throws Exception {
        for (File file : this.sourceFiles) {
            this.printVerbose("Parsing file: " + file);
            this.parseFile(file);
        }
    }

    private void parseLeapSecondsFile() throws Exception {
        this.printVerbose("Parsing leap second file: " + this.leapSecondsFile);
        int lineNumber = 1;
        String line = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(this.leapSecondsFile));
            while ((line = in.readLine()) != null) {
                int index = line.indexOf(35);
                if (index >= 0) {
                    line = line.substring(0, index);
                }
                if (line.trim().length() != 0) {
                    LeapSecondRule secondRule = this.parseLeapSecondRule(line);
                    this.leapSeconds.put(secondRule.leapDate, secondRule.secondAdjustment);
                }
                ++lineNumber;
            }
        }
        catch (Exception ex) {
            throw new Exception("Failed while processing file '" + this.leapSecondsFile + "' on line " + lineNumber + " '" + line + "'", ex);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (Exception ex) {}
        }
    }

    private LeapSecondRule parseLeapSecondRule(String line) {
        StringTokenizer st = new StringTokenizer(line, " \t");
        String first = st.nextToken();
        if (first.equals("Leap")) {
            if (st.countTokens() < 6) {
                this.printVerbose("Invalid leap second line in file: " + this.leapSecondsFile + ", line: " + line);
                throw new IllegalArgumentException("Invalid leap second line");
            }
        } else {
            throw new IllegalArgumentException("Unknown line");
        }
        int year = Integer.parseInt(st.nextToken());
        Month month = this.parseMonth(st.nextToken());
        int dayOfMonth = Integer.parseInt(st.nextToken());
        LocalDate leapDate = LocalDate.of(year, month, dayOfMonth);
        String timeOfLeapSecond = st.nextToken();
        byte adjustmentByte = 0;
        String adjustment = st.nextToken();
        if (adjustment.equals("+")) {
            if (!"23:59:60".equals(timeOfLeapSecond)) {
                throw new IllegalArgumentException("Leap seconds can only be inserted at 23:59:60 - Date:" + leapDate);
            }
            adjustmentByte = 1;
        } else if (adjustment.equals("-")) {
            if (!"23:59:59".equals(timeOfLeapSecond)) {
                throw new IllegalArgumentException("Leap seconds can only be removed at 23:59:59 - Date:" + leapDate);
            }
            adjustmentByte = -1;
        } else {
            throw new IllegalArgumentException("Invalid adjustment '" + adjustment + "' in leap second rule for " + leapDate);
        }
        String rollingOrStationary = st.nextToken();
        if (!"S".equalsIgnoreCase(rollingOrStationary)) {
            throw new IllegalArgumentException("Only stationary ('S') leap seconds are supported, not '" + rollingOrStationary + "'");
        }
        return new LeapSecondRule(leapDate, adjustmentByte);
    }

    private void parseFile(File file) throws Exception {
        int lineNumber = 1;
        String line = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            ArrayList<TZDBZone> openZone = null;
            while ((line = in.readLine()) != null) {
                int index = line.indexOf(35);
                if (index >= 0) {
                    line = line.substring(0, index);
                }
                if (line.trim().length() != 0) {
                    StringTokenizer st = new StringTokenizer(line, " \t");
                    if (openZone != null && Character.isWhitespace(line.charAt(0)) && st.hasMoreTokens()) {
                        if (this.parseZoneLine(st, openZone)) {
                            openZone = null;
                        }
                    } else if (st.hasMoreTokens()) {
                        String first = st.nextToken();
                        if (first.equals("Zone")) {
                            if (st.countTokens() < 3) {
                                this.printVerbose("Invalid Zone line in file: " + file + ", line: " + line);
                                throw new IllegalArgumentException("Invalid Zone line");
                            }
                            openZone = new ArrayList<TZDBZone>();
                            this.zones.put(st.nextToken(), openZone);
                            if (this.parseZoneLine(st, openZone)) {
                                openZone = null;
                            }
                        } else {
                            openZone = null;
                            if (first.equals("Rule")) {
                                if (st.countTokens() < 9) {
                                    this.printVerbose("Invalid Rule line in file: " + file + ", line: " + line);
                                    throw new IllegalArgumentException("Invalid Rule line");
                                }
                                this.parseRuleLine(st);
                            } else if (first.equals("Link")) {
                                if (st.countTokens() < 2) {
                                    this.printVerbose("Invalid Link line in file: " + file + ", line: " + line);
                                    throw new IllegalArgumentException("Invalid Link line");
                                }
                                String realId = st.nextToken();
                                String aliasId = st.nextToken();
                                this.links.put(aliasId, realId);
                            } else {
                                throw new IllegalArgumentException("Unknown line");
                            }
                        }
                    }
                }
                ++lineNumber;
            }
        }
        catch (Exception ex) {
            throw new Exception("Failed while processing file '" + file + "' on line " + lineNumber + " '" + line + "'", ex);
        }
        finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private void parseRuleLine(StringTokenizer st) {
        TZDBRule rule = new TZDBRule();
        String name = st.nextToken();
        if (!this.rules.containsKey(name)) {
            this.rules.put(name, new ArrayList());
        }
        this.rules.get(name).add(rule);
        rule.startYear = this.parseYear(st.nextToken(), 0);
        rule.endYear = this.parseYear(st.nextToken(), rule.startYear);
        if (rule.startYear > rule.endYear) {
            throw new IllegalArgumentException("Year order invalid: " + rule.startYear + " > " + rule.endYear);
        }
        this.parseOptional(st.nextToken());
        this.parseMonthDayTime(st, rule);
        rule.savingsAmount = this.parsePeriod(st.nextToken());
        rule.text = this.parseOptional(st.nextToken());
    }

    private boolean parseZoneLine(StringTokenizer st, List<TZDBZone> zoneList) {
        TZDBZone zone = new TZDBZone();
        zoneList.add(zone);
        zone.standardOffset = this.parseOffset(st.nextToken());
        String savingsRule = this.parseOptional(st.nextToken());
        if (savingsRule == null) {
            zone.fixedSavingsSecs = 0;
            zone.savingsRule = null;
        } else {
            try {
                zone.fixedSavingsSecs = this.parsePeriod(savingsRule);
                zone.savingsRule = null;
            }
            catch (Exception ex) {
                zone.fixedSavingsSecs = null;
                zone.savingsRule = savingsRule;
            }
        }
        zone.text = st.nextToken();
        if (st.hasMoreTokens()) {
            zone.year = Year.of(Integer.parseInt(st.nextToken()));
            if (st.hasMoreTokens()) {
                this.parseMonthDayTime(st, zone);
            }
            return false;
        }
        return true;
    }

    private void parseMonthDayTime(StringTokenizer st, TZDBMonthDayTime mdt) {
        mdt.month = this.parseMonth(st.nextToken());
        if (st.hasMoreTokens()) {
            String dayRule = st.nextToken();
            if (dayRule.startsWith("last")) {
                mdt.dayOfMonth = -1;
                mdt.dayOfWeek = this.parseDayOfWeek(dayRule.substring(4));
                mdt.adjustForwards = false;
            } else {
                int index = dayRule.indexOf(">=");
                if (index > 0) {
                    mdt.dayOfWeek = this.parseDayOfWeek(dayRule.substring(0, index));
                    dayRule = dayRule.substring(index + 2);
                } else {
                    index = dayRule.indexOf("<=");
                    if (index > 0) {
                        mdt.dayOfWeek = this.parseDayOfWeek(dayRule.substring(0, index));
                        mdt.adjustForwards = false;
                        dayRule = dayRule.substring(index + 2);
                    }
                }
                mdt.dayOfMonth = Integer.parseInt(dayRule);
            }
            if (st.hasMoreTokens()) {
                LocalTime time;
                String timeStr = st.nextToken();
                int secsOfDay = this.parseSecs(timeStr);
                if (secsOfDay == 86400) {
                    mdt.endOfDay = true;
                    secsOfDay = 0;
                }
                mdt.time = time = this.deduplicate(LocalTime.ofSecondOfDay(secsOfDay));
                mdt.timeDefinition = this.parseTimeDefinition(timeStr.charAt(timeStr.length() - 1));
            }
        }
    }

    private int parseYear(String str, int defaultYear) {
        if (this.matches(str = str.toLowerCase(), "minimum")) {
            return -999999999;
        }
        if (this.matches(str, "maximum")) {
            return 999999999;
        }
        if (str.equals("only")) {
            return defaultYear;
        }
        return Integer.parseInt(str);
    }

    private Month parseMonth(String str) {
        str = str.toLowerCase();
        for (Month moy : Month.values()) {
            if (!this.matches(str, moy.name().toLowerCase())) continue;
            return moy;
        }
        throw new IllegalArgumentException("Unknown month: " + str);
    }

    private DayOfWeek parseDayOfWeek(String str) {
        str = str.toLowerCase();
        for (DayOfWeek dow : DayOfWeek.values()) {
            if (!this.matches(str, dow.name().toLowerCase())) continue;
            return dow;
        }
        throw new IllegalArgumentException("Unknown day-of-week: " + str);
    }

    private boolean matches(String str, String search) {
        return str.startsWith(search.substring(0, 3)) && search.startsWith(str) && str.length() <= search.length();
    }

    private String parseOptional(String str) {
        return str.equals("-") ? null : str;
    }

    private int parseSecs(String str) {
        ParsePosition pp;
        TemporalAccessor parsed;
        if (str.equals("-")) {
            return 0;
        }
        int pos = 0;
        if (str.startsWith("-")) {
            pos = 1;
        }
        if ((parsed = TIME_PARSER.parseUnresolved(str, pp = new ParsePosition(pos))) == null || pp.getErrorIndex() >= 0) {
            throw new IllegalArgumentException(str);
        }
        long hour = parsed.getLong(ChronoField.HOUR_OF_DAY);
        Long min2 = parsed.isSupported(ChronoField.MINUTE_OF_HOUR) ? Long.valueOf(parsed.getLong(ChronoField.MINUTE_OF_HOUR)) : null;
        Long sec = parsed.isSupported(ChronoField.SECOND_OF_MINUTE) ? Long.valueOf(parsed.getLong(ChronoField.SECOND_OF_MINUTE)) : null;
        int secs = (int)(hour * 60L * 60L + (min2 != null ? min2 : 0L) * 60L + (sec != null ? sec : 0L));
        if (pos == 1) {
            secs = -secs;
        }
        return secs;
    }

    private ZoneOffset parseOffset(String str) {
        int secs = this.parseSecs(str);
        return ZoneOffset.ofTotalSeconds(secs);
    }

    private int parsePeriod(String str) {
        return this.parseSecs(str);
    }

    private ZoneOffsetTransitionRule.TimeDefinition parseTimeDefinition(char c) {
        switch (c) {
            case 'S': 
            case 's': {
                return ZoneOffsetTransitionRule.TimeDefinition.STANDARD;
            }
            case 'G': 
            case 'U': 
            case 'Z': 
            case 'g': 
            case 'u': 
            case 'z': {
                return ZoneOffsetTransitionRule.TimeDefinition.UTC;
            }
        }
        return ZoneOffsetTransitionRule.TimeDefinition.WALL;
    }

    private void buildZoneRules() throws Exception {
        for (String zoneId : this.zones.keySet()) {
            this.printVerbose("Building zone " + zoneId);
            zoneId = this.deduplicate(zoneId);
            List<TZDBZone> tzdbZones = this.zones.get(zoneId);
            ZoneRulesBuilder bld = new ZoneRulesBuilder();
            for (TZDBZone tzdbZone : tzdbZones) {
                bld = tzdbZone.addToBuilder(bld, this.rules);
            }
            ZoneRules buildRules = bld.toRules(zoneId, this.deduplicateMap);
            this.builtZones.put(zoneId, this.deduplicate(buildRules));
        }
        for (String aliasId : this.links.keySet()) {
            aliasId = this.deduplicate(aliasId);
            String realId = this.links.get(aliasId);
            this.printVerbose("Linking alias " + aliasId + " to " + realId);
            ZoneRules realRules = (ZoneRules)this.builtZones.get(realId);
            if (realRules == null) {
                realId = this.links.get(realId);
                this.printVerbose("Relinking alias " + aliasId + " to " + realId);
                realRules = (ZoneRules)this.builtZones.get(realId);
                if (realRules == null) {
                    throw new IllegalArgumentException("Alias '" + aliasId + "' links to invalid zone '" + realId + "' for '" + this.version + "'");
                }
            }
            this.builtZones.put(aliasId, realRules);
        }
        this.builtZones.remove("UTC");
        this.builtZones.remove("GMT");
        this.builtZones.remove("GMT0");
        this.builtZones.remove("GMT+0");
        this.builtZones.remove("GMT-0");
    }

    <T> T deduplicate(T object) {
        if (!this.deduplicateMap.containsKey(object)) {
            this.deduplicateMap.put(object, object);
        }
        return (T)this.deduplicateMap.get(object);
    }

    private void printVerbose(String message) {
        if (this.verbose) {
            System.out.println(message);
        }
    }

    static final class LeapSecondRule {
        final LocalDate leapDate;
        byte secondAdjustment;

        public LeapSecondRule(LocalDate leapDate, byte secondAdjustment) {
            this.leapDate = leapDate;
            this.secondAdjustment = secondAdjustment;
        }
    }

    final class TZDBZone
    extends TZDBMonthDayTime {
        ZoneOffset standardOffset;
        Integer fixedSavingsSecs;
        String savingsRule;
        String text;
        Year year;

        TZDBZone() {
        }

        ZoneRulesBuilder addToBuilder(ZoneRulesBuilder bld, Map<String, List<TZDBRule>> rules) {
            if (this.year != null) {
                bld.addWindow(this.standardOffset, this.toDateTime(this.year.getValue()), this.timeDefinition);
            } else {
                bld.addWindowForever(this.standardOffset);
            }
            if (this.fixedSavingsSecs != null) {
                bld.setFixedSavingsToWindow(this.fixedSavingsSecs);
            } else {
                List<TZDBRule> tzdbRules = rules.get(this.savingsRule);
                if (tzdbRules == null) {
                    throw new IllegalArgumentException("Rule not found: " + this.savingsRule);
                }
                for (TZDBRule tzdbRule : tzdbRules) {
                    tzdbRule.addToBuilder(bld);
                }
            }
            return bld;
        }

        private LocalDateTime toDateTime(int year) {
            LocalDate date;
            this.adjustToFowards(year);
            if (this.dayOfMonth == -1) {
                this.dayOfMonth = this.month.length(Year.isLeap(year));
                date = LocalDate.of(year, this.month, this.dayOfMonth);
                if (this.dayOfWeek != null) {
                    date = date.with(TemporalAdjusters.previousOrSame(this.dayOfWeek));
                }
            } else {
                date = LocalDate.of(year, this.month, this.dayOfMonth);
                if (this.dayOfWeek != null) {
                    date = date.with(TemporalAdjusters.nextOrSame(this.dayOfWeek));
                }
            }
            date = TzdbZoneRulesCompiler.this.deduplicate(date);
            LocalDateTime ldt = LocalDateTime.of(date, this.time);
            if (this.endOfDay) {
                ldt = ldt.plusDays(1L);
            }
            return ldt;
        }
    }

    final class TZDBRule
    extends TZDBMonthDayTime {
        int startYear;
        int endYear;
        int savingsAmount;
        String text;

        TZDBRule() {
        }

        void addToBuilder(ZoneRulesBuilder bld) {
            this.adjustToFowards(2004);
            bld.addRuleToWindow(this.startYear, this.endYear, this.month, this.dayOfMonth, this.dayOfWeek, this.time, this.endOfDay, this.timeDefinition, this.savingsAmount);
        }
    }

    abstract class TZDBMonthDayTime {
        Month month = Month.JANUARY;
        int dayOfMonth = 1;
        boolean adjustForwards = true;
        DayOfWeek dayOfWeek;
        LocalTime time = LocalTime.MIDNIGHT;
        boolean endOfDay;
        ZoneOffsetTransitionRule.TimeDefinition timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.WALL;

        TZDBMonthDayTime() {
        }

        void adjustToFowards(int year) {
            if (!this.adjustForwards && this.dayOfMonth > 0) {
                LocalDate adjustedDate = LocalDate.of(year, this.month, this.dayOfMonth).minusDays(6L);
                this.dayOfMonth = adjustedDate.getDayOfMonth();
                this.month = adjustedDate.getMonth();
                this.adjustForwards = true;
            }
        }
    }
}

