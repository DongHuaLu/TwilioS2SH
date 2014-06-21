package com.dolph.web.intercepter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.model.User;
import com.dolph.service.UserService;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());
		UserService userService = (UserService) wac.getBean("userService");
		User currentUser = (User) request.getSession().getAttribute("user");
		User user = null;
		if (currentUser != null) {
			user = userService.findUserByUserName(currentUser.getUsername());
		}

		int userId = 0;
		String userIdstr = request.getParameter("userId");
		String deviceId = request.getParameter("deviceId");
		if (deviceId != null) {
			if (userIdstr != null) {
				userId = Integer.parseInt(userIdstr);
			} else {
				response.getOutputStream().write(
						"{\"response\":\"userId不能为空\",\"state\":\"sessionerr\"}"
								.getBytes("utf-8"));
				return;
			}
			if (currentUser != null && currentUser.getId() == userId
					&& deviceId.equals(user.getDeviceId())) {
				chain.doFilter(request, response);
			} else {
				response.getOutputStream().write(
						"{\"response\":\"session已经废弃,请重新登录\",\"state\":\"sessionerr\"}"
								.getBytes("utf-8"));
				return;
			}
		} else {
			response.getOutputStream().write(
					"{\"response\":\"deviceId不能为空\",\"state\":\"sessionerr\"}"
							.getBytes("utf-8"));
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
