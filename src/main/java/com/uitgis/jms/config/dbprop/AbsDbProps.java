package com.uitgis.jms.config.dbprop;

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

}
