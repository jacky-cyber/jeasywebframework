package com.jeasywebframework.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CookieUtil {



    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);



    public static final String U_KEY_ID = "_u_id_";
	public static final String U_KEY_PWD = "_u_p_";

	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie ck : cookies) {
				if (StringUtils.equals(key, ck.getName())) {
					return ck.getValue();
				}
			}
		}
		return null;
	}

	public static Cookie get(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie ck : cookies) {
				if (StringUtils.equals(key, ck.getName())) {
					return ck;
				}
			}
		}
		return null;
	}

	public static void setCookie(HttpServletResponse response, String domain,
			String key, String value, int maxAge) {
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		cookie.setDomain(domain);
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response, String key) {
		Cookie cookie = get(request, key);
		if (cookie != null) {
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

}
