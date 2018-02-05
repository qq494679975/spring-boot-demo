package demo.cwd.security.service;

import demo.cwd.security.dao.MenuDao;
import demo.cwd.security.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.AntPathMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenweida on 2018/1/24.
 */
@Service("menuService")
@Primary
public class MenuService {
    @Autowired
    private MenuDao menuDao;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 验证用户是不是有菜单的权限
     * @param request
     * @param authentication
     * @return
     */
    public Boolean hasPerssion(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPerssion = false;
        if (principal instanceof UserDetails) {
            //获取用户账号
           // String account = ((UserDetails) principal).getUsername();

            //这边可以存在redis提高效率
            List<Menu> menus = menuDao.findByUserId(1);
            //获取用户全部权限
            for (Menu uri : menus) {
                if (antPathMatcher.match(uri.getUrl(), request.getRequestURI())) {
                    hasPerssion = true;
                    break;
                }
            }
        }
        return hasPerssion;
    }

    /**
     * 给用户添加默认菜单的权限
     */
    @PostConstruct
    @Transactional
    private void menuInit() {
        String[] urls = new String[]{
                "/menu/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/v2/**",
                "/security/**",
                "/api/**"
        };
        List<Menu> menus = new ArrayList<>();
        for (String url : urls) {
            Menu menu = new Menu();
            menu.setUserId(1);
            menu.setUrl(url);
            menus.add(menu);
        }
        menuDao.save(menus);
    }
}
