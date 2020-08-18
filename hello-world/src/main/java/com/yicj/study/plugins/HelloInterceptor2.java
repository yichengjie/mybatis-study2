package com.yicj.study.plugins;

import com.yicj.study.util.PluginUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;

/**
 * ClassName: HelloInterceptor2
 * Description: TODO(描述)
 * Date: 2020/8/18 14:00
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
@Slf4j
@Component
@Intercepts({
    @Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
    )
})
public class HelloInterceptor2 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (!"prepare".equals(invocation.getMethod().getName())){
            return invocation.proceed() ;
        }
        StatementHandler handler =  (StatementHandler)PluginUtil.processTarget(invocation.getTarget()) ;
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        //原始sql
        String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
        //新sql
        String newSql =  transformSql(originalSql);
        // 替换原sql
        metaObject.setValue("delegate.boundSql.sql", newSql);
        Object result = invocation.proceed();
        return result ;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler){
            return Plugin.wrap(target, this) ;
        }
        return target ;
    }

    /**
     * 对原sql进行特殊处理
     * @param originalSql
     * @return
     */
    private String transformSql(String originalSql){
        // TODO 处理原sql
        return originalSql ;
    }
}