/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject.components;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.events.DestroyEvent;
import com.c1games.terminal.game.events.EnableEvent;
import com.c1games.terminal.game.events.EventListener;
import com.c1games.terminal.game.events.ToDestroyEvent;
import com.c1games.terminal.game.gameobject.Gameobject;
import java.util.EventObject;
import java.util.function.Function;

public abstract class Component {
    private boolean localEnabled;
    private boolean globalEnabled;
    private boolean toDestroyed = false;
    private boolean destroyed = false;
    public Gameobject gameobject;
    public final Game game;

    public Component(Game game, Gameobject host, boolean autoAdd) {
        this.gameobject = host;
        this.game = game;
        if (autoAdd) {
            this.gameobject.addComponent(this);
        } else {
            this.game.addNewComponent(this);
        }
        this.gameobject.eventManager.registerListener(EnableEvent.class, this.enableListener());
        this.gameobject.eventManager.registerListener(ToDestroyEvent.class, this.toDestroyListener());
        this.gameobject.eventManager.registerListener(DestroyEvent.class, this.destroyListener());
    }

    public Component(Game game, Gameobject host, Function<Gameobject, Boolean> requirements, boolean autoAdd) {
        this(game, host, autoAdd);
        if (!requirements.apply(this.gameobject).booleanValue()) {
            throw new ExceptionInInitializerError();
        }
    }

    public Component(Game game, Gameobject host, Function<Gameobject, Boolean> requirements) {
        this(game, host, requirements, true);
    }

    public Component(Game game, Gameobject host) {
        this(game, host, true);
    }

    protected void initFinalizeCallEnable(boolean enabled) {
        this.globalEnabled = this.calculateGlobalEnabled();
        this.localEnabled = enabled;
        this.enableChanged();
    }

    protected final EventListener destroyListener() {
        return new EventListener(){

            @Override
            public void handleEvent(EventObject event) {
                Component.this.destroyOnlyThisComponent();
            }
        };
    }

    protected void onDestroy() {
    }

    public final void destroyOnlyThisComponent() {
        if (!this.destroyed) {
            this.destroyed = true;
            this.gameobject.removeComponent(this);
            this.onDestroy();
        }
    }

    public final void setToDestroy() {
        if (!this.toDestroyed) {
            this.game.toDestroyComponents.add(this);
            this.toDestroyed = true;
            this.onToDestroy();
        }
    }

    public final boolean isToDestroyed() {
        return this.toDestroyed;
    }

    protected final EventListener toDestroyListener() {
        return new EventListener(){

            @Override
            public void handleEvent(EventObject event) {
                if (!Component.this.toDestroyed) {
                    Component.this.toDestroyed = true;
                    Component.this.onToDestroy();
                }
            }
        };
    }

    protected void onToDestroy() {
    }

    protected final EventListener enableListener() {
        return new EventListener(){

            @Override
            public void handleEvent(EventObject event) {
                Component.this.calculateAndSetGlobalEnabled();
            }
        };
    }

    public final boolean isEnabled() {
        return this.localEnabled && this.globalEnabled;
    }

    private final void calculateAndSetGlobalEnabled() {
        boolean genabled = this.calculateGlobalEnabled();
        if (genabled != this.globalEnabled) {
            this.checkEnableChanged(this.localEnabled, genabled);
        }
    }

    private boolean calculateGlobalEnabled() {
        boolean genabled = true;
        for (Gameobject p = this.gameobject; p != null; p = p.getParent()) {
            if (p.isEnabled()) continue;
            genabled = false;
        }
        return genabled;
    }

    protected final boolean getGlobalEnabled() {
        return this.globalEnabled;
    }

    private final void checkEnableChanged(boolean newLocalEnabled, boolean newGlobalEnabled) {
        boolean changed = (newLocalEnabled && newGlobalEnabled) != (this.localEnabled && this.globalEnabled);
        this.localEnabled = newLocalEnabled;
        this.globalEnabled = newGlobalEnabled;
        if (changed) {
            this.enableChanged();
        }
    }

    protected void afterEnable() {
    }

    protected void afterDisable() {
    }

    private void enable() {
        this.afterEnable();
    }

    private void disable() {
        this.afterDisable();
    }

    protected void setEnabled(boolean enabled) {
        if (enabled != this.localEnabled) {
            this.checkEnableChanged(enabled, this.globalEnabled);
        }
    }

    private final void enableChanged() {
        if (this.isEnabled()) {
            this.enable();
        } else {
            this.disable();
        }
    }
}

