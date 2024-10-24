package com.example.fake_news.ui;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fake_news.data.BookMarkList;
import com.example.fake_news.data.Show_You_Real_List;

import java.util.List;

public class Real_ViewModel extends AppCompatActivity {
    private TextView titleShow;
    private TextView authorTextView;
    private TextView linkTextView;
    private TextView pubDateTextView;
    private TextView descriptionView;

    public Real_ViewModel(TextView titleShow, TextView authorTextView, TextView linkTextView, TextView pubDateTextView, TextView descriptionView) {
        this.titleShow = titleShow;
        this.authorTextView = authorTextView;
        this.linkTextView = linkTextView;
        this.pubDateTextView = pubDateTextView;
        this.descriptionView = descriptionView;
    }

    public void updateTextView(List<Show_You_Real_List> data) {
        // 清空 TextView 内容
        titleShow.setText("");
        authorTextView.setText("");
        linkTextView.setText("");
        pubDateTextView.setText("");
        descriptionView.setText("");

        // 只处理数组中的第一个元素
        if (data != null && !data.isEmpty()) {
            Show_You_Real_List firstItem = data.get(0);
            BookMarkList bookMark = firstItem.bookMarkListItem;

            // 在 UI 线程中更新 TextView 的文本
            titleShow.post(() -> titleShow.setText(bookMark.getTitle()));
            authorTextView.post(() -> authorTextView.setText(bookMark.getAuthor()));
            linkTextView.post(() -> linkTextView.setText(bookMark.getLink()));
            pubDateTextView.post(() -> pubDateTextView.setText(bookMark.getPubDate()));
            descriptionView.post(() -> descriptionView.setText(bookMark.getDescription()));
        }
    }
}
