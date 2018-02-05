package demo.cwd.aop.controller;

import demo.cwd.aop.aop.annotation.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestController
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DemoController {


    @RequestMapping(value = "/simpleAOP", method = RequestMethod.GET)
    @ApiOperation("普通的AOP例子")
    public String demo(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {

        return "asd";
    }

    @Demo
    @RequestMapping(value = "/annotationAOP", method = RequestMethod.GET)
    @ApiOperation("只拦截注解的AOP例子")
    public String demo1(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {

        return "asd";
    }
}