package com.yicj.study.service;

import com.yicj.study.Application;
import com.yicj.study.entity.User;
import com.yicj.study.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ParamNameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserServiceTest
 * Description: TODO(描述)
 * Date: 2020/8/16 19:54
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@RunWith(SpringRunner.class)
//SpringJUnit4ClassRunner
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private UserService userService ;

    @Test
    public void selectUserById(){
        Integer id = 1 ;
        User user = userService.selectById(id);
        System.out.println(user);
    }

    @Test
    public void select4Login(){
        String username ="yicj" ;
        String password = "123" ;
        User user = userService.select4Login(username, password);
        log.info("----> {}", user);
    }

    @Test
    public void queryFlux(){
        Integer id = 1 ;
        userService.queryFlux(id);
    }

    @Test
    public void batchAdd(){
        List<User> list = new ArrayList<>() ;
        for (int i = 0 ; i < 10; i++){
            User user = new User() ;
            user.setUsername("yicj"+(i+1));
            user.setPassword("123");
            user.setRoles("ROLE_USER");
            list.add(user);
        }
        userService.batchAdd(list);
    }

    @Test
    public void batchAdd2(){
        List<User> list = new ArrayList<>() ;
        for (int i = 0 ; i < 10; i++){
            User user = new User() ;
            user.setUsername("yicj"+(i+1));
            user.setPassword("123");
            user.setRoles("ROLE_USER");
            list.add(user);
        }
        userService.batchAdd2(list);
    }


    @Test
    public void getParamNames() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("selectById", Integer.class);
        int paramIndex = 0 ;
        String pname = ParamNameUtil.getParamNames(method).get(paramIndex);
        log.info("pname : {}", pname);
    }

    @Test
    public void getParamNames2() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("selectById", Integer.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter: parameters){
            String name = parameter.getName();
            log.info("name : {}", name);
        }
    }

}