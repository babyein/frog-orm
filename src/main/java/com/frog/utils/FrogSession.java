package com.frog.utils;

import java.lang.reflect.Proxy;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public class FrogSession {
    private Executor executor = new FrogExecutor();
    private FrogConfiguration frogConfiguration = new FrogConfiguration();

    public <T> T selectOne(String statement, Object parameter){
        return executor.query(statement, parameter);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> tClass){
        return (T) Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new FrogMapperProxy(frogConfiguration, this));
    }
}
