/*
 * Decompiled with CFR 0.152.
 */
package scala.collection.convert;

import scala.Serializable;
import scala.collection.convert.Wrappers;
import scala.collection.convert.Wrappers$DictionaryWrapper$;
import scala.collection.convert.Wrappers$IterableWrapper$;
import scala.collection.convert.Wrappers$IteratorWrapper$;
import scala.collection.convert.Wrappers$JCollectionWrapper$;
import scala.collection.convert.Wrappers$JConcurrentMapWrapper$;
import scala.collection.convert.Wrappers$JDictionaryWrapper$;
import scala.collection.convert.Wrappers$JEnumerationWrapper$;
import scala.collection.convert.Wrappers$JIterableWrapper$;
import scala.collection.convert.Wrappers$JIteratorWrapper$;
import scala.collection.convert.Wrappers$JListWrapper$;
import scala.collection.convert.Wrappers$JMapWrapper$;
import scala.collection.convert.Wrappers$JPropertiesWrapper$;
import scala.collection.convert.Wrappers$JSetWrapper$;
import scala.collection.convert.Wrappers$MutableBufferWrapper$;
import scala.collection.convert.Wrappers$MutableMapWrapper$;
import scala.collection.convert.Wrappers$MutableSeqWrapper$;
import scala.collection.convert.Wrappers$MutableSetWrapper$;
import scala.collection.convert.Wrappers$SeqWrapper$;
import scala.collection.convert.Wrappers$class;

