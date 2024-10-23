package com.example.reader_by_me.tools;

import android.util.Xml;

import com.example.reader_by_me.data.BookMarkList;
import com.example.reader_by_me.data.Show_You_Real_List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Parse_Me {

    private final XmlPullParser parser;
    private static final OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // 设置连接超时
                .readTimeout(30, TimeUnit.SECONDS) // 设置读取超时
                .build();
    }

    public Parse_Me() throws XmlPullParserException {
        System.out.println("Initializing Parse_Me...");
        parser = XmlPullParserFactory.newInstance().newPullParser();
        System.out.println("XmlPullParser initialized.");
    }

    // 修改 parser_with_a_smile 方法以支持异步请求
    public void parser_with_a_smile(String xml_url, Callback callback, CountDownLatch latch) {
        System.out.println("Starting parser_with_a_smile with URL: " + xml_url);
        goodbones(xml_url, callback, latch);
    }

    // 修改 goodbones 方法为异步请求
    public void goodbones(String xml_url, Callback callback, CountDownLatch latch) {
        System.out.println("Here we Go with GOODBONES...\n");
        Request request = new Request.Builder()
                .url(xml_url)
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("GOODBONES with Failure...\n");
                callback.onFailure(e);
                latch.countDown(); // 确保在失败时也结束等待
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {
                    callback.onFailure(new IOException("Unexpected code " + response));
                    latch.countDown(); // 确保在失败时也结束等待
                    return;
                }

                // 处理响应
                try (InputStream inputStream = response.body().byteStream()) {
                    System.out.println("GOOD BONES with handle inputstream...\n");
                    parser.setInput(inputStream, null);
                    List<Show_You_Real_List> showYouRealLists = parseXML();
                    callback.onSuccess(showYouRealLists);
                } catch (XmlPullParserException e) {
                    callback.onFailure(e);
                    latch.countDown(); // 确保在解析异常时也结束等待
                }
            }
        });
    }

    // 解析 XML 的方法
    public List<Show_You_Real_List> parseXML() throws XmlPullParserException, IOException {
        System.out.println("Here we go with PARSEXML...\n");
        int event;
        String Bigger_Title = null;
        List<Show_You_Real_List> showYouRealLists = new ArrayList<>();

        event = parser.getEventType();
        int itemCount = 0;

        System.out.println("GET into parser...\n");

        while (event != XmlPullParser.END_DOCUMENT && itemCount < 1) {

            String name = parser.getName();
            System.out.println("Current event: " + event + ", Current tag: " + name);

            if (event == XmlPullParser.START_TAG && name.equals("title") ) {
                if (Bigger_Title == null) {  // 确保只记录主 feed 的标题
                    Bigger_Title = who(parser);
                    System.out.println("Found title: " + Bigger_Title);
                }
            } else if (event == XmlPullParser.START_TAG && name.equals("item")) {
                System.out.println("Found item tag, parsing loop...");
                BookMarkList bookMarkList = parse_loop(parser);
                showYouRealLists.add(new Show_You_Real_List(bookMarkList, Bigger_Title));
                itemCount++;
                System.out.println("Added Show_You_Real_List to the list, current count: " + itemCount);
//                break;
            }
            else if (event == XmlPullParser.START_TAG && name.equals("entry")) {
                System.out.println("Found entry tag, parsing loop...");
                BookMarkList bookMarkList = parse_loop(parser);
                showYouRealLists.add(new Show_You_Real_List(bookMarkList, Bigger_Title));
                itemCount++;
                System.out.println("Added Show_You_Real_List to the list, current count: " + itemCount);
//                break;
            }
            event = parser.next();
        }

        System.out.println("Finished parsing XML, total items parsed: " + showYouRealLists.size());
        return showYouRealLists;
    }

    // 定义一个回调接口
    public interface Callback {
        void onSuccess(List<Show_You_Real_List> showYouRealLists);
        void onFailure(Exception e);
    }

//    public String who(XmlPullParser whoever) throws XmlPullParserException, IOException {
//        System.out.println("Here we go with WHO...\n");
//        StringBuilder text = new StringBuilder();
//
//        while (whoever.next() != XmlPullParser.END_TAG) {
//            if (whoever.getEventType() == XmlPullParser.TEXT) {
//                text.append(whoever.getText());
//                System.out.println("Appending text: " + whoever.getText());
//                whoever.nextTag();
//            }
//        }
//        System.out.println("Completed WHO, result: " + text.toString());
//        return text.toString();
//    }

    public String who(XmlPullParser parser) throws XmlPullParserException, IOException {
        System.out.println("Here we go with WHO...\n");
        if (parser.next() == XmlPullParser.TEXT) {
            String text = parser.getText();
            parser.nextTag(); // 跳到下一个标签，避免循环
            System.out.println("Completed WHO, result: " + text);
            return text;
        }
        return "";
    }


    public BookMarkList parse_loop(XmlPullParser popular) throws XmlPullParserException, IOException {
        System.out.println("Here we go with parse_loop...\n");
        String title = null;
        String link = null;
        String author = null;
        String pubDate = null;
        String image = null;
        String description = null;


        System.out.println("parse_loop with real element...\n");
        while (popular.next() != XmlPullParser.END_TAG) {
            String name = popular.getName();
            if (popular.getEventType() == XmlPullParser.START_TAG) {
                switch (name) {
                    case "title":
                        title = who(popular);
                        System.out.println("Parsed title: " + title);
                        break;
                    case "link":
                        // 处理 link 的不同类型
                        String rel = popular.getAttributeValue(null, "rel");
                        if (rel == null || rel.equals("alternate")) {
                            link = popular.getAttributeValue(null, "href");
                        } else {
                            skip(popular); // 跳过不关心的 link
                        }
                        break;
                    case "id":
                       if (link == null) {
                           link = who(popular);
                           System.out.println("Parsed link: " + link);
                       }
                        break;
                    case "author":
                       author = parseAuthor(popular);
                        break;
                    case "dc:creator":
                        author = who(popular);
                        System.out.println("Parsed author: " + author);
                        break;
                    case "pubDate":
                        pubDate = who(popular);
                        System.out.println("Parsed pubDate: " + pubDate);
                        break;
                    case "cover_image":
                        image = who(popular);
                        System.out.println("Parsed cover_image: " + image);
                        break;
                    case "summary":
                    case "description":
                        description = who(popular);
                        System.out.println("Parsed description: " + description);
                        break;
                    default:
                        System.out.println("Skipping unknown tag: " + name);
                        skip(popular);  // 跳过未知的或不关心的标签
                        break;
                }
            }
        }

        System.out.println("Completed parse_loop, creating BookMarkList...");
        return new BookMarkList(title, link, author, pubDate, image, description);
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        System.out.println("Here we go with SKIP...\n");
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
        System.out.println("Skipped unknown tag, current depth: " + depth);
    }

    public String parseAuthor(XmlPullParser parser) throws XmlPullParserException, IOException {
        String authorName = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            String tagName = parser.getName();
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (tagName.equals("name")) {
                    authorName = who(parser);
                } else if (tagName.equals("uri") || tagName.equals("email")) {
                    skip(parser);  // 跳过 URI 和 email，如果你不需要
                } else {
                    skip(parser);
                }
            }
        }
        return authorName;
    }

}