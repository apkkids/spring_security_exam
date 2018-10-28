package com.apkkids.mapper;

import com.apkkids.bean.MyUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by wxb on 2018/10/23 0023.
 * 数据库表user的mapper类
 */
@Mapper
@Component
public interface MyUserMapper {
    /**
     * 从数据库中查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    MyUserBean selectByUsername(@Param("username") String username);
}
