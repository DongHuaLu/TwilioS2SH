package com.dolph.servlet.record;

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

import com.dolph.model.Record;
import com.dolph.model.User;
import com.dolph.service.RecordService;
import com.dolph.utils.JSONUtils;

@WebServlet("/loginfilter/RecordList")
public class RecordList extends HttpServlet {

	public RecordList() {
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

		Map<String, Object> result = new HashMap<String, Object>();

		User currentUser = (User) request.getSession().getAttribute("user");
		List<Record> records = recordService
				.findAllRecordByCurrentUser(currentUser);
		result.put("state", "ok");
		if (records != null && records.size() > 0) {
			result.put("response", JSONUtils.listToJson2(records));
		} else {
			result.put("response", "");
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
