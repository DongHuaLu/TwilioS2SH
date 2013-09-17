package com.dolph.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.service.CodeService;
import com.dolph.service.UserService;
import com.dolph.service.impl.CodeServiceImpl;
import com.dolph.service.impl.UserServiceImpl;

@WebServlet("/GetPassword")
public class GetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPassword() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		UserService userService = (UserService) wac.getBean("userService");
		CodeService codeService = (CodeService) wac.getBean("codeService");

		String code = req.getParameter("code");
		String mobile_phone = req.getParameter("mobile_phone");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password2");
		if (password != null && password.equals(password2)) {
			String queryCode = codeService.queryReceiptientCode(mobile_phone);
			System.out.println("code==>" + queryCode);
			System.out.println("mobile_phone==>" + mobile_phone);
			if (queryCode != null && queryCode.equals(code)) {
				if (userService.findUserByMobliePhone(mobile_phone) == null) {
					resp.getOutputStream()
							.write("修改失败，请重新修改".getBytes("utf-8"));
				} else {
					userService.updatePassword(password, mobile_phone);
					resp.getOutputStream().write("修改成功".getBytes("utf-8"));
				}
			} else {
				resp.getOutputStream().write("code错误".getBytes("utf-8"));
				return;
			}
		} else {
			resp.getOutputStream().write("密码不一样，请重新输入密码".getBytes("utf-8"));
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
