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
import com.dolph.utils.JSONUtils;

@WebServlet("/loginfilter/DeleteRecord")
public class DeleteRecord extends HttpServlet {

	public DeleteRecord() {
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

		String recordId = request.getParameter("recordId");

		Map<String, Object> result = new HashMap<String, Object>();

		User currentUser = (User) request.getSession().getAttribute("user");

		if (recordId != null && Integer.parseInt(recordId) != 0) {
			boolean res = recordService.deleteRecord(
					Integer.parseInt(recordId), currentUser);
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
