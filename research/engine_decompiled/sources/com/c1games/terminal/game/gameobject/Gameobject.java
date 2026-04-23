/*
 * Decompiled with CFR 0.152.
 */
package com.c1games.terminal.game.gameobject;

import com.c1games.terminal.game.Game;
import com.c1games.terminal.game.events.DestroyEvent;
import com.c1games.terminal.game.events.EnableEvent;
import com.c1games.terminal.game.events.EventListener;
import com.c1games.terminal.game.events.EventManager;
import com.c1games.terminal.game.events.ToDestroyEvent;
import com.c1games.terminal.game.gameobject.UniqueId;
import com.c1games.terminal.game.gameobject.components.ColliderComponent;
import com.c1games.terminal.game.gameobject.components.Component;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class Gameobject {
    UniqueId gid;
    public ArrayList<ColliderComponent> colliders;
    private boolean localEnabled;
    private boolean globalEnabled;
    private boolean toDestroyed = false;
    private boolean destroyed = false;
    Map<Class<?>, List<Component>> components;
    public EventManager eventManager;
    private Gameobject parent = null;
    ArrayList<Gameobject> children;
    Game game;

    public Gameobject(Game game) {
        this(game, null, true, false);
    }

    public Gameobject(Game game, boolean enabled) {
        this(game, null, enabled, false);
    }

    public Gameobject(Game game, Gameobject parent) {
        this(game, parent, true, false);
    }

    public Gameobject(Game game, Gameobject parent, boolean enabled, boolean autoAdd) {
        this.game = game;
        this.gid = new UniqueId(game);
        this.components = new HashMap();
        this.colliders = new ArrayList();
        this.eventManager = new EventManager(this);
        this.children = new ArrayList();
        this.setParent(parent);
        this.eventManager.registerListener(EnableEvent.class, this.enableListener());
        this.eventManager.registerListener(ToDestroyEvent.class, this.toDestroyListener());
        if (autoAdd) {
            game.gameObjects.add(this);
        } else {
            game.newGameObjects.add(this);
        }
        this.setEnabled(enabled);
    }

    public Gameobject getParent() {
        return this.parent;
    }

    public <T> T getComponent(Class<T> c) {
        if (Component.class.isAssignableFrom(c)) {
            List<Component> cList;
            if (this.components.containsKey(c) && (cList = this.components.get(c)).size() > 0) {
                return (T)cList.get(0);
            }
            for (Component component : this.game.getNewComponents(this)) {
                if (!c.isAssignableFrom(component.getClass())) continue;
                return (T)component;
            }
            for (Class clazz : this.components.keySet()) {
                List<Component> cList2;
                if (!c.isAssignableFrom(clazz) || (cList2 = this.components.get(clazz)).size() <= 0) continue;
                return (T)cList2.get(0);
            }
        }
        return null;
    }

    public <T> ArrayList<T> getComponents(Class<T> c) {
        ArrayList<Component> al = new ArrayList<Component>();
        if (Component.class.isAssignableFrom(c)) {
            for (Component component : this.game.getNewComponents(this)) {
                if (!c.isAssignableFrom(component.getClass())) continue;
                al.add(component);
            }
            for (Class clazz : this.components.keySet()) {
                if (!c.isAssignableFrom(clazz)) continue;
                al.addAll((ArrayList)this.components.get(clazz));
            }
            return al;
        }
        System.err.println("Trying to get component of not component class: " + String.valueOf(c));
        return al;
    }

    public void addComponent(Component comp) {
        if (this.components.containsKey(comp.getClass())) {
            this.components.get(comp.getClass()).add(comp);
        } else {
            this.components.put(comp.getClass(), new ArrayList());
            this.components.get(comp.getClass()).add(comp);
        }
    }

    public void removeComponent(Component comp) {
        Class<?> compClass = comp.getClass();
        if (this.components.containsKey(compClass)) {
            this.components.get(compClass).remove(comp);
            if (this.components.get(compClass).isEmpty()) {
                this.components.remove(compClass);
            }
        } else {
            System.err.println("Tried to remove unconnected component: " + String.valueOf(comp.getClass()));
        }
    }

    private void addChild(Gameobject child) {
        this.children.add(child);
    }

    private void removeChild(Gameobject child) {
        this.children.remove(child);
    }

    public ArrayList<Gameobject> getChildren() {
        return this.children;
    }

    private ArrayList<Gameobject> getNestedChildrenHelper(ArrayList<Gameobject> total2, Gameobject currentParent) {
        total2.add(currentParent);
        for (Gameobject child : currentParent.getChildren()) {
            this.getNestedChildrenHelper(total2, child);
        }
        return total2;
    }

    public ArrayList<Gameobject> getThisPlusNestedChildren() {
        ArrayList<Gameobject> total2 = new ArrayList<Gameobject>();
        return this.getNestedChildrenHelper(total2, this);
    }

    public void setParent(Gameobject parent) {
        if (parent != null && parent.isDestroyed()) {
            System.err.println("Setting destroyed object as parent!");
            return;
        }
        if (this.parent != null) {
            this.parent.removeChild(this);
        }
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
        }
        if (this.checkEnableChangedAndSet(this.localEnabled, this.calculateGlobalEnabled())) {
            this.fireEventEnableChanged();
        }
    }

    public void destroyThisGameobject() {
        assert (this.toDestroyed);
        if (!this.destroyed) {
            this.destroyed = true;
            if (this.parent != null) {
                this.parent.removeChild(this);
            }
            DestroyEvent de = new DestroyEvent(this);
            this.eventManager.fireEvent(de);
            this.game.gameObjects.remove(this);
        }
    }

    protected EventListener toDestroyListener() {
        return new EventListener(){

            @Override
            public void handleEvent(EventObject event) {
                if (!Gameobject.this.toDestroyed) {
                    Gameobject.this.toDestroyed = true;
                    Gameobject.this.addToDestroyList();
                }
            }
        };
    }

    public boolean getToDestroy() {
        return this.toDestroyed;
    }

    private void addToDestroyList() {
        this.game.toDestroy.add(this);
    }

    private void setToDestroy() {
        ToDestroyEvent de = new ToDestroyEvent(this);
        this.eventManager.fireEvent(de);
    }

    public static Consumer<Gameobject> destroyGameObject(Game.GameObjectKey key) {
        return toDestroy -> toDestroy.setToDestroy();
    }

    public static Consumer<Gameobject> disableGameObject(Game.GameObjectKey key) {
        return toDisable -> toDisable.setEnabled(false);
    }

    protected final EventListener enableListener() {
        return new EventListener(){

            @Override
            public void handleEvent(EventObject event) {
                Gameobject.this.checkEnableChangedAndSet(Gameobject.this.localEnabled, Gameobject.this.calculateGlobalEnabled());
            }
        };
    }

    public final boolean isEnabled() {
        return this.localEnabled && this.globalEnabled;
    }

    public final boolean isDestroyed() {
        return this.destroyed;
    }

    private boolean calculateGlobalEnabled() {
        boolean genabled = true;
        Gameobject p = this.parent;
        while (p != null) {
            if (!p.isEnabled()) {
                genabled = false;
            }
            p = p.parent;
        }
        return genabled;
    }

    private final boolean checkEnableChangedAndSet(boolean newLocalEnabled, boolean newGlobalEnabled) {
        boolean changed = (newLocalEnabled && newGlobalEnabled) != (this.localEnabled && this.globalEnabled);
        this.localEnabled = newLocalEnabled;
        this.globalEnabled = newGlobalEnabled;
        return changed;
    }

    private void fireEventEnableChanged() {
        EnableEvent ev = new EnableEvent(this, this.isEnabled());
        this.eventManager.fireEvent(ev);
    }

    private final void setEnabled(boolean enabled) {
        if (enabled != this.localEnabled && this.checkEnableChangedAndSet(enabled, this.globalEnabled)) {
            this.fireEventEnableChanged();
        }
    }

    public UniqueId getGid() {
        return this.gid;
    }

    public String toString() {
        return this.gid.toString();
    }
}

