package com.example.reader_by_me.data;

import androidx.annotation.NonNull;

public class BookMarkList {
    private String title;
    private String link;
    private String author;
    private String pubDate;

    private String description;


    public BookMarkList(String title, String link, String author, String pubDate, String description) {
        this.title = title;
        this.link = link;
        this.author = author;
        this.pubDate = pubDate;
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NonNull
    @Override
    public String toString() {
        return "BookMarkList{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
