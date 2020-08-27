package com.yicj.study.mapper;

import com.yicj.study.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Description: TODO(描述)
 * Date: 2020/8/16 19:38
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Mapper
public interface UserMapper {

    User selectById(Integer id) ;

    User select4Login(String username, String password) ;

    int insert(User user) ;
}