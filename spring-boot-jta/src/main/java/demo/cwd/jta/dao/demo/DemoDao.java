package demo.cwd.jta.dao.demo;

import demo.cwd.jta.model.demo.Demo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2018/1/12.
 */
public interface DemoDao extends PagingAndSortingRepository<Demo, Long> {

    @Modifying
    @Query(" update Demo set name=?2 where id=?1")
    int updateDemoName(Long id, String name);
}
