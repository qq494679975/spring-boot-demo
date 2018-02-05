package demo.cwd.async.controller;

import demo.cwd.async.service.AsyncJob;
import demo.cwd.async.service.AsyncService;
import demo.cwd.async.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestController
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/aync", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    @Autowired
    private AsyncJob asyncJob;


    @RequestMapping(value = "/errorDemo", method = RequestMethod.GET)
    @ApiOperation("错误例子 测试swagger")
    public SimpleResult<Boolean> errorDemo(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        asyncService.errorDemo();
        long currentTimeMillis1 = System.currentTimeMillis();


        System.out.println("task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new SimpleResult<>(false);
    }


    @ApiOperation("正确例子1 测试swagger")
    @RequestMapping(value = "/successDemo1", method = RequestMethod.GET)
    public SimpleResult<Boolean> successDemo1(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        asyncJob.task1();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new SimpleResult<>(true);
    }

    @ApiOperation("正确例子2 测试swagger")
    @RequestMapping(value = "/successDemo2", method = RequestMethod.GET)
    public SimpleResult<Boolean> successDemo2(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        asyncService.successDemo();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new SimpleResult<>(true);
    }

    @ApiOperation("正确例子3 测试swagger")
    @RequestMapping(value = "/successDemo3", method = RequestMethod.GET)
    public SimpleResult<Boolean> successDemo3(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        asyncService.successDemo2();
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务总耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new SimpleResult<>(true);
    }
}