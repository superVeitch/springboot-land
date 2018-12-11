package com.mybatis.controller;


import com.mybatis.bo.UserBO;
import com.mybatis.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping
    @RequestMapping("/get")
    public String getUser(Integer age){
        UserBO ub = userDAO.query(age);
        return ub.toString();
    }
}
