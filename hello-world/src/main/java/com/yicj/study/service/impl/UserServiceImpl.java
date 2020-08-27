package com.yicj.study.service.impl;

import com.yicj.study.entity.User;
import com.yicj.study.mapper.UserMapper;
import com.yicj.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Description: TODO(描述)
 * Date: 2020/8/16 19:42
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper ;

    private final SqlSessionTemplate sqlSessionTemplate ;

    private final SqlSessionFactory sqlSessionFactory ;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }



    @Override
    public User select4Login(String username, String password) {
        return userMapper.select4Login(username, password);
    }

    @Override
    public void queryFlux(Integer id) {
        sqlSessionTemplate.select("selectById", id, resultContext -> {
            final User user = (User) resultContext.getResultObject();
            log.info("---> {}", user);
        });
    }

    @Override
    public void batchAdd(List<User> list) {
        //通过sqlSessionFactory指定执行器的类型为BATCH,自动提交设置为false
        SqlSession session =
                sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        // 注意这里不要直接使用userMapper，
        // 因为userMapper中的SqlSession是SqlSessionTemplate对象，而不是DefaultSqlSession
        UserMapper mapper = session.getMapper(UserMapper.class);
        try {
            for (int i = 0 ; i < list.size() ; i++){
                mapper.insert(list.get(i));
                //this.insert(list.get(i)) ;
                if( (i == list.size() -1) || (i %100 ==0 && i != 0)){
                    // 手动每100个执行一次提交，提交后无法回滚
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
        }catch (Exception e){
            session.rollback();
        }finally {
            session.close();
        }
    }


    // 注意这里一定要有事务，否则将SqlSessionTemplate将每条一提交
    @Transactional
    @Override
    public void batchAdd2(List<User> list) {
        SqlSessionTemplate sqlSessionTemplate =
                new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH) ;
        UserMapper mapper = sqlSessionTemplate.getMapper(UserMapper.class);
        for (User user: list){
            mapper.insert(user);
        }
    }
}