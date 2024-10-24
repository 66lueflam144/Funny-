package com.example.reader_by_me;
import com.example.reader_by_me.data.Show_You_Real_List;
import com.example.reader_by_me.network.A_Sipder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class whatSup {

    private A_Sipder aSipder;
    

    @Before
    public void setUp() throws Exception {
        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        aSipder = new A_Sipder(parser);
    }

    @Test
    public void testFetchData() throws Exception {
        String testUrl = "https://www.ruanyifeng.com/blog/atom.xml"; // 使用实际的 RSS feed URL
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Show_You_Real_List>[] result = new List[1];
        final Exception[] error = new Exception[1];

        aSipder.fetchData(testUrl, new A_Sipder.FetchCallback<List<Show_You_Real_List>>() {
            @Override
            public void onSuccess(List<Show_You_Real_List> data) {
                result[0] = data;
                latch.countDown();
            }

            @Override
            public void onFailure(Exception e) {
                error[0] = e;
                latch.countDown();
            }
        });

        // 等待异步操作完成
        assertTrue(latch.await(10, TimeUnit.SECONDS));

        if (error[0] != null) {
            fail("Fetch failed with error: " + error[0].getMessage());
        }

        assertNotNull("Result should not be null", result[0]);
        assertFalse("Result should not be empty", result[0].isEmpty());

        // 打印获取到的数据
        for (Show_You_Real_List item : result[0]) {
            System.out.println(item.toString());
        }
        aSipder.closeClient();

        
    }

    

    
}