package demo.cwd.war.config;//package com.yihu.wlyy.config.war;

import demo.cwd.war.WarApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Administrator on 2016.10.14.
 */
public class WarConfig extends SpringBootServletInitializer {
    public WarConfig() {
        super();
        setRegisterErrorPageFilter(false); //报错不跳到错误页
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WarApplication.class);
    }
}
