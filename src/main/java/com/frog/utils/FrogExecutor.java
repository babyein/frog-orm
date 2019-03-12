package com.frog.utils;

import com.frog.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public class FrogExecutor implements Executor{

    private FrogConfiguration frogConfiguration = new FrogConfiguration();

    /**
     * 向数据库发起查询
     * @param statement sql语句
     * @param parameter 查询传参
     * @param <T> 返回值类型
     * @return 由查询结果创建的对象
     */
    @Override
    public <T> T query(String statement, Object parameter) {
        Connection connection = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            ps = connection.prepareStatement(statement);

            // 设置参数
            ps.setString(1, parameter.toString());
            rs = ps.executeQuery();
            User u = new User();

            // 遍历结果集
            while (rs.next()){
                u.setId(rs.getString(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
            }

            return (T)u;
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try{
                if (rs != null){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (connection != null){
                    connection.close();
                }
            } catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取数据库连接
     * @return 数据库连接对象
     */
    private Connection getConnection() {
        try{
            Connection connection = frogConfiguration.build("dataSource.xml");
            return connection;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
