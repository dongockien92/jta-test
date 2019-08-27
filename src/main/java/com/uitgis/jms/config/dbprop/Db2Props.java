package com.uitgis.jms.config.dbprop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db2.datasource")
public class Db2Props extends AbsDbProps {

}
