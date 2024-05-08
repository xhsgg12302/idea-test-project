package site.wtfu.framework.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import site.wtfu.framework.entity.sub.ExYAMLFactory;
import site.wtfu.framework.entity.sub.Proxy;
import site.wtfu.framework.entity.sub.SubResponse;
import site.wtfu.framework.entity.sub.node.Hysteria2Node;
import site.wtfu.framework.entity.sub.node.VmessNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/4
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ParseNodeUtil {

    public static List<Proxy>  parseNode(String origin) {

        List<Proxy> proxies = new ArrayList<>();

        // 逐行读取origin内容
        String[] lines = origin.split("\n");
        for(String line : lines){
            String prefix = line.substring(0, line.indexOf("://"));
            String content = line.substring(prefix.length() + 3);
            Proxy proxy;
            switch (prefix){
                case "vmess1":
                case "vmess":
                    String temp = new String(Base64Utils.decodeFromString(content.trim()));
                    System.out.println(temp);
                    VmessNode.Node node = convertStringToObject(temp, VmessNode.Node.class);

                    VmessNode.WsOpts.Header header = null;
                    if(StringUtils.isNotEmpty(node.getHost())){
                        header = VmessNode.WsOpts.Header.builder().build();
                        header.setHost(node.getHost());
                    }
                    VmessNode.WsOpts wsOpts = null;
                    if(StringUtils.isNotEmpty(node.getPath())){
                        wsOpts = VmessNode.WsOpts.builder().path(node.getPath()).headers(header).udp(false).build();
                    }

                    proxy = VmessNode.builder()
                            .name(node.getPs())
                            .server(node.getAdd())
                            .port(Integer.parseInt(node.getPort()))
                            .type("vmess")
                            .uuid(node.getId())
                            .alterId(Integer.parseInt(node.getAid()))
                            .cipher(StringUtils.isEmpty(node.getScy()) ? "auto": node.getScy())
                            .tls(node.getNet().equals("tls"))
                            .skipCertVerify(true)
                            .servername(node.getSni())
                            .network(node.getNet())
                            .wsOpts(wsOpts)
                            .build();

                    //proxy.setOrigin(content.substring(0, 100));
                    break;
                case "vless":
                case "hysteria2":
                case "trojan":
                case "ss":
                case "ssr":
                case "socks":
                case "http":
                case "https":
                default:
                    proxy = Hysteria2Node.builder().build();
                    //proxy.setOrigin(line);
                    break;
            }

            if(proxy != null) proxies.add(proxy);
        }
        return proxies;
    }

    public static String convertObjectToYaml(SubResponse obj) {
        // Create an ObjectMapper mapper for YAML
        YAMLFactory yamlFactory = new ExYAMLFactory()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                // 设置 YAMLGenerator.Feature.USE_PLATFORM_LINE_BREAKS 以便跨平台兼容
                .enable(YAMLGenerator.Feature.USE_PLATFORM_LINE_BREAKS)
                // 自定义缩进空格数，例如设置为4个空格
                .configure(YAMLGenerator.Feature.SPLIT_LINES, false)
                .configure(YAMLGenerator.Feature.INDENT_ARRAYS, true)
                .configure(YAMLGenerator.Feature.ALLOW_LONG_KEYS, true)
                .configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true)
                .configure(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR, true);
        //.configure(YAMLGenerator.Feature.WRITE_FLOW_MAP_AS_MAP, true)
        //.configure(YAMLGenerator.Feature.INDENTATION, 4)
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Write object as YAML file
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 使用jackson 解析 字符串到实体
    public static <T> T convertStringToObject(String content, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
