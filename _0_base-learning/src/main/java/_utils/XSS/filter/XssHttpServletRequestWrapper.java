package _utils.XSS.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @类功能说明： xss跨站点脚本攻击拦截器--过滤请求特殊字符.
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @公司名称：****信息科技有限公司
 * @作者：lrx
 * @创建时间：2015-5-16 下午10:29:12
 * @版本：V1.0
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        String value = super.getHeader(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     * 
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            /*switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '%':
                    sb.append('％'); // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }*/
        }
        return sb.toString();
    }

    /**
     * 获取最原始的request
     * 
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     * 
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }
}
