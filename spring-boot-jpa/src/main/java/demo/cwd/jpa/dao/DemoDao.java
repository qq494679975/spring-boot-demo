package demo.cwd.jpa.dao;

import demo.cwd.jpa.model.Demo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2018/1/12.
 */
public interface DemoDao extends PagingAndSortingRepository<Demo, Long> {

}
