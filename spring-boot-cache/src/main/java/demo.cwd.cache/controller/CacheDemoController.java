package demo.cwd.cache.controller;

import demo.cwd.cache.service.CacheService;
import demo.cwd.cache.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/cacheDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CacheDemoController {
    @Autowired
    private CacheService cacheService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("redisDemo ")
    public SimpleResult<String> errorDemo(
            @ApiParam(name = "key", value = "测试", required = false) @RequestParam(value = "key", required = false) String key) throws Exception {

        return new SimpleResult<String>(cacheService.getOne(key));
    }

    @RequestMapping(value = "/getToCache", method = RequestMethod.GET)
    @ApiOperation("从缓存中取数据 ")
    public SimpleResult<String> getToCache(
            @ApiParam(name = "key", value = "测试", required = false) @RequestParam(value = "key", required = false) String key) throws Exception {

        return new SimpleResult<String>(cacheService.getToCache(key));
    }

    @RequestMapping(value = "/cleanCache", method = RequestMethod.GET)
    @ApiOperation("清空缓存的数据 ")
    public SimpleResult<String> cleanCache(
    ) throws Exception {

        return new SimpleResult<String>(cacheService.cleanCache());
    }

    @RequestMapping(value = "/cachePutTest", method = RequestMethod.GET)
    @ApiOperation("测试CachePut 每次都会调用 一般用于更新 返回结果也会存在redis")
    public SimpleResult<String> cachePutTest(
            @ApiParam(name = "key", value = "测试", required = false) @RequestParam(value = "key", required = false) String key
    ) throws Exception {

        return new SimpleResult<String>(cacheService.cachePutTest(key));
    }
}