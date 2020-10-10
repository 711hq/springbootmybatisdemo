package com.etc.dao;


import com.etc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    //方法一：使用XML映射文件
    //方法二：使用注解@Select("sql语句")
/*
     @Select("select * from user order by uid desc")
     List<User> find();


     @Select("select * from user where uid=#{uid}")
     User selectByUid(int uid);

     @Insert("insert into user values(default,#{uname},#{pwd})")
     @Options(useGeneratedKeys = true, keyProperty = "uid")
     void insert(User u);

     @Update("update user set uname=#{uname},pwd=#{pwd} where uid=#{uid}")
     void update(User u);

     @Delete("delete from user where uid=#{uid}")
     void delete(int uid);*/
    List<User> find();
    User selectLogin(User u);
    User selectByUid(int uid);

    void insert(User u);

    void update(User u);

    void delete(int uid);

}
