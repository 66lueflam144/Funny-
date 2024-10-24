package com.example.fake_news.tools;



import static com.example.fake_news.tools.Sugar_kit.skip;
import static com.example.fake_news.tools.Sugar_kit.who;

import com.example.fake_news.data.BookMarkList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class No_Limits {
    String title = null;
    String link = null;
    String id = null;
    String description = null;
    String author = null;
    String pubDate = null;
    String updated = null;
    String summary = null;

    public BookMarkList handleCommonFeedTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        while (parser.next() != XmlPullParser.END_TAG) {
            String name = parser.getName();

            if (parser.getEventType() == XmlPullParser.START_TAG) {
                switch (name) {
                    case "title":
                        title = who(parser);
                        break;
                    case "link":
                        String rel = parser.getAttributeValue(null, "rel");
                        if (rel == null || rel.equals("alternate")) {
                            link = parser.getAttributeValue(null, "href");
                        } else {
                            skip(parser);
                        }
                        break;
                    case "updated":
                        updated = who(parser);
                        break;
                    case "id":
                        id = who(parser);
                        break;
                    case "author":
                        author = who(parser);
                        break;
                    case "published":
                        pubDate = who(parser);
                    case "summary":
                        summary = who(parser);
                    default:
                        skip(parser);
                        System.out.println("----SO ---");
                        break;
                }
            }
        }

        System.out.println("Completed parse_loop, creating BookMarkList...");
        return new BookMarkList(title, link, author, pubDate, description);
    }





}

