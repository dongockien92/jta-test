package com.uitgis.jms.config.dbconfig;

import java.util.Map;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.uitgis.jms.config.JtaConfig;
import com.uitgis.jms.config.dbprop.Db2Props;

@Configuration
@DependsOn(JtaConfig.BEAN_NAME_TRANSACTION_MANANGER)
@EnableJpaRepositories(entityManagerFactoryRef = Db2Config.BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY, transactionManagerRef = JtaConfig.BEAN_NAME_TRANSACTION_MANANGER, basePackages = "com.uitgis.jms.repository.db2")
public class Db2Config extends AbsDbConfig {
	public static final String BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY = "db2EntityManagerFactory";
	private static final String BEAN_NAME_DB2_DATASOURCE = "db2DataSource";

	@Autowired
	private Db2Props db2Props;

	@Bean(name = BEAN_NAME_DB2_DATASOURCE)
	public DataSource dataSource() {
		XADataSource xaDataSource = dataSourceComponent.getXADataSource(db2Props);
		AtomikosDataSourceBean xaDb2DataSource = new AtomikosDataSourceBean();
		xaDb2DataSource.setXaDataSource(xaDataSource);
		xaDb2DataSource.setUniqueResourceName(BEAN_NAME_DB2_DATASOURCE);
		xaDb2DataSource.setPoolSize(db2Props.getPoolSizeValue());
		return xaDb2DataSource;
	}

	@Bean(name = BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier(BEAN_NAME_DB2_DATASOURCE) DataSource dataSource) {
		Map<String, Object> properties = createEntityManagerProps();

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.uitgis.jms.entity.db2");
		entityManager.setPersistenceUnitName("db2");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
