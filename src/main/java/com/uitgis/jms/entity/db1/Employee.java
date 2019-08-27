package com.uitgis.jms.entity.db1;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	/**
	 * Only using generator native for mysql
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullName;

	private Date hireDate;

	public Employee(String fullName, Date hireDate) {
		super();
		this.fullName = fullName;
		this.hireDate = hireDate;
	}

}
