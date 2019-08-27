package com.uitgis.jms.service.impl;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uitgis.jms.config.JtaConfig;
import com.uitgis.jms.entity.db1.Employee;
import com.uitgis.jms.entity.db2.Department;
import com.uitgis.jms.repository.db1.EmployeeRepository;
import com.uitgis.jms.repository.db2.DepartmentRepository;
import com.uitgis.jms.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private DepartmentRepository departmentRepo;

	@Override
	@Transactional(transactionManager = JtaConfig.BEAN_NAME_TRANSACTION_MANANGER)
	public void transactionRollbackTest(int error) {
		Employee employee = new Employee(RandomStringUtils.randomAlphabetic(10), new Date());
		employeeRepo.save(employee);

		Department department = new Department(RandomStringUtils.randomAlphabetic(5));
		departmentRepo.save(department);

		if (error != 0) {
			int a = 1 / 0;
			System.out.println(a);
		}
	}

}
