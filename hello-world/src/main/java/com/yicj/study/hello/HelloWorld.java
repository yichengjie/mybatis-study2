package com.yicj.study.hello;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * ClassName: Hello
 * Description: TODO(描述)
 * Date: 2020/8/20 22:33
 *
 * @author yicj(626659321 @ qq.com)
 * 修改记录
 * @version 产品版本信息 yyyy-mm-dd 姓名(邮箱) 修改信息
 */
public class HelloWorld {
    public void hello(String name, String age){}
    /*public static void main(String[] args) throws NoSuchMethodException {
        Method method = HelloWorld.class.getMethod("hello", String.class, String.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter p: parameters){
            System.out.println(p.getName());
        }
    }*/
}