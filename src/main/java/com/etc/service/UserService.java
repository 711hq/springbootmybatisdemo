package com.etc.service;

import com.etc.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    //分页查询  pageNum表示当前页
    public PageInfo<User> findPage(Integer pageNum,Integer pageSize);

    List<User> find();
    User selectByUid(int uid);
    User loginquery(User u);
    void add(User u);
    void mod(User u);
    void remove(int uid);
}
