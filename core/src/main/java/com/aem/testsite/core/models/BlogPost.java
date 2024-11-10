package com.aem.testsite.core.models;

public class BlogPost {
    private String title;
    private String link;

    public BlogPost(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
