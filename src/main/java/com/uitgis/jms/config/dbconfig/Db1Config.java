package com.uitgis.jms.config.dbconfig;

import java.util.Map;

import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.uitgis.jms.config.JtaConfig;
import com.uitgis.jms.config.dbprop.Db1Props;

@Configuration
//@EnableTransactionManagement
@DependsOn(JtaConfig.BEAN_NAME_TRANSACTION_MANANGER)
@EnableJpaRepositories(entityManagerFactoryRef = Db1Config.BEAN_NAME_DB1_ENTITY_MANAGER_FACTORY, transactionManagerRef = JtaConfig.BEAN_NAME_TRANSACTION_MANANGER, basePackages = "com.uitgis.jms.repository.db1")
public class Db1Config extends AbsDbConfig {
	public static final String BEAN_NAME_DB1_ENTITY_MANAGER_FACTORY = "db1EntityManagerFactory";
	private static final String BEAN_NAME_DB1_DATASOURCE = "db1DataSource";

	@Autowired
	private Db1Props db1Props;

	/**
	 * Cần khai báo sau cùng để tránh null
	 */
	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Primary
	@Bean(name = BEAN_NAME_DB1_DATASOURCE)
	public DataSource dataSource() {
		XADataSource xaDataSource = dataSourceComponent.getXADataSource(db1Props);
		AtomikosDataSourceBean xaDb1DataSource = new AtomikosDataSourceBean();
		xaDb1DataSource.setXaDataSource(xaDataSource);
		xaDb1DataSource.setUniqueResourceName(BEAN_NAME_DB1_DATASOURCE);
		xaDb1DataSource.setPoolSize(db1Props.getPoolSizeValue());
		return xaDb1DataSource;
	}

	@Primary
	@Bean(name = BEAN_NAME_DB1_ENTITY_MANAGER_FACTORY)
	@DependsOn(JtaConfig.BEAN_NAME_TRANSACTION_MANANGER)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier(BEAN_NAME_DB1_DATASOURCE) DataSource dataSource) {
		Map<String, Object> properties = createEntityManagerProps();

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.uitgis.jms.entity.db1");
		entityManager.setPersistenceUnitName("db1");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
