package com.frog.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * FrogMapperProxy代理类完成xml方法和实际方法的对应，执行查询
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public class FrogMapperProxy implements InvocationHandler {

    private FrogSession frogSession;

    private FrogConfiguration frogConfiguration;

    public FrogMapperProxy(FrogConfiguration frogConfiguration,FrogSession frogSession) {
        this.frogConfiguration = frogConfiguration;
        this.frogSession = frogSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean readMapper = frogConfiguration.readMapper("mapper/UserMapper.xml");

        // 是否是xml文件对应的接口
        if (!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())){
            return null;
        }
        List<FrogFunction> list = readMapper.getList();
        if (null != list || list.size() != 0){
            for (FrogFunction function : list){
                // 检查id是否和接口方法名一样
                if (method.getName().equals(function.getFuncName())){
                    return frogSession.selectOne(function.getSql(), String.valueOf(args[0]));
                }
            }
        }
        return null;
    }
}
