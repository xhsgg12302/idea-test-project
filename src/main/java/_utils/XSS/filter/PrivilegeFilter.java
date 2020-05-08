package _utils.XSS.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @类功能说明： 粗粒度权限控制拦截过滤器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：****信息科技有限公司
 * @作者：lrx
 * @创建时间：2015-7-25 下午8:16:51
 * @版本：V1.0
 */
public class PrivilegeFilter implements Filter {

	private static final Log LOG = LogFactory.getLog(PrivilegeFilter.class);

	public void destroy() {
		// Do nothing because of X and Y.
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String uri = request.getServletPath(); // 请求路径
		
		LOG.info("== uri:" + uri);
		
		if (uri.startsWith("/") && uri.length() == 1) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/index.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/jsp")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/common")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/dwz")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/toLogin")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/favicon.ico")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/randomCode")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.endsWith("/logoutConfirm")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.endsWith("/timeoutConfirm")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/logout")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/toFindPasswd")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.startsWith("/findPasswd")) {
			chain.doFilter(request, response);
			return;
		}
			
		chain.doFilter(request, response);
		return;
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
