package demo.cwd.redis.controller;

import demo.cwd.redis.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestController
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/redisDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RedisDemoController {
    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("redisDemo ")
    public SimpleResult<String> errorDemo(
            @ApiParam(name = "key", value = "测试", required = false) @RequestParam(value = "key", required = false) String key) throws Exception {
        Set<String> keys = redisTemplate.keys("questionnaire:prize:code:*");
        System.out.println(keys.size());
        return new SimpleResult<String>(redisTemplate.opsForValue().get(key));
    }

    @RequestMapping(value = "/addKety", method = RequestMethod.GET)
    @ApiOperation("redisDemo ")
    public SimpleResult<String> addKety(
            @ApiParam(name = "key", value = "测试", required = false) @RequestParam(value = "key", required = false) String key) throws Exception {
        redisTemplate.opsForValue().set(key, null);
        return new SimpleResult<String>("成功");
    }

}