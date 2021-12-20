package _framework.crawler4j;

import _framework.crawler4j.mbts.StatsGovData;
import _framework.crawler4j.mbts.Utils;
import _framework.crawler4j.mbts.mapper.StatsGovDataMapper;
import com.mysql.fabric.HashShardMapping;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.frontier.Frontier;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.regex.Pattern;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/14
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class MyCrawler extends WebCrawler {
    
    private static final Object mutex = new Object();
    
    private static final Set<String> records = new HashSet<>();

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");
    
    private final static String CSS_PREFIX = "body > table:nth-child(3) > tbody > tr:nth-child(1) > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > table > tbody ";

    private final static String preparementSql = "insert into stats_gov_data  (sgd_name,sgd_doc_id,sgd_father_doc_id,sgd_depth,sgd_code,sgd_classifier_code,sgd_source_url,sgd_state,sgd_created_time,sgd_remark )" +
            " values (?,?,?,?,?,?,?,?,?,?)";
    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "https://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/");
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {
            try {
                String html = new String(page.getContentData(), page.getContentCharset());
                handleHtml(page,html);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 爬取失败后重新加入队列
     * @param page
     */
    @Override
    protected void onContentFetchError(Page page) {
        super.onContentFetchError(page);
        Frontier frontier = getMyController().getFrontier();
        //map 记录
        recordFailedUrl(page.getWebURL());
        frontier.schedule(page.getWebURL());
    }
    
    private void recordFailedUrl(WebURL failUrl){
        synchronized (mutex){
            records.add(failUrl.getURL());
        }
    }
    private void removeFailedUrl(WebURL failUrl){
        if(records.contains(failUrl.getURL())){
            synchronized (mutex){
                records.remove(failUrl.getURL());
                logger.error("success fetch the page :" + failUrl.getURL());
                logger.info("the remain size : " + records.size());
            }
        }
        
    }

    private void handleHtml(Page page, String htmlString) {
        WebURL webURL = page.getWebURL();
        page.getParseData().getOutgoingUrls();
        Document document = Jsoup.parse(htmlString);
        Elements select;
        
        if(webURL == null){ System.err.println("error"); return; }
        removeFailedUrl(webURL);

        String fatherUrl = Utils.getFatherUrl(webURL.getURL());
        List<StatsGovData> addLists = new ArrayList<>();
        switch (webURL.getDepth()){
            case 0:
                select = document.select(CSS_PREFIX +" tr[class=provincetr]");
                for (Element te : select) {
                    Elements children = te.children();
                    for (Element item : children) {
                        addLists.add(StatsGovData.builder()
                                .sgdName(item.text())
                                .sgdDocId(webURL.getDocid())
                                .sgdFatherDocId(webURL.getParentDocid())
                                .sgdDepth(webURL.getDepth())
                                .sgdCode("")
                                .sgdClassifierCode("")
                                .sgdSourceUrl(Utils.getSelfUrl(item.children().attr("href"),fatherUrl))
                                .sgdFatherUrl(fatherUrl)
                                .sgdRemark("second edition")
                                .build());
                    }
                }
                break;
            case 1:
                select = document.select(CSS_PREFIX + " tr[class=citytr]");
                for (Element item : select) {
                    addLists.add(StatsGovData.builder()
                            .sgdName(item.child(1).text())
                            .sgdDocId(webURL.getDocid())
                            .sgdFatherDocId(webURL.getParentDocid())
                            .sgdDepth(webURL.getDepth())
                            .sgdCode(item.child(0).text())
                            .sgdClassifierCode("")
                            .sgdSourceUrl(Utils.getSelfUrl(item.child(1).children().attr("href"),fatherUrl))
                            .sgdFatherUrl(fatherUrl)
                            .sgdRemark("second edition")
                            .build());
                }
                break;
            case 2:
                select = document.select(CSS_PREFIX + " tr[class=countytr]");
                for (Element item : select) {
                    addLists.add(StatsGovData.builder()
                            .sgdName(item.child(1).text())
                            .sgdDocId(webURL.getDocid())
                            .sgdFatherDocId(webURL.getParentDocid())
                            .sgdDepth(webURL.getDepth())
                            .sgdCode(item.child(0).text())
                            .sgdClassifierCode("")
                            .sgdSourceUrl(Utils.getSelfUrl(item.child(1).children().attr("href"),fatherUrl))
                            .sgdFatherUrl(fatherUrl)
                            .sgdRemark("second edition")
                            .build());
                }
                break;
            case 3:
                select = document.select(CSS_PREFIX + " tr[class=towntr]");
                for (Element item : select) {
                    addLists.add(StatsGovData.builder()
                            .sgdName(item.child(1).text())
                            .sgdDocId(webURL.getDocid())
                            .sgdFatherDocId(webURL.getParentDocid())
                            .sgdDepth(webURL.getDepth())
                            .sgdCode(item.child(0).text())
                            .sgdClassifierCode("")
                            .sgdSourceUrl(Utils.getSelfUrl(item.child(1).children().attr("href"),fatherUrl))
                            .sgdFatherUrl(fatherUrl)
                            .sgdRemark("second edition")
                            .build());
                }
                break;
            case 4:
                select = document.select(CSS_PREFIX + " tr[class=villagetr]");
                for (Element item : select) {
                    addLists.add(StatsGovData.builder()
                            .sgdName(item.child(2).text())
                            .sgdDocId(webURL.getDocid())
                            .sgdFatherDocId(webURL.getParentDocid())
                            .sgdDepth(webURL.getDepth())
                            .sgdCode(item.child(0).text())
                            .sgdClassifierCode(item.child(1).text())
                            .sgdSourceUrl(Utils.getSelfUrl(item.child(2).children().attr("href"),fatherUrl))
                            .sgdFatherUrl(fatherUrl)
                            .sgdRemark("second edition")
                            .build());
                }
                break;
            default:
                break;
        }
        Utils.executeSqlInsert(addLists);
    }
}
