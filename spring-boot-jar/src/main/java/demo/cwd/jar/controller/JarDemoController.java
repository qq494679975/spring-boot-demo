package demo.cwd.jar.controller;

import demo.cwd.jar.vo.SimpleResult;
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
@RequestMapping(value = "/jarDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class JarDemoController {


    @RequestMapping(value = "/jarDemo", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public SimpleResult<Boolean> errorDemo(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {

        return new SimpleResult<>(false);
    }


}