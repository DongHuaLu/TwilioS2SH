package com.twilio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShanApp
 */
@WebServlet("/ShanApp")
public class ShanApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShanApp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String phoneNumber = request.getParameter("PhoneNumber");
		response.setHeader("Content-type", "text/xml");
		response.getOutputStream().write(
				("<Response><Dial callerId='+8618913162424'><Number>"
						+ phoneNumber + "</Number></Dial></Response>")
						.getBytes());
//		response.getOutputStream()
//				.write(("<Response><Say voice='woman' language='en-US'>Hello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello helloHello hello hello</Say></Response>")
//						.getBytes());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
