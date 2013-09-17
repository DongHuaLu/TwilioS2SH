package com.dolph.servlet;

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
import com.dolph.service.ContactService;
import com.dolph.utils.JSONUtils;

/**
 * Servlet implementation class AddContact
 */
@WebServlet("/loginfilter/AddContact")
public class AddContact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddContact() {
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
		ContactService contactService = (ContactService) wac
				.getBean("contactService");

		String contactId = request.getParameter("contactId");
		String contactName = request.getParameter("contactName");
		String contactNumber = request.getParameter("contactNumber");
		String address = request.getParameter("address");

		Map<String, Object> result = new HashMap<String, Object>();

		// request.getSession().setMaxInactiveInterval(1);
		User currentUser = (User) request.getSession().getAttribute("user");

		if (contactName != null) {
			if (currentUser != null && currentUser.getId() != 0) {
				if (contactId != null && Integer.parseInt(contactId) != 0) {
					contactService.updateContact(Integer.parseInt(contactId),
							contactName, address, contactNumber, currentUser);
					result.put("state", "ok");
					result.put("response", "修改成功");
				} else {
					contactService.saveContact(contactName, address,
							contactNumber, currentUser);
					result.put("state", "ok");
					result.put("response", "添加成功");
				}

			} else {
				result.put("state", "sessionerr");
				result.put("response", "session已经超时,请重新登录");
			}
		} else {
			result.put("state", "err");
			result.put("response", "contactName不能为空");
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
