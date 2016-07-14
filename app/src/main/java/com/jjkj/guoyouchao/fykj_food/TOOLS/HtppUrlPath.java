package com.jjkj.guoyouchao.fykj_food.TOOLS;

/**
 * Created by guoyouchao on 16/6/22.
 */
public class HtppUrlPath {
    // 服务器地址
//     public static String serverIp = "http://192.168.1.42:8080";

     public static String serverIp = "http://192.168.1.100:8080";

    //注册用户路径
    public static String registerUserPath = serverIp + "/FYKJ/login_register/register.jsp";

    // 手机号注册
    public static String registerByPhonePath = serverIp + "/FYKJ/login_register/registerByPhone.jsp";
    // 发送Email验证码路径
    public static String sendYZMPath = serverIp + "/FYKJ/login_register/sendYZMByEmail.jsp";

    // 发送短信验证码路径
    public static String sendYZMByPhone = serverIp +"/FYKJ/login_register/sendYZMByPhone.jsp";
    //登陆路径
    public static String loginPath = serverIp + "/FYKJ/login_register/login.jsp";

    // 手机号登陆
    public static String phoneLoginPath = serverIp +"/FYKJ/login_register/loginbyephone.jsp";
}
