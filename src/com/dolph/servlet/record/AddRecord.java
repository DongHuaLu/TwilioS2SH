package com.dolph.servlet.record;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dolph.model.Record;
import com.dolph.model.User;
import com.dolph.service.ContactService;
import com.dolph.service.RecordService;

@WebServlet("/loginfilter/AddRecord")
public class AddRecord extends HttpServlet {

	public AddRecord() {
		super();
	}

	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		RecordService recordService = (RecordService) wac
				.getBean("recordService");

		String startTime = request.getParameter("startTime");
		String duration = request.getParameter("duration");
		String phoneNumber = request.getParameter("phoneNumber");

		Map<String, Object> result = new HashMap<String, Object>();

		User currentUser = (User) request.getSession().getAttribute("user");

		if (startTime != null && duration != null && phoneNumber != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date startDate = df.parse(startTime);
				boolean res = recordService.saveRecord(startDate, duration,
						phoneNumber, currentUser);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
	}

}
