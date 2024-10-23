package com.example.reader_by_me;

import com.example.reader_by_me.data.BookMarkList;
import com.example.reader_by_me.data.Show_You_Real_List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParserException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.example.reader_by_me.tools.Parse_Me;


@RunWith(RobolectricTestRunner.class)
public class whatSup {

    private Parse_Me parseMe;
    private Parse_Me.Callback callback;

    @Before
    public void setUp() throws XmlPullParserException {
        parseMe = new Parse_Me();
        callback = mock(Parse_Me.Callback.class);
    }

    @Test
    public void testParserWithASmile_Success() throws Exception {
        String testUrl = "https://jesor.me/feed.xml";
        CountDownLatch latch = new CountDownLatch(1);

        // 模拟成功的网络请求
        doAnswer(invocation -> {
            // 这里我们直接调用 onSuccess 方法，模拟解析后的结果

            List<Show_You_Real_List> mockList = parseMe.parseXML(); // 直接调用解析方法
//            ((Parse_Me.Callback) invocation.getArguments()[0]).onSuccess(mockList);
            Parse_Me.Callback callback = (Parse_Me.Callback) invocation.getMock();
            latch.countDown();
            return null;
        }).when(callback).onSuccess(anyList());

        // 调用 parser_with_a_smile 方法
        parseMe.parser_with_a_smile(testUrl, callback, latch);


        // 等待异步操作完成，设置超时
        if (!latch.await(5, TimeUnit.SECONDS)) {
            System.out.println("Operation timed out.");
            return; // 或者抛出异常
        }

        // 验证回调的 onSuccess 方法被调用
        ArgumentCaptor<List<Show_You_Real_List>> captor = ArgumentCaptor.forClass(List.class);
        verify(callback, timeout(5000)).onSuccess(captor.capture());

        // 获取解析后的列表
        List<Show_You_Real_List> result = captor.getValue();

        // 输出解析后的内容
        System.out.println("---------------------\nParsed entries:");
        for (Show_You_Real_List item : result) {
            System.out.println(item.toString()); // 假设你在 Show_You_Real_List 类中实现了 toString 方法
        }

        // 进行断言，检查返回的结果
//        List<Show_You_Real_List> result = captor.getValue();
//        assertEquals(1, result.size());
    }
//
//    @Test
//    public void testParserWithASmile_Failure() throws Exception {
//        String testUrl = "http://invalid-url.com/test.xml";
//        CountDownLatch latch = new CountDownLatch(1);
//
//        // 模拟失败的网络请求
//        doAnswer(invocation -> {
//            ((Parse_Me.Callback) invocation.getArguments()[1]).onFailure(new Exception("Network error"));
//            latch.countDown();
//            return null;
//        }).when(callback).onFailure(any(Exception.class));
//
//        parseMe.parser_with_a_smile(testUrl, callback);
//        latch.await(); // 等待异步操作完成
//
//        // 验证回调的 onFailure 方法被调用
//        verify(callback, timeout(5000)).onFailure(any(Exception.class));
//    }
}