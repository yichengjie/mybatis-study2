package com.yicj.study.hello;

import com.mysql.jdbc.Driver;
import java.sql.DriverManager;

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
    public void hello(String name, String age) throws Exception {
        Class.forName("com.mysql.jdbc.Driver") ;
        //Driver driver = new Driver();
        //DriverManager.registerDriver(driver);
        DriverManager.getConnection("") ;

    }
    /*public static void main(String[] args) throws NoSuchMethodException {
        Method method = HelloWorld.class.getMethod("hello", String.class, String.class);
        Parameter[] parameters = method.getParameters();
        for (Parameter p: parameters){
            System.out.println(p.getName());
        }
    }*/
}