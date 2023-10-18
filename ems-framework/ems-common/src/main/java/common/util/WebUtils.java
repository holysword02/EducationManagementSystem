package common.util;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Map;

@Slf4j
public class WebUtils {

    /**
     * 获取ServletRequestAttributes
     *
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra == null) {
            return null;
        }
        return (ServletRequestAttributes) ra;
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }

    /**
     * 获取response
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = getServletRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getResponse();
    }

    /**
     * 获取request header中的内容
     *
     * @param headerName 请求头名称
     * @return 请求头的值
     */
    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getRequest().getHeader(headerName);
    }

    public static void setResponseHeader(String key, String value){
        HttpServletResponse response = getResponse();
        if (response == null) {
            return;
        }
        response.setHeader(key, value);
    }

    public static boolean isSuccess() {
        HttpServletResponse response = getResponse();
        return response != null && response.getStatus() < 300;
    }

    /**
     * 获取请求地址中的请求参数组装成 key1=value1&key2=value2
     * 如果key对应多个值，中间使用逗号隔开例如 key1对应value1，key2对应value2，value3， key1=value1&key2=value2,value3
     *
     * @param request
     * @return 返回拼接字符串
     */
    public static String getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        return getParameters(parameterMap);
    }

    /**
     * 获取请求地址中的请求参数组装成 key1=value1&key2=value2
     * 如果key对应多个值，中间使用逗号隔开例如 key1对应value1，key2对应value2，value3， key1=value1&key2=value2,value3
     *
     * @param queries
     * @return
     */
    public  static <T> String getParameters(final Map<String, T> queries) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, T> entry : queries.entrySet()) {
            if(entry.getValue() instanceof String[]){
                buffer.append(entry.getKey()).append(String.join(",", ((String[])entry.getValue())))
                    .append("&");
            }else if(entry.getValue() instanceof Collection){
                buffer.append(entry.getKey()).append(
                        CollUtil.join(((Collection<String>)entry.getValue()),",")
                ).append("&");
            }
        }
        return buffer.length() > 0 ? buffer.substring(0, buffer.length() - 1) : StrUtil.EMPTY;
    }

}
