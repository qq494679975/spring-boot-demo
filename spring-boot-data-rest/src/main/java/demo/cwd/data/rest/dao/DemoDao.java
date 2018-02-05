package demo.cwd.data.rest.dao;

import demo.cwd.data.rest.model.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by chenweida on 2018/1/12.
 */
@RepositoryRestResource(path = "demo")
@Api(tags = "demo")
public interface DemoDao extends PagingAndSortingRepository<Demo, Long> {
    /**
     * 根据用户名称查找用户
     */
    @ApiOperation("根据名字查找")
    @RestResource(path = "name")
    @Query("from Demo where name=?1")
    Demo findByName(@Param("name") String name);
}
