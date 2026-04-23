/*
 * Decompiled with CFR 0.152.
 */
package io.grpc.internal;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.JsonParser;
import io.grpc.internal.PairSocketAddress;
import io.grpc.internal.ProxyDetector;
import io.grpc.internal.ProxyParameters;
import io.grpc.internal.ServiceConfigUtil;
import io.grpc.internal.SharedResourceHolder;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.InitialDirContext;

final class DnsNameResolver
extends NameResolver {
    private static final Logger logger = Logger.getLogger(DnsNameResolver.class.getName());
    private static final boolean JNDI_AVAILABLE = DnsNameResolver.jndiAvailable();
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY = "clientLanguage";
    private static final String SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY = "percentage";
    private static final String SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY = "clientHostname";
    private static final String SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY = "serviceConfig";
    static final String SERVICE_CONFIG_PREFIX = "_grpc_config=";
    private static final Set<String> SERVICE_CONFIG_CHOICE_KEYS = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList("clientLanguage", "percentage", "clientHostname", "serviceConfig")));
    private static final String SERVICE_CONFIG_NAME_PREFIX = "_grpc_config.";
    private static final String GRPCLB_NAME_PREFIX = "_grpclb._tcp.";
    private static final String JNDI_PROPERTY = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", "false");
    @VisibleForTesting
    static boolean enableJndi = Boolean.parseBoolean(JNDI_PROPERTY);
    @VisibleForTesting
    final ProxyDetector proxyDetector;
    private static String localHostname;
    private final Random random = new Random();
    private DelegateResolver delegateResolver = this.pickDelegateResolver();
    private final String authority;
    private final String host;
    private final int port;
    private final SharedResourceHolder.Resource<ExecutorService> executorResource;
    @GuardedBy(value="this")
    private boolean shutdown;
    @GuardedBy(value="this")
    private ExecutorService executor;
    @GuardedBy(value="this")
    private boolean resolving;
    @GuardedBy(value="this")
    private NameResolver.Listener listener;
    private final Runnable resolutionRunnable = new Runnable(){

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            NameResolver.Listener savedListener;
            DnsNameResolver dnsNameResolver = DnsNameResolver.this;
            synchronized (dnsNameResolver) {
                if (DnsNameResolver.this.shutdown) {
                    return;
                }
                savedListener = DnsNameResolver.this.listener;
                DnsNameResolver.this.resolving = true;
            }
            try {
                ResolutionResults resolvedInetAddrs;
                ProxyParameters proxy;
                InetSocketAddress destination = InetSocketAddress.createUnresolved(DnsNameResolver.this.host, DnsNameResolver.this.port);
                try {
                    proxy = DnsNameResolver.this.proxyDetector.proxyFor(destination);
                }
                catch (IOException e) {
                    savedListener.onError(Status.UNAVAILABLE.withDescription("Unable to resolve host " + DnsNameResolver.this.host).withCause(e));
                    DnsNameResolver dnsNameResolver2 = DnsNameResolver.this;
                    synchronized (dnsNameResolver2) {
                        DnsNameResolver.this.resolving = false;
                    }
                    return;
                }
                if (proxy != null) {
                    EquivalentAddressGroup server = new EquivalentAddressGroup(new PairSocketAddress(destination, Attributes.newBuilder().set(ProxyDetector.PROXY_PARAMS_KEY, proxy).build()));
                    savedListener.onAddresses(Collections.singletonList(server), Attributes.EMPTY);
                    return;
                }
                try {
                    resolvedInetAddrs = DnsNameResolver.this.delegateResolver.resolve(DnsNameResolver.this.host);
                }
                catch (Exception e) {
                    savedListener.onError(Status.UNAVAILABLE.withDescription("Unable to resolve host " + DnsNameResolver.this.host).withCause(e));
                    DnsNameResolver dnsNameResolver3 = DnsNameResolver.this;
                    synchronized (dnsNameResolver3) {
                        DnsNameResolver.this.resolving = false;
                    }
                    return;
                }
                ArrayList<EquivalentAddressGroup> servers = new ArrayList<EquivalentAddressGroup>();
                for (InetAddress inetAddr : resolvedInetAddrs.addresses) {
                    servers.add(new EquivalentAddressGroup(new InetSocketAddress(inetAddr, DnsNameResolver.this.port)));
                }
                servers.addAll(resolvedInetAddrs.balancerAddresses);
                Attributes.Builder attrs = Attributes.newBuilder();
                if (!resolvedInetAddrs.txtRecords.isEmpty()) {
                    Map<String, Object> serviceConfig = null;
                    try {
                        for (Map<String, Object> possibleConfig : DnsNameResolver.parseTxtResults(resolvedInetAddrs.txtRecords)) {
                            try {
                                serviceConfig = DnsNameResolver.maybeChooseServiceConfig(possibleConfig, DnsNameResolver.this.random, DnsNameResolver.getLocalHostname());
                            }
                            catch (RuntimeException e) {
                                logger.log(Level.WARNING, "Bad service config choice " + possibleConfig, e);
                            }
                            if (serviceConfig == null) continue;
                            break;
                        }
                    }
                    catch (RuntimeException e) {
                        logger.log(Level.WARNING, "Can't parse service Configs", e);
                    }
                    if (serviceConfig != null) {
                        attrs.set(GrpcAttributes.NAME_RESOLVER_SERVICE_CONFIG, serviceConfig);
                    }
                } else {
                    logger.log(Level.FINE, "No TXT records found for {0}", new Object[]{DnsNameResolver.this.host});
                }
                savedListener.onAddresses(servers, attrs.build());
            }
            finally {
                DnsNameResolver dnsNameResolver4 = DnsNameResolver.this;
                synchronized (dnsNameResolver4) {
                    DnsNameResolver.this.resolving = false;
                }
            }
        }
    };

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    DnsNameResolver(@Nullable String nsAuthority, String name, Attributes params2, SharedResourceHolder.Resource<ExecutorService> executorResource, ProxyDetector proxyDetector) {
        this.executorResource = executorResource;
        URI nameUri = URI.create("//" + name);
        this.authority = Preconditions.checkNotNull(nameUri.getAuthority(), "nameUri (%s) doesn't have an authority", (Object)nameUri);
        this.host = Preconditions.checkNotNull(nameUri.getHost(), "host");
        if (nameUri.getPort() == -1) {
            Integer defaultPort = params2.get(NameResolver.Factory.PARAMS_DEFAULT_PORT);
            if (defaultPort == null) throw new IllegalArgumentException("name '" + name + "' doesn't contain a port, and default port is not set in params");
            this.port = defaultPort;
        } else {
            this.port = nameUri.getPort();
        }
        this.proxyDetector = proxyDetector;
    }

    @Override
    public final String getServiceAuthority() {
        return this.authority;
    }

    @Override
    public final synchronized void start(NameResolver.Listener listener) {
        Preconditions.checkState(this.listener == null, "already started");
        this.executor = SharedResourceHolder.get(this.executorResource);
        this.listener = Preconditions.checkNotNull(listener, "listener");
        this.resolve();
    }

    @Override
    public final synchronized void refresh() {
        Preconditions.checkState(this.listener != null, "not started");
        this.resolve();
    }

    @GuardedBy(value="this")
    private void resolve() {
        if (this.resolving || this.shutdown) {
            return;
        }
        this.executor.execute(this.resolutionRunnable);
    }

    @Override
    public final synchronized void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        if (this.executor != null) {
            this.executor = SharedResourceHolder.release(this.executorResource, this.executor);
        }
    }

    final int getPort() {
        return this.port;
    }

    private DelegateResolver pickDelegateResolver() {
        JdkResolver jdkResolver = new JdkResolver();
        if (JNDI_AVAILABLE && enableJndi) {
            return new CompositeResolver(jdkResolver, new JndiResolver());
        }
        return jdkResolver;
    }

    @VisibleForTesting
    void setDelegateResolver(DelegateResolver delegateResolver) {
        this.delegateResolver = delegateResolver;
    }

    @VisibleForTesting
    static List<Map<String, Object>> parseTxtResults(List<String> txtRecords) {
        ArrayList<Map<String, Object>> serviceConfigs = new ArrayList<Map<String, Object>>();
        for (String txtRecord : txtRecords) {
            if (txtRecord.startsWith(SERVICE_CONFIG_PREFIX)) {
                List choices;
                try {
                    Object rawChoices = JsonParser.parse(txtRecord.substring(SERVICE_CONFIG_PREFIX.length()));
                    if (!(rawChoices instanceof List)) {
                        throw new IOException("wrong type " + rawChoices);
                    }
                    List listChoices = (List)rawChoices;
                    for (Object obj : listChoices) {
                        if (obj instanceof Map) continue;
                        throw new IOException("wrong element type " + rawChoices);
                    }
                    choices = listChoices;
                }
                catch (IOException e) {
                    logger.log(Level.WARNING, "Bad service config: " + txtRecord, e);
                    continue;
                }
                serviceConfigs.addAll(choices);
                continue;
            }
            logger.log(Level.FINE, "Ignoring non service config {0}", new Object[]{txtRecord});
        }
        return serviceConfigs;
    }

    @Nullable
    private static final Double getPercentageFromChoice(Map<String, Object> serviceConfigChoice) {
        if (!serviceConfigChoice.containsKey(SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY)) {
            return null;
        }
        return ServiceConfigUtil.getDouble(serviceConfigChoice, SERVICE_CONFIG_CHOICE_PERCENTAGE_KEY);
    }

    @Nullable
    private static final List<String> getClientLanguagesFromChoice(Map<String, Object> serviceConfigChoice) {
        if (!serviceConfigChoice.containsKey(SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY)) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_LANGUAGE_KEY));
    }

    @Nullable
    private static final List<String> getHostnamesFromChoice(Map<String, Object> serviceConfigChoice) {
        if (!serviceConfigChoice.containsKey(SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY)) {
            return null;
        }
        return ServiceConfigUtil.checkStringList(ServiceConfigUtil.getList(serviceConfigChoice, SERVICE_CONFIG_CHOICE_CLIENT_HOSTNAME_KEY));
    }

    @Nullable
    @VisibleForTesting
    static Map<String, Object> maybeChooseServiceConfig(Map<String, Object> choice, Random random, String hostname) {
        List<String> clientHostnames;
        Double percentage;
        for (Map.Entry<String, Object> entry : choice.entrySet()) {
            Verify.verify(SERVICE_CONFIG_CHOICE_KEYS.contains(entry.getKey()), "Bad key: %s", entry);
        }
        List<String> clientLanguages = DnsNameResolver.getClientLanguagesFromChoice(choice);
        if (clientLanguages != null && !clientLanguages.isEmpty()) {
            boolean javaPresent = false;
            for (String lang : clientLanguages) {
                if (!"java".equalsIgnoreCase(lang)) continue;
                javaPresent = true;
                break;
            }
            if (!javaPresent) {
                return null;
            }
        }
        if ((percentage = DnsNameResolver.getPercentageFromChoice(choice)) != null) {
            int pct = percentage.intValue();
            Verify.verify(pct >= 0 && pct <= 100, "Bad percentage: %s", percentage);
            if (random.nextInt(100) >= pct) {
                return null;
            }
        }
        if ((clientHostnames = DnsNameResolver.getHostnamesFromChoice(choice)) != null && !clientHostnames.isEmpty()) {
            boolean hostnamePresent = false;
            for (String clientHostname : clientHostnames) {
                if (!clientHostname.equals(hostname)) continue;
                hostnamePresent = true;
                break;
            }
            if (!hostnamePresent) {
                return null;
            }
        }
        return ServiceConfigUtil.getObject(choice, SERVICE_CONFIG_CHOICE_SERVICE_CONFIG_KEY);
    }

    @VisibleForTesting
    static boolean jndiAvailable() {
        if (GrpcUtil.IS_RESTRICTED_APPENGINE) {
            return false;
        }
        try {
            Class.forName("javax.naming.directory.InitialDirContext");
            Class.forName("com.sun.jndi.dns.DnsContextFactory");
        }
        catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find JNDI DNS resolver, skipping", e);
            return false;
        }
        return true;
    }

    @VisibleForTesting
    static String unquote(String txtRecord) {
        StringBuilder sb = new StringBuilder(txtRecord.length());
        boolean inquote = false;
        for (int i = 0; i < txtRecord.length(); ++i) {
            char c = txtRecord.charAt(i);
            if (!inquote) {
                if (c == ' ') continue;
                if (c == '\"') {
                    inquote = true;
                    continue;
                }
            } else {
                if (c == '\"') {
                    inquote = false;
                    continue;
                }
                if (c == '\\') {
                    c = txtRecord.charAt(++i);
                    assert (c == '\"' || c == '\\');
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String getLocalHostname() {
        if (localHostname == null) {
            try {
                localHostname = InetAddress.getLocalHost().getHostName();
            }
            catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
        return localHostname;
    }

    @VisibleForTesting
    static final class JndiResolver
    extends DelegateResolver {
        private static final Pattern whitespace = Pattern.compile("\\s+");

        JndiResolver() {
        }

        @Override
        ResolutionResults resolve(String host) throws NamingException {
            List balancerAddresses;
            List<String> serviceConfigTxtRecords;
            block12: {
                String serviceConfigHostname;
                block11: {
                    serviceConfigTxtRecords = Collections.emptyList();
                    serviceConfigHostname = DnsNameResolver.SERVICE_CONFIG_NAME_PREFIX + host;
                    if (logger.isLoggable(Level.FINER)) {
                        logger.log(Level.FINER, "About to query TXT records for {0}", new Object[]{serviceConfigHostname});
                    }
                    try {
                        serviceConfigTxtRecords = this.getAllRecords("TXT", "dns:///" + serviceConfigHostname);
                    }
                    catch (NamingException e) {
                        if (!logger.isLoggable(Level.FINE)) break block11;
                        logger.log(Level.FINE, "Unable to look up " + serviceConfigHostname, e);
                    }
                }
                String grpclbHostname = DnsNameResolver.GRPCLB_NAME_PREFIX + host;
                if (logger.isLoggable(Level.FINER)) {
                    logger.log(Level.FINER, "About to query SRV records for {0}", new Object[]{grpclbHostname});
                }
                balancerAddresses = Collections.emptyList();
                try {
                    List<String> grpclbSrvRecords = this.getAllRecords("SRV", "dns:///" + grpclbHostname);
                    balancerAddresses = new ArrayList(grpclbSrvRecords.size());
                    for (String srvRecord : grpclbSrvRecords) {
                        try {
                            String[] parts = whitespace.split(srvRecord);
                            Verify.verify(parts.length == 4, "Bad SRV Record: %s, ", srvRecord);
                            String srvHostname = parts[3];
                            int port = Integer.parseInt(parts[2]);
                            InetAddress[] addrs = InetAddress.getAllByName(srvHostname);
                            ArrayList<InetSocketAddress> sockaddrs = new ArrayList<InetSocketAddress>(addrs.length);
                            for (InetAddress addr : addrs) {
                                sockaddrs.add(new InetSocketAddress(addr, port));
                            }
                            Attributes attrs = Attributes.newBuilder().set(GrpcAttributes.ATTR_LB_ADDR_AUTHORITY, srvHostname).build();
                            balancerAddresses.add(new EquivalentAddressGroup(Collections.unmodifiableList(sockaddrs), attrs));
                        }
                        catch (UnknownHostException e) {
                            logger.log(Level.WARNING, "Can't find address for SRV record" + srvRecord, e);
                        }
                        catch (RuntimeException e) {
                            logger.log(Level.WARNING, "Failed to construct SRV record" + srvRecord, e);
                        }
                    }
                }
                catch (NamingException e) {
                    if (!logger.isLoggable(Level.FINE)) break block12;
                    logger.log(Level.FINE, "Unable to look up " + serviceConfigHostname, e);
                }
            }
            return new ResolutionResults(Collections.<InetAddress>emptyList(), serviceConfigTxtRecords, Collections.unmodifiableList(balancerAddresses));
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private List<String> getAllRecords(String recordType, String name) throws NamingException {
            InitialDirContext dirContext = new InitialDirContext();
            String[] rrType = new String[]{recordType};
            javax.naming.directory.Attributes attrs = dirContext.getAttributes(name, rrType);
            ArrayList<String> records = new ArrayList<String>();
            NamingEnumeration<? extends Attribute> rrGroups = attrs.getAll();
            try {
                while (rrGroups.hasMore()) {
                    Attribute rrEntry = rrGroups.next();
                    assert (Arrays.asList(rrType).contains(rrEntry.getID()));
                    NamingEnumeration<?> rrValues = rrEntry.getAll();
                    try {
                        while (rrValues.hasMore()) {
                            records.add(DnsNameResolver.unquote(String.valueOf(rrValues.next())));
                        }
                    }
                    finally {
                        rrValues.close();
                    }
                }
            }
            finally {
                rrGroups.close();
            }
            return records;
        }
    }

    @VisibleForTesting
    static final class JdkResolver
    extends DelegateResolver {
        JdkResolver() {
        }

        @Override
        ResolutionResults resolve(String host) throws Exception {
            return new ResolutionResults(Arrays.asList(InetAddress.getAllByName(host)), Collections.<String>emptyList(), Collections.<EquivalentAddressGroup>emptyList());
        }
    }

    @VisibleForTesting
    static final class CompositeResolver
    extends DelegateResolver {
        private final DelegateResolver jdkResovler;
        private final DelegateResolver jndiResovler;

        CompositeResolver(DelegateResolver jdkResovler, DelegateResolver jndiResovler) {
            this.jdkResovler = jdkResovler;
            this.jndiResovler = jndiResovler;
        }

        @Override
        ResolutionResults resolve(String host) throws Exception {
            ResolutionResults jdkResults = this.jdkResovler.resolve(host);
            List<InetAddress> addresses = jdkResults.addresses;
            List<String> txtRecords = Collections.emptyList();
            List<EquivalentAddressGroup> balancerAddresses = Collections.emptyList();
            try {
                ResolutionResults jdniResults = this.jndiResovler.resolve(host);
                txtRecords = jdniResults.txtRecords;
                balancerAddresses = jdniResults.balancerAddresses;
            }
            catch (Throwable e) {
                logger.log(Level.SEVERE, "Failed to resolve TXT results", e);
            }
            return new ResolutionResults(addresses, txtRecords, balancerAddresses);
        }
    }

    @VisibleForTesting
    static final class ResolutionResults {
        final List<InetAddress> addresses;
        final List<String> txtRecords;
        final List<EquivalentAddressGroup> balancerAddresses;

        ResolutionResults(List<InetAddress> addresses, List<String> txtRecords, List<EquivalentAddressGroup> balancerAddresses) {
            this.addresses = Collections.unmodifiableList(Preconditions.checkNotNull(addresses, "addresses"));
            this.txtRecords = Collections.unmodifiableList(Preconditions.checkNotNull(txtRecords, "txtRecords"));
            this.balancerAddresses = Collections.unmodifiableList(Preconditions.checkNotNull(balancerAddresses, "balancerAddresses"));
        }
    }

    @VisibleForTesting
    static abstract class DelegateResolver {
        DelegateResolver() {
        }

        abstract ResolutionResults resolve(String var1) throws Exception;
    }
}

