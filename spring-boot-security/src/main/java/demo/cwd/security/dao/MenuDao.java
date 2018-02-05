package demo.cwd.security.dao;

import demo.cwd.security.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by chenweida on 2018/1/24.
 */
public interface MenuDao extends PagingAndSortingRepository<Menu, Long> {
    @Query("from Menu where userId=?1 ")
    List<Menu> findByUserId(Integer userId);
}
