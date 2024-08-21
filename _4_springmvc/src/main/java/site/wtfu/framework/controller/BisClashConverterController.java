package site.wtfu.framework.controller;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/4
 *                          @since  1.0
 *                          @author 12302
 *
 */

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import site.wtfu.framework.entity.sub.Proxy;
import site.wtfu.framework.entity.sub.SubResponse;
import site.wtfu.framework.utils.HttpUtil;
import site.wtfu.framework.utils.ParseNodeUtil;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class BisClashConverterController {

    @RequestMapping(value = "/sub", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    public Object subConverter(String url, String target, String rename) {

        String rst = HttpUtil.requestGetBak(url);
        System.out.println(rst);

        byte[] response = Base64Utils.decode(rst.trim().getBytes(StandardCharsets.UTF_8));

        String origin = new String(response);

        List<Proxy> proxies = ParseNodeUtil.parseNode(origin);

        SubResponse build = SubResponse.builder().proxies(proxies).origin(origin).build();

        return ParseNodeUtil.convertObjectToYaml(build);
    }

}
