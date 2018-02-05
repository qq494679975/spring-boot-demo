package demo.cwd.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * Created by chenweida on 2018/1/18.
 */

@EnableCaching
@Configuration
public class CacheConfig {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    CacheManager cacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        cacheManager.setUsePrefix(true);//是否添加前缀
        cacheManager.setCachePrefix(new DefaultRedisCachePrefix(":wlyy:cache:"));
        cacheManager.setDefaultExpiration(60);//单位是秒 缓存60秒
        cacheManager.setLoadRemoteCachesOnStartup(true);
        return cacheManager;
    }
}
