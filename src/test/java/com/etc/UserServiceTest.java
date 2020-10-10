package com.etc;

import com.etc.dao.UserDao;
import com.etc.entity.User;
import com.etc.service.UserService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Resource
    UserService userService;
    @Resource
    UserDao userDao;
    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void findUserTest(Integer pageNum,Integer pageSize){
        List<User> list = userService.find();
        for(User u:list){
            logger.info(u.getUid()+":uname:" + u.getUname() + ",password:" + u.getPwd());
        }
    }
//    @Test
//    public void booleanUserTest(Integer pageNum,Integer pageSize){
//        User user = new User(1, "丰旭龙", "123456");
//        Boolean flag = userDao.selectLogin(user);
//        System.out.println(flag);
//    }
}
