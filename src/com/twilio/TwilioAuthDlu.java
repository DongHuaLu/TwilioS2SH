package com.twilio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.client.TwilioCapability;
import com.twilio.sdk.client.TwilioCapability.DomainException;

/**
 * Servlet implementation class TwilioServer
 */
@WebServlet("/loginfilter/TwilioAuthDlu")
public class TwilioAuthDlu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TwilioAuthDlu() {
		super();
		// TODO Auto-generated constructor stub
	}

	final String accountSid = "AC074234565ada58f017faf7502febc9df";
	final String authToken = "1dfe35f4fafca990108f9157fa8871df";
	final String appSid = "AP2b0058621d8e5191d0187cf67e1d774b";
	final String clientName = "monkey";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TwilioCapability capability = new TwilioCapability(accountSid,
				authToken);
		capability.allowClientIncoming(clientName);
		capability.allowClientOutgoing(appSid);
		try {
			String token = capability.generateToken();
			response.getOutputStream().write(token.getBytes());
		} catch (DomainException e) {
			e.printStackTrace();
		}

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
