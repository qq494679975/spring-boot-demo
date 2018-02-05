package demo.cwd.swagger.controller;

import demo.cwd.swagger.vo.Demo;
import demo.cwd.swagger.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenweida on 2018/1/18.
 */

@Controller
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SwaggerDemoController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("测试swagger")
    public SimpleResult<Demo> testSwagger(
            @ApiParam(name = "username", value = "测试", required = true) @RequestParam(value = "username") String username) {


        return new SimpleResult<>(new Demo());
    }
}
