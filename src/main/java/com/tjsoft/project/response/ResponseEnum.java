package com.tjsoft.project.response;

/**
 * @author sefo
 * @version V1.0
 * @Package com.fjm.project.core
 * @date 2021/7/27 11:25
 * @Copyright ©
 */
public enum ResponseEnum {
    //响应枚举
    REJECT_LOGIN(403, "拒绝访问"),
    NO_LOGIN(401, "未登录"),
    SUCCESS_LOGIN(200, "登陆成功"),
    FAIL_LOGIN(500, "登陆失败"),
    SUCCESS_LOGOUT(200, "登出成功"),
    TOKEN_EXPIRE(505, "Token失效");

    private int code;
    private String msg;



    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
