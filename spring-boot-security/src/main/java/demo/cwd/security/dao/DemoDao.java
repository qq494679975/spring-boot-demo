package demo.cwd.security.dao;

import demo.cwd.security.model.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2018/1/12.
 */
public interface DemoDao extends PagingAndSortingRepository<Demo, Long> {

}
