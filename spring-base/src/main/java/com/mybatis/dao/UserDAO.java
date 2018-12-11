package com.mybatis.dao;

import com.mybatis.bo.UserBO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDAO {
    UserBO query(@Param("age") Integer age);
}
