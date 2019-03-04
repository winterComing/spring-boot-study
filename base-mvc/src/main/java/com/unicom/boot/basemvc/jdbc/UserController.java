package com.unicom.boot.basemvc.jdbc;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author dengh
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDaoImpl;

    @PostConstruct
    public void postConstruct(){
        System.out.println("执行post-Construct");
    }

    @PostMapping("/saveUser")
    @ResponseBody
    public void saveUser(@RequestBody User user){

        logger.info("----------saveUser-------------");
        userDaoImpl.saveUser(user);

        logger.info("----------queryUserByName------");
        List<User> userList = userDaoImpl.queryUserByName("dengh");
        logger.info("result:---" + JSONObject.toJSONString(userList));

        logger.info("----------queryBetweenAge------");
        List<User> userList1 = userDaoImpl.queryUserBetweenAge("10", "30");




    }

}
