package com.jeasywebframework.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by jeasywebframework@gmail.com on 13-12-25.
 */
public abstract class IpUtil {

    public static final String DEF_IP = "127.0.0.1";

    /**
     * 获取request的IP
     *
     * @param request
     * @return
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = IpUtil.DEF_IP;

        if (request != null) {
            // 如果登录提供的IP地址为空则取request的IP
            ip = request.getHeader("iv-remote-address");
            if (ip == null) {
                ip = request.getRemoteAddr();
            }
        }
        return ip;
    }


    /**
     * 获得本机IP地址
     * @return
     */
    public static String getLocalIp() {
        try {
            InetAddress inet = InetAddress.getLocalHost();
            return inet.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return IpUtil.DEF_IP;
    }


}
