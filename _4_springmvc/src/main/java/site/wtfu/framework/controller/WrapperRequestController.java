package site.wtfu.framework.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/clash")
public class WrapperRequestController {

    private String currentUrl = "https://raw.githubusercontent.com/xhsgg12302/gradle-test-project/master/origin.txt";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/set")
    public Object setUrl(String url){
        this.currentUrl = url;
        return new HashMap(){{ put("code", 200); put("desc","success");}};
    }

    @GetMapping(value = "/config")
    public void getContent(HttpServletResponse response){
        ResponseEntity<String> rst = null;
        try{
            if(StringUtils.isEmpty(currentUrl)){response.setStatus(200);return;}
            String url = currentUrl.replaceFirst("raw\\.githubusercontent\\.com","raw.staticdn.net");

            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            HttpEntity entity = new HttpEntity(headers);


            boolean flag = false;
            try{
                rst = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, new HashMap<>());
            }catch (HttpClientErrorException he){
                flag = true;
            }
            if(flag || rst.getStatusCode().value() != 200){
                url = "https://ghproxy.com/" + currentUrl;
                rst = restTemplate.exchange(new URI(url), HttpMethod.GET, entity, String.class);
            }


            response.setContentType("text/plain; charset=utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(rst.getBody().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){ e.printStackTrace();}
    }
}
