package com.frog.utils;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public class FrogFunction {
    /**
     * SQL语句类型
     */
    private String sqlType;
    /**
     * 方法名
     */
    private String funcName;
    /**
     * SQL语句
     */
    private String sql;
    /**
     * 返回值类型
     */
    private Object resultType;
    /**
     * 参数类型
     */
    private String parameterType;

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    @Override
    public String toString() {
        return "FrogFunction{" +
                "sqlType='" + sqlType + '\'' +
                ", funcName='" + funcName + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType=" + resultType +
                ", parameterType='" + parameterType + '\'' +
                '}';
    }
}
