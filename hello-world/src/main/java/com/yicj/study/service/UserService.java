package com.yicj.study.service;

import com.yicj.study.entity.User;

import java.util.List;

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

    /**
     * 流式查询（避免oom）
     * @param id
     */
    void queryFlux(Integer id) ;

    /**
     * 批量添加
     * @param list
     */
    void batchAdd(List<User> list) ;

    /**
     * 批量添加：方式二
     * @param list
     */
    void batchAdd2(List<User> list) ;
}