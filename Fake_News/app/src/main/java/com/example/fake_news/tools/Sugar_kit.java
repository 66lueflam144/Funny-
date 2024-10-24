package com.example.fake_news.tools;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Sugar_kit {

    public static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    public static String parseAuthor(XmlPullParser parser) throws XmlPullParserException, IOException {
        String authorName = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            String tagName = parser.getName();
            if (parser.getEventType() == XmlPullParser.START_TAG) {
                if (tagName.equals("name")) {
                    authorName = who(parser);
                } else if (tagName.equals("uri") || tagName.equals("email")) {
                    skip(parser);  // 跳过 URI 和 email
                } else {
                    skip(parser);
                }
            }
        }
        return authorName;
    }

    public static String who(XmlPullParser parser) throws XmlPullParserException, IOException {
        System.out.println("Here we go with WHO...\n");
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag(); // 跳到下一个标签，避免循环
            System.out.println("Completed WHO, result: " + result);

        }
        return result;

    }


}
