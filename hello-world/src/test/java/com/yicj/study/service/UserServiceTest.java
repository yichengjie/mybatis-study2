package com.yicj.study.service;

import com.yicj.study.Application;
import com.yicj.study.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ClassName: UserServiceTest
 * Description: TODO(描述)
 * Date: 2020/8/16 19:54
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
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
}