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
@WebServlet("/loginfilter/TwilioAuth")
public class TwilioAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TwilioAuth() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static final String ACCOUNT_SID = "AC6f10a42ec8e76409834cc2ba59cadcaf";
	public static final String AUTH_TOKEN = "eed382fd0b19ad78710689e89534fb96";
	public static final String appSid = "AP2bb1e352e1c1f8bb80a0526193d2dde0";
	public static String clientName = "monkey";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TwilioCapability capability = new TwilioCapability(ACCOUNT_SID,
				AUTH_TOKEN);
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
