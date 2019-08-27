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
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.uitgis.jms.component.DataSourceComponent;
import com.uitgis.jms.config.JtaConfig;
import com.uitgis.jms.config.dbprop.Db2Props;

@Configuration
//@EnableTransactionManagement
@DependsOn(JtaConfig.BEAN_NAME_TRANSACTION_MANANGER)
@EnableJpaRepositories(entityManagerFactoryRef = Db2Config.BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY, transactionManagerRef = JtaConfig.BEAN_NAME_TRANSACTION_MANANGER, basePackages = "com.uitgis.jms.repository.db2")
public class Db2Config extends AbsDbConfig {
	public static final String BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY = "db2EntityManagerFactory";
//	public static final String BEAN_NAME_DB2_TRANSACTION_MANANGER = "db2TransactionManager";
	private static final String BEAN_NAME_DB2_DATASOURCE = "db2DataSource";

	@Autowired
	private DataSourceComponent dataSourceComponent;

	@Autowired
	private Db2Props db2Props;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = BEAN_NAME_DB2_DATASOURCE)
	// @ConfigurationProperties(prefix = "db2.datasource")
	public DataSource dataSource() {
		XADataSource xaDataSource = dataSourceComponent.getXADataSource(db2Props);

		AtomikosDataSourceBean xaDb1DataSource = new AtomikosDataSourceBean();
		xaDb1DataSource.setXaDataSource(xaDataSource);
		xaDb1DataSource.setUniqueResourceName(BEAN_NAME_DB2_DATASOURCE);
		return xaDb1DataSource;
	}

	@Bean(name = BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier(BEAN_NAME_DB2_DATASOURCE) DataSource dataSource) {
		// return
		// builder.dataSource(dataSource).packages("com.uitgis.jms.entity.db2").persistenceUnit("db2").build();
		Map<String, Object> properties = createEntityManagerProps();

		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.uitgis.jms.entity.db2");
		entityManager.setPersistenceUnitName("db2");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

//	@Bean(name = BEAN_NAME_DB2_TRANSACTION_MANANGER)
//	public PlatformTransactionManager transactionManager(
//			@Qualifier(BEAN_NAME_DB2_ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
}
