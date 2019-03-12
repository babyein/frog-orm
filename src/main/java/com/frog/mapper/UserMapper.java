package com.frog.mapper;

import com.frog.entity.User;

/**
 * //TODO
 *
 * @author Kudou Shinichi
 * @version 1.0
 **/
public interface UserMapper {
    /**
     * 根据用户ID获取
     * @param id 用户id
     * @return User对象
     */
    User getUserById(String id);
}
