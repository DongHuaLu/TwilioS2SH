package com.dolph.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.model.User;
import com.dolph.service.CodeService;
import com.dolph.service.UserService;
import com.dolph.service.impl.CodeServiceImpl;
import com.dolph.service.impl.UserServiceImpl;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Register() {
		// TODO Auto-generated constructor stub
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
		CodeService codeService = (CodeService) wac.getBean("codeService");

		String code = request.getParameter("code");
		String username = request.getParameter("user_name");
		String mobile_phone = request.getParameter("mobile_phone");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		if (password != null && password.equals(password2)) {
			{
				String queryCode = codeService
						.queryReceiptientCode(mobile_phone);
				if (queryCode != null && queryCode.equals(code)) {
					User user = userService.findUserByUserName(username);
					if (user == null) {
						if (userService.findUserByMobliePhone(mobile_phone) == null) {
							userService.saveUser(username, password,
									mobile_phone);
							response.getOutputStream().write(
									"成功".getBytes("utf-8"));
						} else {
							response.getOutputStream().write(
									"该电话已经注册".getBytes("utf-8"));
						}
					} else {
						response.getOutputStream().write(
								"用户名已存在".getBytes("utf-8"));
						return;
					}
				} else {
					response.getOutputStream()
							.write("code错误".getBytes("utf-8"));
					return;
				}
			}
		} else {
			response.getOutputStream().write("密码不一致".getBytes("utf-8"));
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
