package com.etc.service.impl;


import com.etc.dao.UserDao;
import com.etc.entity.User;
import com.etc.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<User> findPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//通过PageHelper方法startPage()设置当前页及每页的显示的记录条数
        List<User> list = userDao.find();//2,查询出页面要显示的集合的数据
        PageInfo<User> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    //查询所有
    @Override
    public List<User> find() {
        return userDao.find();
    }

    //通过uid进行查询
    @Override
    public User selectByUid(int uid) {

        return userDao.selectByUid(uid);
    }

    @Override
    public User loginquery(User u) {
        return userDao.selectLogin(u);
    }


    //新增用户
    @Override
    public void add(User u) {
        userDao.insert(u);
    }
    //修改用户
    @Override
    public void mod(User u) {

        userDao.update(u);
    }

    //删除用户
    @Override
    public void remove(int uid) {
        userDao.delete(uid);
    }
}
