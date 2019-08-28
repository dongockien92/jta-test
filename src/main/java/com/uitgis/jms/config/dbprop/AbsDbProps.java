package com.uitgis.jms.config.dbprop;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Slf4j
public abstract class AbsDbProps {
	protected String jdbcUrl;

	protected String username;

	protected String password;

	protected String driverClassName;

	protected String poolSize;

	public int getPoolSizeValue() {
		return NumberUtils.toInt(poolSize);
	}
}
