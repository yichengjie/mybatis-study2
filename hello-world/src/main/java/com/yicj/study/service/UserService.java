package com.yicj.study.service;

import com.yicj.study.entity.User;

/**
 * ClassName: UserService
 * Description: TODO(描述)
 * Date: 2020/8/16 19:41
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public interface UserService {

    User selectById(Integer id) ;

    User select4Login(String username, String password) ;

    void queryFlux(Integer id) ;
}