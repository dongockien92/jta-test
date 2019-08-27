package com.uitgis.jms.component;

import java.sql.SQLException;

import javax.sql.XADataSource;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.uitgis.jms.config.dbprop.AbsDbProps;
import com.uitgis.jms.def.Profile;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DataSourceComponent {
	@Autowired
	private PropsComponent propsComponent;

	private XADataSource getMySqlXADataSource(AbsDbProps dbProps) {
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(dbProps.getJdbcUrl());
		mysqlXaDataSource.setUser(dbProps.getUsername());
		mysqlXaDataSource.setPassword(dbProps.getPassword());
		try {
			mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		} catch (SQLException e) {
			log.error("=============" + e.getMessage());
		}
		return mysqlXaDataSource;
	}

	/**
	 * Need set max_prepared_transactions > 0
	 * 
	 * @param dbProps
	 * @return
	 */
	private XADataSource getPostgreXADataSource(AbsDbProps dbProps) {
		PGXADataSource pgXADataSource = new PGXADataSource();
		pgXADataSource.setUrl(dbProps.getJdbcUrl());
		pgXADataSource.setUser(dbProps.getUsername());
		pgXADataSource.setPassword(dbProps.getPassword());
		return pgXADataSource;
	}

	public XADataSource getXADataSource(AbsDbProps dbProps) {
		System.out.println(">>>>>>>>: " + dbProps.toString());
		Profile activeProfile = propsComponent.getActiveProfile();
		if (activeProfile == Profile.MYSQL)
			return getMySqlXADataSource(dbProps);
		return getPostgreXADataSource(dbProps);
	}
}
