package com.swmu.bszn.platform.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author seven.mu
 * @Description: druid监控视图配置
 * @date 2019/1/25
 */
@WebServlet(urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "loginUsername", value = "admin"),// 用户名
                @WebInitParam(name = "loginPassword", value = "swmu@123"),// 密码
                @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
        })
public class DruidStatViewServlet extends StatViewServlet {
}
