package com.piousbox.training2.rest;

public class SiteNews {
    private String title;
    private String description;

    public static String getContent() {
        return "some content";
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "@TODO: replace this, toString of SiteNews.java";
    }
}
