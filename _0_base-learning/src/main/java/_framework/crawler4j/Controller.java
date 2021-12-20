package _framework.crawler4j;

import com.google.common.base.Supplier;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/14
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Controller {
    public static void main(String[] args) throws Exception {
        
        int numberOfCrawlers = 10;

        CrawlConfig config = new CrawlConfig();
        config.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/96.0.4664.93 Safari/537.36");
        config.setCrawlStorageFolder("C:/Users/haohuo/Desktop/temp/");
        config.setPolitenessDelay(200);
        
        config.setConnectionTimeout(60000);
        config.setSocketTimeout(120000);

        // Instantiate the controller for this crawl.
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/index.html");
        //controller.addSeed("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/62/6205.html");

        // The factory which creates instances of crawlers.
        CrawlController.WebCrawlerFactory<MyCrawler> factory = MyCrawler::new;

        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, numberOfCrawlers);
    }
}
