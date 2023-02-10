package com.geekstack.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.demo.model.JiraPayload;
import com.geekstack.demo.service.JiraTicketService;

@RestController
public class ServiceTicketController {

	@Autowired
	public ServiceTicketController(JiraTicketService jiraTicketService) {
	}

	@PostMapping("/ticket.create")
	public ResponseEntity<String> createJiraTicket(@RequestBody JiraPayload jiraPayload) {

		return new ResponseEntity<String>(HttpStatus.CREATED);

	}

}
