package com.uitgis.jms.service;

import static org.junit.Assert.assertTrue;

import org.junit.After;
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
	public void transactionRollbackTest_error() {
		long emloyeeSizeBefore = employeeRepo.count();
		long departmentSizeBefore = departmentRepo.count();
		log.info("emloyeeSizeBefore_error: " + emloyeeSizeBefore);
		log.info("departmentSizeBefore_error: " + departmentSizeBefore);
		try {
			companyService.transactionRollbackTest(1);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		long emloyeeSizeAfter = employeeRepo.count();
		long departmentSizeAfter = departmentRepo.count();
		log.info("emloyeeSizeAfter_error: " + emloyeeSizeAfter);
		log.info("departmentSizeAfter_error: " + departmentSizeAfter);
		assertTrue(emloyeeSizeBefore == emloyeeSizeAfter);
		assertTrue(departmentSizeBefore == departmentSizeAfter);
	}

	@Test
	public void transactionRollbackTest_nonerror() {
		long emloyeeSizeBefore = employeeRepo.count();
		long departmentSizeBefore = departmentRepo.count();
		log.info("emloyeeSizeBefore_nonerror: " + emloyeeSizeBefore);
		log.info("departmentSizeBefore_nonerror: " + departmentSizeBefore);
		try {
			companyService.transactionRollbackTest(0);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		long emloyeeSizeAfter = employeeRepo.count();
		long departmentSizeAfter = departmentRepo.count();
		log.info("emloyeeSizeAfter_nonerror: " + emloyeeSizeAfter);
		log.info("departmentSizeAfter_nonerror: " + departmentSizeAfter);
		assertTrue(emloyeeSizeBefore == emloyeeSizeAfter - 1);
		assertTrue(departmentSizeBefore == departmentSizeAfter - 1);
	}

	@After
	public void clear() {
		employeeRepo.deleteAll();
		departmentRepo.deleteAll();
	}
}
