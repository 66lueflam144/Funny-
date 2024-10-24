package com.example.fake_news.data;


import androidx.annotation.NonNull;

public class Show_You_Real_List {

    public BookMarkList bookMarkListItem;
    String title_show;

    public Show_You_Real_List(BookMarkList bookMarkListItem, String title_show) {
        this.bookMarkListItem = bookMarkListItem;
        this.title_show = title_show;
    }



    public Show_You_Real_List() {

    }

    @NonNull
    @Override
    public String toString() {
        return "Show_You_Real_List{" +
                "bookMarkListItem=" + bookMarkListItem +
                ", title_show='" + title_show + '\'' +
                '}';
    }


}
