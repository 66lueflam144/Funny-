package com.example.fake_news.dateHandler;

import com.example.fake_news.data.Show_You_Real_List;
import com.example.fake_news.ui.Real_ViewModel;
import com.example.fake_news.output.SHe;
import com.example.fake_news.netwotk.A_Spider;

import org.xmlpull.v1.XmlPullParserException;

import java.util.List;

public class DataHandler {
    private Real_ViewModel realViewModel;
    private SHe she;

    public DataHandler(Real_ViewModel realViewModel) throws XmlPullParserException {
        this.realViewModel = realViewModel;
        this.she = new SHe();
    }

    public void fetchAndHandleData(String url) {
        // 从指定 URL 获取数据并处理
        she.fetchDataFromUrl(url, new A_Spider.FetchCallback<List<Show_You_Real_List>>() {
            @Override
            public void onSuccess(List<Show_You_Real_List> data) {
                handleData(data);  // 处理成功的数据
            }

            @Override
            public void onFailure(Exception e) {
                // 处理错误（如果需要）
                System.out.println("Fetch failed with error: " + e.getMessage());
            }
        });
    }

    private void handleData(List<Show_You_Real_List> data) {
        // 将数据传递给 Real_ViewModel
        realViewModel.updateTextView(data);
    }
}
