package com.uitgis.jms.config.dbprop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db1.datasource")
public class Db1Props extends AbsDbProps {

}
