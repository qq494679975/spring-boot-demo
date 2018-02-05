package demo.cwd.jta.dao.test;

import demo.cwd.jta.model.test.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2018/1/30.
 */
public interface TestDao extends PagingAndSortingRepository<Test, Long> {


//    @Modifying
//    @Query(" update Test set name= ?2  where id=?1 ")
//    int updateTestName(Long id, String name);


    @Query(" from Test ")
    List<Test> query();
}
