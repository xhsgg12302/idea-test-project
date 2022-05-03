package _base.html_parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-07-17
 */
public class HtmlTest {


    public static void main(String[] args) throws IOException {


        InputStream resourceAsStream = HtmlTest.class.getClassLoader().getResourceAsStream("_base/html_parse/res/wtfu.site.html");

        String html = new String(FileCopyUtils.copyToByteArray(resourceAsStream),"UTF-8");

        Document doc = Jsoup.parse(html);

        Elements select = doc.select(".s-bottom-layer");

        Elements allElements = select.get(0).children().get(0).children();

        allElements.stream().forEach((e) -> {
            String text = e.select("a").text();
            String url = e.select("a").attr("href");
            System.out.println(String.format("%s : %s",text,url));
        });

        System.out.println("pause");
    }
}
