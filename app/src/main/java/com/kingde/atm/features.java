package com.kingde.atm;

public class features {
    String name;
    int icon;

    public features() {

    }

    public features(String name) {
        this.name = name;
    }

    public features(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
