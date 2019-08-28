package com.uitgis.jms.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uitgis.jms.repository.db1.EmployeeRepository;
import com.uitgis.jms.repository.db2.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CompanyServiceTest {
	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Autowired
	private CompanyService companyService;

	@Test
	public void transactionRollbackTest() {
		long emloyeeSizeBefore = employeeRepo.count();
		long departmentSizeBefore = departmentRepo.count();
		log.info("emloyeeSizeBefore: " + emloyeeSizeBefore);
		log.info("departmentSizeBefore: " + departmentSizeBefore);
		try {
			companyService.transactionRollbackTest(1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		long emloyeeSizeAfter = employeeRepo.count();
		long departmentSizeAfter = departmentRepo.count();
		log.info("emloyeeSizeAfter: " + emloyeeSizeAfter);
		log.info("departmentSizeAfter: " + departmentSizeAfter);
		assertTrue(emloyeeSizeBefore == emloyeeSizeAfter);
		assertTrue(departmentSizeBefore == departmentSizeAfter);
	}
}
