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


        InputStream resourceAsStream = HtmlTest.class.getClassLoader().getResourceAsStream("demoHtml.html");

        String html = new String(FileCopyUtils.copyToByteArray(resourceAsStream),"UTF-8");

        Document doc = Jsoup.parse(html);

        Elements select = doc.select(".abstract-mode");

        Elements allElements = select.get(0).children();

        allElements.stream().forEach((e) -> {
            String id = e.attr("id");
            String text = e.children().select("div.title > div > span").text();
            System.out.println(String.format("%s,%s",id,text));
        });

        System.out.println("pause");
    }
}
