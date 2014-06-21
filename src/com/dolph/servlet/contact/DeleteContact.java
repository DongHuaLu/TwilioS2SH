package com.dolph.servlet.contact;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.dolph.service.ContactService;
import com.dolph.utils.JSONUtils;

@WebServlet("/loginfilter/DeleteContact")
public class DeleteContact extends HttpServlet {

	public DeleteContact() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		ContactService contactService = (ContactService) wac
				.getBean("contactService");

		String contactId = request.getParameter("contactId");

		Map<String, Object> result = new HashMap<String, Object>();

		User currentUser = (User) request.getSession().getAttribute("user");

		if (contactId != null && Integer.parseInt(contactId) != 0) {
			boolean res = contactService.deleteContact(
					Integer.parseInt(contactId), currentUser);
			if (res) {
				result.put("state", "ok");
				result.put("response", "删除成功");
			} else {
				result.put("state", "err");
				result.put("response", "删除失败");
			}
		} else {
			result.put("state", "err");
			result.put("response", "删除失败,Id不能为空");
		}
		response.getOutputStream().write(
				JSONUtils.toJSON(result).getBytes("utf-8"));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
	}

}
