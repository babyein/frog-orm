package com.frog.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 读取与解析配置信息，并返回处理后的Environment
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
class FrogConfiguration {
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    /**
     * 读取xml信息并处理
     */
    Connection build(String resource){
        try{
            Element root = getRootNode(resource);
            return evalDataSource(root);
        } catch (Exception e){
            throw new RuntimeException("error occurred while evaling xml" + resource);
        }
    }

    private Connection evalDataSource(Element node) throws ClassNotFoundException {
        final String dataBase = "database";
        if (!dataBase.equals(node.getName())){
            throw new RuntimeException("root should be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;

        // 获取属性节点
        final String property = "property";
        for (Object item : node.elements(property)){
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null){
                throw new RuntimeException("[database]: <property> unknown name");
            }

            // 赋值
            switch (name){
                case "url" : url = value; break;
                case "username" : username = value; break;
                case "password" : password = value; break;
                case "driverClassName" : driverClassName = value; break;
                default: throw new RuntimeException("[database] <property> unknown name");
            }
        }

        Class.forName(driverClassName);
        Connection connection = null;
        try{
            if (url == null || username == null || password == null){
                return null;
            } else {
                // 建立数据库连接
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 获取property属性的值，如果有value值，则获取
     * @param node 根节点
     * @return 字符串值
     */
    private String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    MapperBean readMapper(String path){
        System.out.println(path);
        MapperBean mapper = new MapperBean();
        try{

            Element root = getRootNode(path);
            // 把mapper节点的nameSpace值存为接口名
            mapper.setInterfaceName(root.attributeValue("nameSpace").trim());
            // 用来存储方法的List
            List<FrogFunction> list = new ArrayList<>();
            // 遍历根节点下所有子节点
            for (Iterator rootIter = root.elementIterator(); rootIter.hasNext();){
                FrogFunction function = new FrogFunction();
                Element e = (Element) rootIter.next();
                String sqlType = e.getName().trim();
                String funcName = e.attributeValue("id").trim();
                String sql = e.getText().trim();
                String resultType = e.attributeValue("resultType").trim();
                function.setSqlType(sqlType);
                function.setFuncName(funcName);
                Object newInstance = null;
                try{
                    newInstance = Class.forName(resultType).newInstance();
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e1){
                    e1.printStackTrace();
                }

                function.setResultType(newInstance);
                function.setSql(sql);
                list.add(function);
            }
            mapper.setList(list);
        } catch (DocumentException e){
            e.printStackTrace();
        }

        return mapper;
    }

    private Element getRootNode(String path) throws DocumentException {
        InputStream stream = loader.getResourceAsStream(path);
        SAXReader reader = new SAXReader();
        Document document = reader.read(stream);
        return document.getRootElement();
    }
}
