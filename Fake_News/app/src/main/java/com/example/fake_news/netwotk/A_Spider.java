package com.example.fake_news.netwotk;

import android.telecom.Call;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fake_news.data.Show_You_Real_List;
import com.example.fake_news.tools.Parse_Me;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class A_Spider {

    private static final String TAG = "A_Sipder";
    private final XmlPullParser parser;
    private Parse_Me parse_me = new Parse_Me();
    private OkHttpClient client = new OkHttpClient();

    public A_Spider(XmlPullParser parser) throws XmlPullParserException {
        this.parser = parser;
    }

    public void fetchData(String xmlUrl, FetchCallback<List<Show_You_Real_List>> callback) {
        Log.d(TAG, "Fetching data from: " + xmlUrl);

        Request request = new Request.Builder()
                .url(xmlUrl)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {

            @Override
            public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try (InputStream inputStream = response.body().byteStream()) {
                        parse_me.setInput(inputStream); // 设置输入流
                        List<Show_You_Real_List> result = parse_me.touchParse_XML(); // 解析 XML
                        callback.onSuccess(result);
                    } catch (XmlPullParserException e) {
                        Log.e(TAG, "XML resolving failed: " + e.getMessage());
                        callback.onFailure(e);
                    }
                } else {
                    Log.e(TAG, "Error response code: " + response.code());
                    callback.onFailure(new IOException("Unexpected code: " + response));
                }

            }

            @Override
            public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
                Log.e(TAG, "Failed to request network: " + e.getMessage());
                callback.onFailure(e);

            }



        });
    }

    public void setParseMe(Parse_Me parseMe) {
        this.parse_me = parseMe;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void closeClient() {
        if (client != null) {
            client.dispatcher().executorService().shutdown();
            client.connectionPool().evictAll();
            if (client.cache() != null) {
                try {
                    client.cache().close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing OkHttpClient cache: " + e.getMessage());
                }
            }
        }
    }

    // Callback 接口定义
    public interface FetchCallback<T> {
        void onSuccess(T result);
        void onFailure(Exception e);
    }

}
