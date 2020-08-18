package com.yicj.study.util;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import java.lang.reflect.Proxy;

/**
 * ClassName: PluginUtil
 * Description: TODO(描述)
 * Date: 2020/8/18 13:57
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class PluginUtil {

    private PluginUtil(){}

    public static Object processTarget(Object target){
        if (Proxy.isProxyClass(target.getClass())){
            MetaObject mo = SystemMetaObject.forObject(target) ;
            return processTarget(mo.getValue("h.target")) ;
        }
        return target ;
    }
}