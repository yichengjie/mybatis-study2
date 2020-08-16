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
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Autowired
    private UserService userService ;

    @Test
    public void selectUserById(){
        Integer id = 1 ;
        User user = userService.selectUserById(id);
        System.out.println(user);
    }


    @Test
    public void getParamNames() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("selectUserById", Integer.class);
        int paramIndex = 0 ;
        String pname = ParamNameUtil.getParamNames(method).get(paramIndex);
        log.info("pname : {}", pname);
    }

    @Test
    public void getParamNames2() throws NoSuchMethodException {
        Method method = UserMapper.class.getMethod("selectUserById", Integer.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter: parameters){
            String name = parameter.getName();
            log.info("name : {}", name);
        }
    }
}