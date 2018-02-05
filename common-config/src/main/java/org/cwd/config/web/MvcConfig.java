package org.cwd.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chenweida on 2017/4/6.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(MvcConfig.class);


}
