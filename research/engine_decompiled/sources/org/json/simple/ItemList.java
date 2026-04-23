/*
 * Decompiled with CFR 0.152.
 */
package org.json.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ItemList {
    private String sp = ",";
    List items = new ArrayList();

    public ItemList() {
    }

    public ItemList(String s2) {
        this.split(s2, this.sp, this.items);
    }

    public ItemList(String s2, String sp2) {
        this.sp = s2;
        this.split(s2, sp2, this.items);
    }

    public ItemList(String s2, String sp2, boolean isMultiToken) {
        this.split(s2, sp2, this.items, isMultiToken);
    }

    public List getItems() {
        return this.items;
    }

    public String[] getArray() {
        return (String[])this.items.toArray();
    }

    public void split(String s2, String sp2, List append2, boolean isMultiToken) {
        if (s2 == null || sp2 == null) {
            return;
        }
        if (isMultiToken) {
            StringTokenizer tokens = new StringTokenizer(s2, sp2);
            while (tokens.hasMoreTokens()) {
                append2.add(tokens.nextToken().trim());
            }
        } else {
            this.split(s2, sp2, append2);
        }
    }

    public void split(String s2, String sp2, List append2) {
        if (s2 == null || sp2 == null) {
            return;
        }
        int pos = 0;
        int prevPos = 0;
        do {
            prevPos = pos;
            if ((pos = s2.indexOf(sp2, pos)) == -1) break;
            append2.add(s2.substring(prevPos, pos).trim());
        } while ((pos += sp2.length()) != -1);
        append2.add(s2.substring(prevPos).trim());
    }

    public void setSP(String sp2) {
        this.sp = sp2;
    }

    public void add(int i, String item) {
        if (item == null) {
            return;
        }
        this.items.add(i, item.trim());
    }

    public void add(String item) {
        if (item == null) {
            return;
        }
        this.items.add(item.trim());
    }

    public void addAll(ItemList list2) {
        this.items.addAll(list2.items);
    }

    public void addAll(String s2) {
        this.split(s2, this.sp, this.items);
    }

    public void addAll(String s2, String sp2) {
        this.split(s2, sp2, this.items);
    }

    public void addAll(String s2, String sp2, boolean isMultiToken) {
        this.split(s2, sp2, this.items, isMultiToken);
    }

    public String get(int i) {
        return (String)this.items.get(i);
    }

    public int size() {
        return this.items.size();
    }

    public String toString() {
        return this.toString(this.sp);
    }

    public String toString(String sp2) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.items.size(); ++i) {
            if (i == 0) {
                sb.append(this.items.get(i));
                continue;
            }
            sb.append(sp2);
            sb.append(this.items.get(i));
        }
        return sb.toString();
    }

    public void clear() {
        this.items.clear();
    }

    public void reset() {
        this.sp = ",";
        this.items.clear();
    }
}

