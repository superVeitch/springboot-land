package com.mybatis.bo;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
public class UserBO {

    private String name;
    private Integer age;
    private String sex;
    private Integer isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
