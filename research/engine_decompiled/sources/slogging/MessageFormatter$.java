/*
 * Decompiled with CFR 0.152.
 */
package slogging;

import slogging.MessageFormatter;

/*
 * Illegal identifiers - consider using --renameillegalidents true
 */
public final class MessageFormatter$ {
    public static final MessageFormatter$ MODULE$;
    private MessageFormatter default;
    private volatile boolean bitmap$0;

    static {
        new MessageFormatter$();
    }

    private MessageFormatter default$lzycompute() {
        MessageFormatter$ messageFormatter$ = this;
        synchronized (messageFormatter$) {
            if (!this.bitmap$0) {
                this.default = new MessageFormatter.DefaultPrefixFormatter(true, true, false);
                this.bitmap$0 = true;
            }
            return this.default;
        }
    }

    public MessageFormatter default() {
        return this.bitmap$0 ? this.default : this.default$lzycompute();
    }

    private MessageFormatter$() {
        MODULE$ = this;
    }
}

