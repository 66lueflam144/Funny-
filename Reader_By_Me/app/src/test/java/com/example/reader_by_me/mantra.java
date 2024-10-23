////package com.example.reader_by_me;
////
////import com.example.reader_by_me.data.Show_You_Real_List;
////import com.example.reader_by_me.tools.Parse_Me;
////
////import org.junit.Test;
////import static org.junit.Assert.*;
////
////import org.junit.runner.RunWith;
////import org.robolectric.RobolectricTestRunner;
////import org.robolectric.annotation.Config;
////import org.xmlpull.v1.XmlPullParserException;
////
////import java.io.IOException;
////import java.util.List;
////
////
////@RunWith(RobolectricTestRunner.class)
////@Config(sdk = 28)
////public class mantra {
////    @Test
////    public void flawless() throws XmlPullParserException {
////        String xmlUrl = "https://free.apprcn.com/feed/";
////        Parse_Me parseMe = new Parse_Me();
////
////        try {
////            List<Show_You_Real_List> solo = parseMe.parser_with_a_smile(xmlUrl);
////
////            // 验证解析结果不为 null
////            assertNotNull("Parsed list should not be null", solo);
////            // 验证解析结果至少有一个项
////            assertTrue("Parsed list should have at least one item", solo.size() > 0);
////
////            // 输出每个项的信息
////            for (Show_You_Real_List listItem : solo) {
////                System.out.println(listItem);
////                System.out.println("--------------------------");
////            }
////
////        } catch (XmlPullParserException | IOException e) {
////            e.printStackTrace(); // 输出异常信息，便于调试
////        }
////    }
////}
//
//package com.example.reader_by_me;
//
//import com.example.reader_by_me.data.BookMarkList;
//import com.example.reader_by_me.data.Show_You_Real_List;
//import com.example.reader_by_me.tools.Parse_Me;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import org.junit.runner.RunWith;
//import org.robolectric.RobolectricTestRunner;
//import org.robolectric.annotation.Config;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//
//@RunWith(RobolectricTestRunner.class)
//@Config(sdk = 28)
//public class mantra {
//
//    @Test
//    public void flawless() {
//        CountDownLatch latch = new CountDownLatch(1);
//        try {
//            // 使用 Mock 数据替代真实的 URL
//            String mockXmlData = "https://www.biede.com/feed/";
//            // 创建 Parse_Me 实例
//            Parse_Me parseMe = new Parse_Me();
//
//            // 直接解析 Mock XML 数据
//            parseMe.parser_with_a_smile(mockXmlData, new Parse_Me.Callback() {
//                @Override
//                public void onSuccess(List<Show_You_Real_List> showYouRealLists) {
//                    // 输出每个项的信息
//                    System.out.println("Parsed Show_You_Real_List items:");
//                    for (Show_You_Real_List listItem : showYouRealLists) {
//                        System.out.println(listItem); // 这里会调用你重写的 toString 方法
//                        System.out.println("--------------------------");
//                    }
//                    latch.countDown();
//                }
//
//                @Override
//                public void onFailure(Exception e) {
//                    System.err.println("Error occurred during parsing: " + e.getMessage());
//                    e.printStackTrace(); // 输出异常信息，便于调试
//                    latch.countDown();
//                }
//            });
//            latch.await();
//
//        } catch (XmlPullParserException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
