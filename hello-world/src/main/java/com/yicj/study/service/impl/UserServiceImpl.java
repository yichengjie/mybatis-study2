package com.yicj.study.service.impl;

import com.yicj.study.entity.User;
import com.yicj.study.mapper.UserMapper;
import com.yicj.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Description: TODO(描述)
 * Date: 2020/8/16 19:42
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper ;

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }
}