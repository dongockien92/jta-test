package com.uitgis.jms.config.dbconfig;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.uitgis.jms.component.DataSourceComponent;
import com.uitgis.jms.config.AtomikosJtaPlatform;

public abstract class AbsDbConfig {

	@Autowired
	protected DataSourceComponent dataSourceComponent;

	protected Map<String, Object> createEntityManagerProps() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
//		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.transaction.coordinator_class", "jta");
		properties.put("hibernate.jdbc.lob.non_contextual_creation", true); // only using for postgresql to avoid error
		return properties;
	}

//	protected AtomikosNonXADataSourceBean createNonXADataSource(AbsDbProps dbProps) {
//		AtomikosNonXADataSourceBean nonXaDataSource = new AtomikosNonXADataSourceBean();
//		nonXaDataSource.setUrl(dbProps.getJdbcUrl());
//		nonXaDataSource.setUser(dbProps.getUsername());
//		nonXaDataSource.setPassword(dbProps.getPassword());
//		nonXaDataSource.setDriverClassName(dbProps.getDriverClassName());
//		nonXaDataSource.setPoolSize(dbProps.getPoolSizeValue());
//		return nonXaDataSource;
//	}
}
