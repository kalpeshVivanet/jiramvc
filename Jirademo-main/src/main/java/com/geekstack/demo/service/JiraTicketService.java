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

// import com.geekstack.demo.model.JiraPayload;

// import com.geekstack.demo.model.JiraPayload;

@Service
public class JiraTicketService {
	// private HttpHeaders httpHeaders;
	private RestTemplate restTemplate;
	// @Value("${jira.user-name}")
	private String username = "kalpeshchouhan2@gmail.com";
	// @Value("${jira.secret}")
	private String secret = "ATATT3xFfGF0m4WrotWnss9oBiM9N9ljqDCN9Faz-84zhLKW-USyQlTrS_9Y7FSLrQ3b6q7giG21LIvg2n8IKx2rV41QqpCvKoM2RU6yNLNJ65IE1Zs3s1ij8tYu86M-veLVCjVWO1Xews5U7dlfjj529TYbzL99qxof99OoQJNEO-Rv6X0mAfo=F597A615";
	// @Value("${jira.base-url}")
	private String baseUrl = "https://mykalpeshchouhan.atlassian.net";
	// @Value("${jira.ticket-url}")
	private String ticketCreationUrl = "/rest/api/2/issue";

	@Autowired
	public JiraTicketService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String createJiraTicket() {
		System.out.println("////////////////");
		System.out.println(baseUrl.concat(ticketCreationUrl));
		System.out.println(getHeaders());
		///////

		String createIssueJSON = createCreateIssueJSON("BT", "summary",
				"description", "Bug");
		// httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<String>(createIssueJSON,
				getHeaders());
		System.out.println(requestEntity);
		ResponseEntity<String> response = restTemplate.exchange(baseUrl.concat(ticketCreationUrl),
				HttpMethod.POST, requestEntity,
				String.class);
		if (response != null) {
			return response.getBody();
		}
		return null;
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
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authHeader);
		return headers;
	}

	public String createCreateIssueJSON(String key, String summary, String description, String issueType) {
		String createIssueJSON = "{\"fields\":{\"project\":{\"key\":\"$KEY\"},\"summary\":\"$SUMMARY\",\"description\":\"$DESCRIPTION\",\"issuetype\":{\"name\":\"$ISSUETYPE\"}}}";

		createIssueJSON = createIssueJSON.replace("$KEY", key);
		createIssueJSON = createIssueJSON.replace("$SUMMARY", summary);
		createIssueJSON = createIssueJSON.replace("$DESCRIPTION", description);
		return createIssueJSON.replace("$ISSUETYPE", issueType);
	}

}
