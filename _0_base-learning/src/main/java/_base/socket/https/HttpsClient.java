package _base.socket.https;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/4/11
 *                          @since  1.0
 *                          @author 12302
 *
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HttpsClient {
    public static void main(String[] args) throws Exception {

        System.setProperty("javax.net.debug", "all");
        // 创建URL对象
        URL url = new URL("https://wtfu.site");

        // 打开连接
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        // 设置请求方法
        connection.setRequestMethod("GET");

        // 获取响应代码
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // 读取响应
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应内容
        System.out.println("Response Content: " + response.toString());
    }
}