public final class Wrappers$
implements Wrappers,
Serializable {
    public static final Wrappers$ MODULE$;
    public static final long serialVersionUID = -5857859809262781311L;
    private volatile Wrappers$IteratorWrapper$ IteratorWrapper$module;
    private volatile Wrappers$JIteratorWrapper$ JIteratorWrapper$module;
    private volatile Wrappers$JEnumerationWrapper$ JEnumerationWrapper$module;
    private volatile Wrappers$IterableWrapper$ IterableWrapper$module;
    private volatile Wrappers$JIterableWrapper$ JIterableWrapper$module;
    private volatile Wrappers$JCollectionWrapper$ JCollectionWrapper$module;
    private volatile Wrappers$SeqWrapper$ SeqWrapper$module;
    private volatile Wrappers$MutableSeqWrapper$ MutableSeqWrapper$module;
    private volatile Wrappers$MutableBufferWrapper$ MutableBufferWrapper$module;
    private volatile Wrappers$JListWrapper$ JListWrapper$module;
    private volatile Wrappers$MutableSetWrapper$ MutableSetWrapper$module;
    private volatile Wrappers$JSetWrapper$ JSetWrapper$module;
    private volatile Wrappers$MutableMapWrapper$ MutableMapWrapper$module;
    private volatile Wrappers$JMapWrapper$ JMapWrapper$module;
    private volatile Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper$module;
    private volatile Wrappers$DictionaryWrapper$ DictionaryWrapper$module;
    private volatile Wrappers$JDictionaryWrapper$ JDictionaryWrapper$module;
    private volatile Wrappers$JPropertiesWrapper$ JPropertiesWrapper$module;

    static {
        new Wrappers$();
    }

    private Wrappers$IteratorWrapper$ IteratorWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.IteratorWrapper$module == null) {
                this.IteratorWrapper$module = new Wrappers$IteratorWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.IteratorWrapper$module;
        }
    }

    @Override
    public Wrappers$IteratorWrapper$ IteratorWrapper() {
        return this.IteratorWrapper$module == null ? this.IteratorWrapper$lzycompute() : this.IteratorWrapper$module;
    }

    private Wrappers$JIteratorWrapper$ JIteratorWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JIteratorWrapper$module == null) {
                this.JIteratorWrapper$module = new Wrappers$JIteratorWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JIteratorWrapper$module;
        }
    }

    @Override
    public Wrappers$JIteratorWrapper$ JIteratorWrapper() {
        return this.JIteratorWrapper$module == null ? this.JIteratorWrapper$lzycompute() : this.JIteratorWrapper$module;
    }

    private Wrappers$JEnumerationWrapper$ JEnumerationWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JEnumerationWrapper$module == null) {
                this.JEnumerationWrapper$module = new Wrappers$JEnumerationWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JEnumerationWrapper$module;
        }
    }

    @Override
    public Wrappers$JEnumerationWrapper$ JEnumerationWrapper() {
        return this.JEnumerationWrapper$module == null ? this.JEnumerationWrapper$lzycompute() : this.JEnumerationWrapper$module;
    }

    private Wrappers$IterableWrapper$ IterableWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.IterableWrapper$module == null) {
                this.IterableWrapper$module = new Wrappers$IterableWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.IterableWrapper$module;
        }
    }

    @Override
    public Wrappers$IterableWrapper$ IterableWrapper() {
        return this.IterableWrapper$module == null ? this.IterableWrapper$lzycompute() : this.IterableWrapper$module;
    }

    private Wrappers$JIterableWrapper$ JIterableWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JIterableWrapper$module == null) {
                this.JIterableWrapper$module = new Wrappers$JIterableWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JIterableWrapper$module;
        }
    }

    @Override
    public Wrappers$JIterableWrapper$ JIterableWrapper() {
        return this.JIterableWrapper$module == null ? this.JIterableWrapper$lzycompute() : this.JIterableWrapper$module;
    }

    private Wrappers$JCollectionWrapper$ JCollectionWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JCollectionWrapper$module == null) {
                this.JCollectionWrapper$module = new Wrappers$JCollectionWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JCollectionWrapper$module;
        }
    }

    @Override
    public Wrappers$JCollectionWrapper$ JCollectionWrapper() {
        return this.JCollectionWrapper$module == null ? this.JCollectionWrapper$lzycompute() : this.JCollectionWrapper$module;
    }

    private Wrappers$SeqWrapper$ SeqWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.SeqWrapper$module == null) {
                this.SeqWrapper$module = new Wrappers$SeqWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.SeqWrapper$module;
        }
    }

    @Override
    public Wrappers$SeqWrapper$ SeqWrapper() {
        return this.SeqWrapper$module == null ? this.SeqWrapper$lzycompute() : this.SeqWrapper$module;
    }

    private Wrappers$MutableSeqWrapper$ MutableSeqWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.MutableSeqWrapper$module == null) {
                this.MutableSeqWrapper$module = new Wrappers$MutableSeqWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MutableSeqWrapper$module;
        }
    }

    @Override
    public Wrappers$MutableSeqWrapper$ MutableSeqWrapper() {
        return this.MutableSeqWrapper$module == null ? this.MutableSeqWrapper$lzycompute() : this.MutableSeqWrapper$module;
    }

    private Wrappers$MutableBufferWrapper$ MutableBufferWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.MutableBufferWrapper$module == null) {
                this.MutableBufferWrapper$module = new Wrappers$MutableBufferWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MutableBufferWrapper$module;
        }
    }

    @Override
    public Wrappers$MutableBufferWrapper$ MutableBufferWrapper() {
        return this.MutableBufferWrapper$module == null ? this.MutableBufferWrapper$lzycompute() : this.MutableBufferWrapper$module;
    }

    private Wrappers$JListWrapper$ JListWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JListWrapper$module == null) {
                this.JListWrapper$module = new Wrappers$JListWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JListWrapper$module;
        }
    }

    @Override
    public Wrappers$JListWrapper$ JListWrapper() {
        return this.JListWrapper$module == null ? this.JListWrapper$lzycompute() : this.JListWrapper$module;
    }

    private Wrappers$MutableSetWrapper$ MutableSetWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.MutableSetWrapper$module == null) {
                this.MutableSetWrapper$module = new Wrappers$MutableSetWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MutableSetWrapper$module;
        }
    }

    @Override
    public Wrappers$MutableSetWrapper$ MutableSetWrapper() {
        return this.MutableSetWrapper$module == null ? this.MutableSetWrapper$lzycompute() : this.MutableSetWrapper$module;
    }

    private Wrappers$JSetWrapper$ JSetWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JSetWrapper$module == null) {
                this.JSetWrapper$module = new Wrappers$JSetWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JSetWrapper$module;
        }
    }

    @Override
    public Wrappers$JSetWrapper$ JSetWrapper() {
        return this.JSetWrapper$module == null ? this.JSetWrapper$lzycompute() : this.JSetWrapper$module;
    }

    private Wrappers$MutableMapWrapper$ MutableMapWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.MutableMapWrapper$module == null) {
                this.MutableMapWrapper$module = new Wrappers$MutableMapWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.MutableMapWrapper$module;
        }
    }

    @Override
    public Wrappers$MutableMapWrapper$ MutableMapWrapper() {
        return this.MutableMapWrapper$module == null ? this.MutableMapWrapper$lzycompute() : this.MutableMapWrapper$module;
    }

    private Wrappers$JMapWrapper$ JMapWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JMapWrapper$module == null) {
                this.JMapWrapper$module = new Wrappers$JMapWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JMapWrapper$module;
        }
    }

    @Override
    public Wrappers$JMapWrapper$ JMapWrapper() {
        return this.JMapWrapper$module == null ? this.JMapWrapper$lzycompute() : this.JMapWrapper$module;
    }

    private Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JConcurrentMapWrapper$module == null) {
                this.JConcurrentMapWrapper$module = new Wrappers$JConcurrentMapWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JConcurrentMapWrapper$module;
        }
    }

    @Override
    public Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper() {
        return this.JConcurrentMapWrapper$module == null ? this.JConcurrentMapWrapper$lzycompute() : this.JConcurrentMapWrapper$module;
    }

    private Wrappers$DictionaryWrapper$ DictionaryWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.DictionaryWrapper$module == null) {
                this.DictionaryWrapper$module = new Wrappers$DictionaryWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.DictionaryWrapper$module;
        }
    }

    @Override
    public Wrappers$DictionaryWrapper$ DictionaryWrapper() {
        return this.DictionaryWrapper$module == null ? this.DictionaryWrapper$lzycompute() : this.DictionaryWrapper$module;
    }

    private Wrappers$JDictionaryWrapper$ JDictionaryWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JDictionaryWrapper$module == null) {
                this.JDictionaryWrapper$module = new Wrappers$JDictionaryWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JDictionaryWrapper$module;
        }
    }

    @Override
    public Wrappers$JDictionaryWrapper$ JDictionaryWrapper() {
        return this.JDictionaryWrapper$module == null ? this.JDictionaryWrapper$lzycompute() : this.JDictionaryWrapper$module;
    }

    private Wrappers$JPropertiesWrapper$ JPropertiesWrapper$lzycompute() {
        Wrappers$ wrappers$ = this;
        synchronized (wrappers$) {
            if (this.JPropertiesWrapper$module == null) {
                this.JPropertiesWrapper$module = new Wrappers$JPropertiesWrapper$(this);
            }
            // ** MonitorExit[this] (shouldn't be in output)
            return this.JPropertiesWrapper$module;
        }
    }

    @Override
    public Wrappers$JPropertiesWrapper$ JPropertiesWrapper() {
        return this.JPropertiesWrapper$module == null ? this.JPropertiesWrapper$lzycompute() : this.JPropertiesWrapper$module;
    }

    private Object readResolve() {
        return MODULE$;
    }

    private Wrappers$() {
        MODULE$ = this;
        Wrappers$class.$init$(this);
    }
}

