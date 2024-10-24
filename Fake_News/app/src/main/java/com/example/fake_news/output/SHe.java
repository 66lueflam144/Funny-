package com.example.fake_news.output;

import com.example.fake_news.data.Show_You_Real_List;
import com.example.fake_news.netwotk.A_Spider;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SHe {
    private static A_Spider aSpider;

    public SHe() throws XmlPullParserException {
        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        aSpider = new A_Spider(parser);  // 初始化 A_Spider 并传入 parser
    }

    public void fetchDataFromUrl(String url, A_Spider.FetchCallback<List<Show_You_Real_List>> callback) {
        final CountDownLatch latch = new CountDownLatch(1);  // 用于等待请求完成
        final List<Show_You_Real_List>[] result = new List[1];
        final Exception[] error = new Exception[1];

        // 发起网络请求
        aSpider.fetchData(url, new A_Spider.FetchCallback<List<Show_You_Real_List>>() {
            @Override
            public void onSuccess(List<Show_You_Real_List> data) {
                result[0] = data;  // 请求成功后将结果存储到 result 数组
                latch.countDown();  // 减少 latch 计数
            }

            @Override
            public void onFailure(Exception e) {
                error[0] = e;  // 如果发生错误，将错误存储到 error 数组
                latch.countDown();  // 减少 latch 计数
            }
        });

        // 等待 10 秒，直到请求完成
        try {
            latch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查是否有错误
        if (error[0] != null) {
            callback.onFailure(error[0]);  // 调用回调方法传递错误
            return;  // 发生错误时直接退出
        }

        // 输出解析结果
        if (result[0] != null) {
            callback.onSuccess(result[0]);  // 调用回调方法传递结果
        } else {
            callback.onFailure(new Exception("No data fetched."));  // 如果没有数据，返回异常
        }
    }

    public void closeClient() {
        aSpider.closeClient();  // 关闭 OkHttp 客户端
    }
}
