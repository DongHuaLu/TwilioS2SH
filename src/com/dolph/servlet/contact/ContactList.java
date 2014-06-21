package com.dolph.servlet.contact;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.model.Contact;
import com.dolph.model.User;
import com.dolph.service.ContactService;
import com.dolph.utils.JSONUtils;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddContact
 */
@WebServlet("/loginfilter/ContactList")
public class ContactList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactList() {
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

		String sSearch = request.getParameter("sSearch");

		Map<String, Object> result = new HashMap<String, Object>();

		// request.getSession().setMaxInactiveInterval(1);
		User currentUser = (User) request.getSession().getAttribute("user");
		List<Contact> contacts = contactService.findAllContactsByUser(
				currentUser, sSearch);
		result.put("state", "ok");
		if (contacts != null && contacts.size() > 0) {
			result.put("response", JSONUtils.listToJson(contacts));
		} else {
			result.put("response", "");
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
