package com.piousbox.training2.rest;

public class Newsitem {
    public final String id;
    public final String title;
    public final String description;
    public Newsitem(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "" + title + " :: " + description;
    }
}
