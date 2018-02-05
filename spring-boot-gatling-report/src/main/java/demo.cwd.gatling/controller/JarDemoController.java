package demo.cwd.gatling.controller;

import demo.cwd.gatling.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/jarDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JarDemoController {

    private Logger logger = LoggerFactory.getLogger(JarDemoController.class);


    @RequestMapping(value = "/systemOut", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public SimpleResult<Boolean> systemOut(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {

        System.out.println("System.out.println：");

        return new SimpleResult<>(false);
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public SimpleResult<Boolean> logger(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {

        logger.info("logger info ");
        return new SimpleResult<>(false);
    }

}