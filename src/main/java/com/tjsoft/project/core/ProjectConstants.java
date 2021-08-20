package com.tjsoft.project.core;

/**
 * 常量类
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.core
 * @date 2021/8/11 16:06
 * @Copyright ©
 */
public class ProjectConstants {

    /**
     * 接口请求头key
     */
    public static final String TOKEN_HEADER_STRING = "Authroization";

    /**
     * 数据库类型-mysql
     */
    public static final String DATA_SOURCE_TYPE_MYSQL = "mysql";

    /**
     * 用户状态
     * 0 正常 1 删除 2 禁用
     */
    public static final String USER_STATUS_0 = "0";
    public static final String USER_STATUS_1 = "1";
    public static final String USER_STATUS_2 = "2";


    /**
     * 方法类型
     */
    public static final String OPERTYPE_INSERT = "INSERT";
    public static final String OPERTYPE_QUERY = "QUERY";
    public static final String OPERTYPE_UPDATE = "UPDATE";
    public static final String OPERTYPE_DELETE = "DELETE";
}
