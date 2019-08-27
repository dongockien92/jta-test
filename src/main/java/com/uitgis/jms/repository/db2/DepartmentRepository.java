package com.uitgis.jms.repository.db2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uitgis.jms.entity.db2.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
