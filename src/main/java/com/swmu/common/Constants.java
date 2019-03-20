package com.swmu.common;

/**
 * @author YuanJingshan
 * @version 1.0
 * @description 常量定义
 * @date Create in 2018/12/28 13:09
 */
public class Constants {

    /**************** 错误码 ***************/
    /**
     * 后台验证不通过
     */
    public static final int CODE_ERROR_VALIDATION = 403;


    /****************用户角色 ***************/
    /**
     * 超级管理员
     */
    public static final String USER_ROLE_ADMIN="0";
    /**
     * 普通用户
     */
    public static final String USER_ROLE_NORMAL = "1";
    /**
     * 系统主ID
     */
    public static final String SYS_FUN_ID = "1001";
    /**
     * 首页ID
     */
    public static final String SYS_FUN_INDEX = "1002";
//    public static final String SYS_USER_ADMIN = "admin"; //系统管理员
//    public static final String RESET_PASSWORD = "111111"; //重置密码

    public static final String DEF_ENCODE = "UTF-8";
}
