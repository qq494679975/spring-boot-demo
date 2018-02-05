package demo.cwd.security.service;

import demo.cwd.security.dao.UserDao;
import demo.cwd.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by chenweida on 2018/1/24.
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.finByAccount(username);
    }

    /**
     * 添加默认用户
     */
    @PostConstruct
    @Transactional
    private void userInit() {
        User user = new User();
        user.setAccount("admin");
        user.setPassword("admin");
        user.setName("cwd");
        userDao.save(user);
    }
}
