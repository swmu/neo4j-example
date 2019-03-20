package com.swmu.bszn.platform.druid;


import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author seven.mu
 * @Description: 配置监控拦截器, druid监控拦截器
 * @date 2019/1/25
 */
@WebFilter(filterName = "druidWebStatFilter",
           urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {
}