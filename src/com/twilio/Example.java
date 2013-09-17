package com.twilio;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.weaver.ast.Call;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;

public class Example {
	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC6f10a42ec8e76409834cc2ba59cadcaf";
	public static final String AUTH_TOKEN = "eed382fd0b19ad78710689e89534fb96";

	public static void main(String[] args) throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		// Build a filter for the CallList
		Map<String, String> params = new HashMap<String, String>();
		params.put("Url",
				"http://122.193.29.102:82/TwilioServer01/ShanApp123");
		params.put("To", "+8613451517774");
		params.put("From", "+8618913162424");
		CallFactory callFactory = client.getAccount().getCallFactory();
		com.twilio.sdk.resource.instance.Call call = callFactory.create(params);
		System.out.println(call.getSid());
	}
}
