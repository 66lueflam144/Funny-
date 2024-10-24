package com.example.reader_by_me.tools;

import static com.example.reader_by_me.tools.Sugar_kit.skip;
import static com.example.reader_by_me.tools.Sugar_kit.who;

import androidx.annotation.NonNull;

import com.example.reader_by_me.data.BookMarkList;
import com.example.reader_by_me.data.Show_You_Real_List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Parse_Me {

    private final XmlPullParser parser;


    public Parse_Me() throws XmlPullParserException {
        System.out.println("Initializing Parse_Me...");
        parser = XmlPullParserFactory.newInstance().newPullParser();
        System.out.println("XmlPullParser initialized.");
    }

    




    public List<Show_You_Real_List> touchParse_XML() throws XmlPullParserException, IOException {
        System.out.println("\n------------Here We Go With TOUCHPARSE_XML-----------\n");

        int event;
        List<Show_You_Real_List> strawberryLists = new ArrayList<>();
        No_Limits noLimits = new No_Limits();

        System.out.println("\n----GET into parser...\n");

        // 确保解析器进入根标签，获取 rootTag
        while ((event = parser.next()) != XmlPullParser.END_DOCUMENT) {
            System.out.println("Event: " + event + ", Tag: " + parser.getName());

            if (event == XmlPullParser.START_TAG) {
                String rootTag = parser.getName();
                System.out.println("Root tag is: " + rootTag);

                if (rootTag.equals("feed")) {
                    handleAtomFeed(strawberryLists, noLimits);
                    return strawberryLists; // 找到 feed 标签后立即返回
                } else {
                    throw new XmlPullParserException("Unsupported XML format: " + rootTag);
                }
            } else if (event == XmlPullParser.END_TAG) {
                System.out.println("End tag found: " + parser.getName());
            } else if (event == XmlPullParser.TEXT) {
                System.out.println("Text found: " + parser.getText());
            }
        }

        throw new XmlPullParserException("No start tag found in the XML");
    }




    public void handleAtomFeed(List<Show_You_Real_List> strawberryLists, No_Limits noLimits) throws XmlPullParserException, IOException {
    strawberryLists.clear(); // 清空列表，准备下一次解析
    parser.require(XmlPullParser.START_TAG, null, "feed");

    String feedTitle = null; // 整个 feed 的标题
    String author;

    try {
        while (true) {
            int eventType = parser.next(); // 获取下一个事件
            System.out.println("Event Type: " + eventType + ", Tag: " + parser.getName() + "\n");

            if (eventType == XmlPullParser.END_TAG) {
                // 检查是否是 feed 的结束标签
                if (parser.getName().equals("feed")) {
                    break; // 如果是 feed 的结束标签，则退出循环
                }
                continue; // 如果是其他的结束标签，继续下一个事件
            }

            if (eventType != XmlPullParser.START_TAG) {
                continue; // 不是开始标签，继续下一个事件
            }

            String name = parser.getName();

            // 处理同级标签
            switch (name) {
                case "title":
                    feedTitle = who(parser); // 获取 feed 的标题

                    System.out.println("Feed Title: " + feedTitle);
                    break;
                case "subtitle":
                    author = who(parser); // 获取 subtitle

                    System.out.println("Subtitle: " + author);
                    break;
                case "entry":
                    // 处理 entry 标签
                    System.out.println("ENTRY!!!!!!");
                    BookMarkList bookMarkList = noLimits.handleCommonFeedTag(parser);
                    if (bookMarkList != null) {
                        Show_You_Real_List item = new Show_You_Real_List(bookMarkList, feedTitle);
                        strawberryLists.add(item);
                        System.out.println("\nADD this one to list:" + item + "\n");
                    }
                    break;
                default:
                    skip(parser); // 跳过未知标签

                    System.out.println("Skipped unknown tag in FEED: " + name);
                    break;
            }
        }
    } catch (XmlPullParserException | IOException e) {
        System.err.println("Error while parsing XML: " + e.getMessage());
        throw e; // 重新抛出异常以便调用者处理
    }

    parser.require(XmlPullParser.END_TAG, null, "feed"); // 确保结束标签
}


    public void setInput(InputStream inputStream) throws XmlPullParserException {
        parser.setInput(inputStream, null);
    }
}