package site.wtfu.framework.web.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/21
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestInterceptor extends HandlerInterceptorAdapter {
    protected final Log logger = LogFactory.getLog(TestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("pass by preHandle");
        return true;
    }
}
