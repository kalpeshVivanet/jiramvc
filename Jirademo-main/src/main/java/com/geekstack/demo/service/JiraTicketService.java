package com.geekstack.demo.service;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.geekstack.demo.model.JiraPayload;

@Service
public class JiraTicketService {
	private HttpHeaders httpHeaders;
	private RestTemplate restTemplate;
	// @Value("${jira.user-name}")
	private String username = "kalpeshchouhan2@gmail.com";
	// @Value("${jira.secret}")
	private String secret = "ap6mLN3z29cyyoS5fbAi0F20";
	// @Value("${jira.base-url}")
	private String baseUrl = "https://mykalpeshchouhan.atlassian.net";
	// @Value("${jira.ticket-url}")
	private String ticketCreationUrl = "/rest/api/2/issue";

	@Autowired
	public JiraTicketService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseEntity createJiraTicket(JiraPayload jiraPayload) {
		System.out.println(baseUrl.concat(ticketCreationUrl));
		System.out.println(getHeaders());
		///////
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<JiraPayload> requestEntity = new HttpEntity<JiraPayload>(jiraPayload,
				getHeaders());
		System.out.println(requestEntity);
		return restTemplate.exchange(baseUrl.concat(ticketCreationUrl), HttpMethod.POST, requestEntity,
				String.class);

		/////////
		// ResponseEntity<String> response =
		///////// restTemplate.exchange(baseUrl.concat(ticketCreationUrl),
		///////// HttpMethod.POST,
		// new HttpEntity<JiraPayload>(jiraPayload, getHeaders()), String.class);
		// System.out.println("sssssss" + response.getBody());
		// if (response != null) {
		// return response.getBody();
		// }
		// return null;
	}

	public HttpHeaders getHeaders() {
		String auth = username + ":" + secret;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		// System.out.println(authHeader);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		return headers;
	}
}
