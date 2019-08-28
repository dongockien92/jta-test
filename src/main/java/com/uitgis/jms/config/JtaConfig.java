package com.uitgis.jms.config;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class JtaConfig {
	public static final String BEAN_NAME_TRANSACTION_MANANGER = "transactionManager";

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		return hibernateJpaVendorAdapter;
	}

	private UserTransaction userTransaction() throws Throwable {
		UserTransactionImp userTransactionImp = new UserTransactionImp();
		userTransactionImp.setTransactionTimeout(10000);
		return userTransactionImp;
	}

	private TransactionManager atomikosTransactionManager() throws Throwable {
		UserTransactionManager userTransactionManager = new UserTransactionManager();
		userTransactionManager.setForceShutdown(false);
		return userTransactionManager;
	}

	@Bean(name = BEAN_NAME_TRANSACTION_MANANGER)
	public PlatformTransactionManager transactionManager() throws Throwable {
		UserTransaction userTransaction = userTransaction();
		AtomikosJtaPlatform.transaction = userTransaction;

		TransactionManager atomikosTransactionManager = atomikosTransactionManager();
		AtomikosJtaPlatform.transactionManager = atomikosTransactionManager;

		JtaTransactionManager jtaTransactionManager = new JtaTransactionManager(userTransaction,
				atomikosTransactionManager);
		jtaTransactionManager.setAllowCustomIsolationLevels(true);
		return jtaTransactionManager;
	}
}
