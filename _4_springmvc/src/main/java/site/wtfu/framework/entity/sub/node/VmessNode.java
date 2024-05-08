package site.wtfu.framework.entity.sub.node;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import site.wtfu.framework.entity.sub.Proxy;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/4
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Data
@Builder
@Accessors(chain = true)
public class VmessNode extends Proxy {

    private String name;

    private String server;

    private Integer port;

    private String type;

    private String uuid;

    private Integer alterId;

    private String cipher;

    private Boolean tls;

    @JsonProperty("skip-cert-verify")
    private Boolean skipCertVerify;

    private String servername;

    private String network;

    @JsonProperty("ws-opts")
    private WsOpts wsOpts;

    @Data
    @Builder
    @Accessors(chain = true)
    public static class WsOpts {

        private String path;

        private Header headers;

        private Boolean udp;

        @Data
        @Builder
        public static class Header {
            private String Host;
        }
    }

    @Data
    @Builder
    @Accessors(chain = true)
    public static class Node {

        private String v;

        private String ps;

        private String add;

        private String port;

        private String type;

        private String id;

        private String aid;

        private String net;

        private String tls;

        private String host;

        private String sni;

        private String scy;

        private String path;

        private String alpn;

        private String fp;

    }
}
