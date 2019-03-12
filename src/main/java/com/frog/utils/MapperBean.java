package com.frog.utils;

import java.util.List;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public class MapperBean {

    private String interfaceName;

    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * 接口下所有方法
     */
    private List<FrogFunction> list;

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<FrogFunction> getList() {
        return list;
    }

    public void setList(List<FrogFunction> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MapperBean{" +
                "interfaceName='" + interfaceName + '\'' +
                '}';
    }
}
