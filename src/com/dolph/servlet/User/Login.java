package com.dolph.servlet.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.model.User;
import com.dolph.service.UserService;
import com.dolph.service.impl.UserServiceImpl;
import com.dolph.utils.JSONUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		UserService userService = (UserService) wac.getBean("userService");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String deviceId = request.getParameter("deviceId");

		User user = userService.findUserByUserName(username);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user == null) {
			result.put("state", "err");
			result.put("response", "用户名错误");
		} else if (password == null || !password.equals(user.getPassword())) {
			result.put("state", "err");
			result.put("response", "密码错误");
		} else if (deviceId == null || "".equals(deviceId)) {
			result.put("state", "err");
			result.put("response", "deviceId不能为空");
		} else {
			Map<String, String> userInfo = new HashMap<String, String>();
			userInfo.put("userId", user.getId() + "");
			userInfo.put("username", user.getUsername());
			userInfo.put("mobile_phone", user.getMobile_phone());
			result.put("state", "ok");
			result.put("response", userInfo);
			request.getSession().setAttribute("user", user);
			user.setDeviceId(deviceId);
			userService.updataUser(user);
		}
		response.getOutputStream().write(
				JSONUtils.toJSON(result).getBytes("utf-8"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
