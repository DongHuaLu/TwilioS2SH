package com.dolph.servlet;

import java.io.IOException;
import java.util.Random;

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

/**
 * Servlet implementation class GetRegisterCode
 */
@WebServlet("/GetRegisterCode")
public class GetRegisterCode extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetRegisterCode() {
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
		CodeService codeService = (CodeService) wac.getBean("codeService");

		String mobile_phone = request.getParameter("mobile_phone");
		User user = userService.findUserByMobliePhone(mobile_phone);
		if (mobile_phone != null && !"".equals(mobile_phone)) {
			if (user == null) {

				Random random = new Random();
				String newCode = (Math.abs(random.nextInt(89999999)) + 10000000)
						+ "";
				codeService.insertOrUpdateCode(newCode, mobile_phone);
				response.getOutputStream().write(newCode.getBytes());
			} else {
				response.getOutputStream().write("电话已经被注册".getBytes("utf-8"));
			}
		} else {
			response.getOutputStream().write("电话不能为空".getBytes("utf-8"));
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
