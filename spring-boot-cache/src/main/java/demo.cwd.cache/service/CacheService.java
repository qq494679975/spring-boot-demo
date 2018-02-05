package demo.cwd.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenweida on 2018/1/18.
 */
@Service
@CacheConfig(cacheNames = "demo") //存在redis中 是 demo::wlyy:cache:key
public class CacheService {
    @Autowired
    private CacheManager cacheManager;

    @Cacheable
    public String getOne(String key) {
        String value = "查询key:" + key + "," + new Date().getTime();
        System.out.println(value);
        return value;
    }

   @CachePut
    public String cachePutTest(String key) {
        String value = "查询key:" + key + "," + new Date().getTime();
        System.out.println(value);
        return value;
    }

    public String getToCache(String key) {
        return cacheManager.getCache("demo").get(key, String.class);
    }

    @CacheEvict(allEntries = true)  //清空的是dmeo下的数据
    public String cleanCache() {
        return "清空成功";
    }
}
