package com.frog;

import static org.junit.Assert.assertTrue;

import com.frog.entity.User;
import com.frog.mapper.UserMapper;
import com.frog.utils.FrogSession;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void test1(){
        FrogSession frogSession = new FrogSession();
        UserMapper mapper = frogSession.getMapper(UserMapper.class);
        User user = mapper.getUserById("1");
        System.out.println(user);
    }
}
