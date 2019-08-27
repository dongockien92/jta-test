package com.uitgis.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uitgis.jms.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@PostMapping("/transactionRollbackTest")
	public void transactionRollbackTest(@RequestParam(name = "error", defaultValue = "0") int error) {
		companyService.transactionRollbackTest(error);
	}
}
