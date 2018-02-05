package demo.cwd.security.dao;

import demo.cwd.security.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by chenweida on 2018/1/24.
 */
public interface UserDao extends PagingAndSortingRepository<User, Long> {
    @Query("from User where account=?1")
    User finByAccount(String username);
}
