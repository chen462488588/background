package com.sono.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;

import java.sql.*;

public class MyRealm extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        /**
         * 1.将token转换为UsernamePasswordToken
         *
         * 2.获取用户名
         *
         * 3.查询数据库，进行验证
         *
         * 4.结果返回
         *
         * 5.验证不通过，抛出异常
         */
        //1.将token转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        //2.获取用户名
        String userName = upToken.getUsername();
        //获取用户名后。通过查询用户名查询数据库是否有值，有值则进行密码验证。
        SimpleAuthenticationInfo info=null;
        //3。查询数据库
        //使用JDBC链接数据库进行查询
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/test";
            Connection conn= DriverManager.getConnection(url,"root","");
            PreparedStatement ps = conn.prepareStatement("select * from account where name=?");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                Object principal=userName;
                Object credentials=rs.getString(3);
                String realmName=this.getName();
                //SimpleHash sh=new SimpleHash(algorithmName, source, salt, iterations);
                //加密类型  加密资源        盐值加密      加密次数
                //给从数据库中拿到的密码做MD5的加密
                SimpleHash sh=new SimpleHash("MD5", credentials, null, 1024);
                //info = new SimpleAuthenticationInfo(principal, credentials, realmName);
                info = new SimpleAuthenticationInfo(principal, sh, realmName);
            }else{
                throw new AuthenticationException();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

}
