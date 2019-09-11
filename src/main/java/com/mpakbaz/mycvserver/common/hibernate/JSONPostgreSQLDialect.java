package com.mpakbaz.mycvserver.common.hibernate;

import org.hibernate.spatial.dialect.postgis.PostgisPG82Dialect;

import java.sql.Types;

public class JSONPostgreSQLDialect extends PostgisPG82Dialect  //PostgreSQL9Dialect
{
    public JSONPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
