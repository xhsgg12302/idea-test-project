package _utils.http;

import _utils.thread.ThreadUtil;
import io.netty.buffer.ByteBufUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class HttpUtil {

    public final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public final static int HTTP_STATUS_OK = HttpStatus.OK.value();

    public final static HttpHost DEFAULT_PROXY = new HttpHost("127.0.0.1", 8889, "HTTP");

    public static String workGet(String taskUrl){
        return workGet(taskUrl,false,null);
    }

    public static String workGet(String taskUrl, boolean isAsync){
        return workGet(taskUrl,isAsync,null);
    }

    public static String workGet(String taskUrl, Boolean isAsync , Consumer<String> callback) {

        String rst = null;
        if (isAsync) {
            ThreadUtil.getInstance().submit(() -> {
                System.out.println(taskUrl + "\thas commit");
                Future<InputStream> future = requestGet(taskUrl);
                String temp = null;
                try (InputStream in = future.get() ){
                    temp = new String(FileCopyUtils.copyToByteArray(in), "UTF-8");
                    System.out.println("return result:\t" + temp);
                    if (callback != null)
                        {callback.accept(temp);}
                } catch (Exception h) {
                    System.err.println(h.getMessage());
                }
            });
            return rst;
        } else {
            System.out.println(isAsync + "\t" + taskUrl + "\thas commit");
            Future<InputStream> future = requestGet(taskUrl);
            try (InputStream in = future.get()){
                rst = new String(FileCopyUtils.copyToByteArray(in), "UTF-8");
                System.out.println("return result:\t" + rst);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                return rst;
            }
        }
    }


    public static void workPost(String taskUrl, String filepath) {
        try {
            System.out.println(taskUrl + "\t" + filepath + "\t" + "\thas commit");
            HttpResponse response = requestPost(taskUrl, filepath).get();
            if (response != null) {
                HttpEntity rspEntity = response.getEntity();
                if (rspEntity != null) {
                    System.out.println("发送图片\t" + EntityUtils.toString(rspEntity));
                    System.out.println("return result:\t" + EntityUtils.toString(rspEntity));
                    System.out.println(taskUrl + "\t" + filepath + "\thas complete");
                } else {
                    System.out.println(taskUrl + "\t" + filepath + "\trspEntity is null");
                }
            } else {
                System.out.println(taskUrl + "\t" + filepath + "\tresponse is null");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * http GET 请求,返回Future<InputStream>
     *
     * @param requestUrl
     * @return
     */
    public static Future<InputStream> requestGet(String requestUrl) {
        Future<InputStream> future = ThreadUtil.getInstance().submit(() -> {

            HttpClient httpClient = HttpClients.createDefault();

            HttpGet httpRequest = new HttpGet(requestUrl);

            httpRequest.setConfig(
                    RequestConfig.custom()
                            .setProxy(new HttpHost("127.0.0.1", 8889, "HTTP"))
                            .build()
            );

            InputStream inputStream = null;
            HttpResponse httpResponse;
            try {
                httpResponse = httpClient.execute(httpRequest);
                if (httpResponse.getStatusLine().getStatusCode() == HTTP_STATUS_OK) {
                    HttpEntity httpEntity = httpResponse.getEntity();
                    inputStream = httpEntity.getContent();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            return inputStream;
        });
        return future;
    }


    public static String requestGetBak(String requestUrl) {
        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpRequest = new HttpGet(requestUrl);

        InputStream inputStream = null;
        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httpRequest);

            StatusLine statusLine = httpResponse.getStatusLine();
            String result = statusLine.toString();
            return statusLine.getStatusCode() != 200 ? result : null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * http POST 请求,返回Future<HttpResponse>
     *
     * @param requestUrl
     * @param filepath
     * @return
     */
    public static Future<HttpResponse> requestPost(String requestUrl, String filepath) {
        Future<HttpResponse> future = ThreadUtil.getInstance().submit(() -> {
            HttpResponse httpResponse = null;
            File file = new File(filepath);
            if (!file.exists()) {
                System.err.println("file is not exist, please check it:\t" + filepath);
                return null;
            }
            try {
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost post = new HttpPost(requestUrl);
                System.out.println(requestUrl);
                // 把文件转换成流对象FileBody
                FileBody bin = new FileBody(new File(filepath));
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder = builder.addPart("file", bin);
                HttpEntity reqEntity = builder.build();
                post.setEntity(reqEntity);
                httpResponse = httpClient.execute(post);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            return httpResponse;
        });
        return future;
    }


    /**
     * 转换map中的参数
     *
     * @param params
     * @return
     */
    public static String convertParams(Map<String, String> params) {
        String paramStr = "";
        try {
            if (params != null) {
                Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    String key = entry.getKey();
                    key = URLEncoder.encode(key, "UTF-8");
                    String val = entry.getValue();
                    val = URLEncoder.encode(val, "UTF-8");
                    paramStr += paramStr = "&" + key + "=" + val;
                }
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        if (!paramStr.equals("")) {
            paramStr = paramStr.replaceFirst("&", "?");
        }
        return paramStr;
    }


    @Test
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

        workGet("https://www.google.com/", true, (rst) -> System.out.println("wtfu:"+rst));


        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
