package org.csu.mypetstore.utils;
import org.springframework.util.DigestUtils;


/** * Created by yanshao on 2018/12/17. */
public class Md5Util {
    public static String encrypt(String password_web) {
//        //打印md5加密后的密码
        System.out.println("md5加密结果："+DigestUtils.md5DigestAsHex(password_web.getBytes()));
        return DigestUtils.md5DigestAsHex(password_web.getBytes());
    }
    public static void main(String []args){
        encrypt("lgz");
    }
}




