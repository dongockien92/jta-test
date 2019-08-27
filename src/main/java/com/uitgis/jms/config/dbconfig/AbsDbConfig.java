package com.uitgis.jms.config.dbconfig;

import java.util.HashMap;
import java.util.Map;

import com.uitgis.jms.config.AtomikosJtaPlatform;

public abstract class AbsDbConfig {
	protected Map<String, Object> createEntityManagerProps() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.jdbc.lob.non_contextual_creation", true); // only using for postgresql to avoid error
		return properties;
	}
}
