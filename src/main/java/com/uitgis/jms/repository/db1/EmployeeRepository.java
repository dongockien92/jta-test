package com.uitgis.jms.repository.db1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uitgis.jms.entity.db1.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
