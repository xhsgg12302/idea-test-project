package site.wtfu.framework.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import site.wtfu.framework.utils.HttpUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/clash")
public class WrapperRequestController {

    private String currentUrl = "https://raw.githubusercontent.com/xhsgg12302/gradle-test-project/master/origin.txt";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/set", method = RequestMethod.GET)
    public Object setUrl(String url, HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ": " + headerValue);
        }
        this.currentUrl = url;
        return new HashMap(){{ put("code", 200); put("desc","success");}};
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public void getContent(HttpServletResponse response){
        try{
            if(StringUtils.isEmpty(currentUrl)){response.setStatus(200);return;}

            String rst = HttpUtil.requestGetBak(currentUrl);

            response.setContentType("text/plain; charset=utf-8");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(rst.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){ e.printStackTrace();}
    }
}
