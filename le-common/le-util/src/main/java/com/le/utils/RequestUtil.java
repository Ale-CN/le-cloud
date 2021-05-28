package com.le.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    /**
     * 判断是否 Ajax 请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        if ("XMLHttpRequest".equals(request.getMethod())) {
            return true;
        }
        return false;
    }

}
