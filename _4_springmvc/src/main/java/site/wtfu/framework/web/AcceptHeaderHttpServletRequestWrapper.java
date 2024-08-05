package site.wtfu.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/8/5
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class AcceptHeaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final String acceptHeader;

    public AcceptHeaderHttpServletRequestWrapper(HttpServletRequest request, String acceptHeader) {
        super(request);
        this.acceptHeader = acceptHeader;
    }

    @Override
    public String getHeader(String name) {
        if ("Accept".equalsIgnoreCase(name)) {
            return acceptHeader;
        }
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        if ("Accept".equalsIgnoreCase(name)) {
            return new Enumeration<String>() {
                private boolean flag = true;
                @Override
                public boolean hasMoreElements() {
                    return flag;
                }

                @Override
                public String nextElement() {
                    flag = false;
                    return acceptHeader;
                }
            };
        }
        return super.getHeaders(name);
    }
}