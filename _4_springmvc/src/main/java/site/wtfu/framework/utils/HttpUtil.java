package site.wtfu.framework.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;

import java.nio.charset.Charset;

public class HttpUtil {

    public final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public final static int HTTP_STATUS_OK = HttpStatus.OK.value();

    public final static HttpHost DEFAULT_PROXY = new HttpHost("127.0.0.1", 8889, "HTTP");



    public static String requestGetBak(String requestUrl) {
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpRequest = new HttpGet(requestUrl);

        /*httpRequest.setConfig(
                RequestConfig.custom()
                        .setProxy(new HttpHost("wtfu.site", 13391, "HTTP"))
                        .build()
        );*/

        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpRequest);
            StatusLine statusLine = httpResponse.getStatusLine();
            return statusLine.getStatusCode() == 200 ? IOUtils.toString(httpResponse.getEntity().getContent()) : "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public void test() {

        //workGet("https://www.baidu.com", false);


        /* _12302_2021/7/12_< 测试系统代理 > */

        // NO
        //System.setProperty("http.proxyHost", "127.0.0.1");
        //System.setProperty("http.proxyPort", "8889");

        // NO
        /*System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "8889");*/

        // NO
        //System.setProperty("proxyHost", "127.0.0.1");
        //System.setProperty("proxyPort", "8889");


        // YES
        //System.setProperty("socksProxyHost", "127.0.0.1");
        //System.setProperty("socksProxyPort", "1089");


        // YES
        /*httpRequest.setConfig(
                    RequestConfig.custom()
                            .setProxy(new HttpHost("127.0.0.1", 8889, "HTTP"))
                            .build()
            );*/


        // YES
        //httpResponse = httpClient.execute(new HttpHost("127.0.0.1",1089,"HTTP"),httpRequest);


        // YES
        //HttpClient httpClient = HttpClients.custom().setRoutePlanner(new DefaultProxyRoutePlanner(new HttpHost("127.0.0.1", 8889, "HTTP"))).build();

        //workGet("https://twitter.com/",false);

        //workGet("https://www.google.com/", true, (rst) -> System.out.println("wtfu:"+rst));


        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
