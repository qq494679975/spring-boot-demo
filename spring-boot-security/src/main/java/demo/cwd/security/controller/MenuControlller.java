package demo.cwd.security.controller;

import demo.cwd.security.dao.MenuDao;
import demo.cwd.security.model.Menu;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chenweida on 2018/1/12.
 */
@RestController
@RequestMapping("menu")
public class MenuControlller {
    @Autowired
    private MenuDao menuDao;

    @ApiOperation(value = "新增菜单权限")
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public String extractOneDate() {
        try {
            Menu menu = new Menu();
            menu.setUserId(1);
            menu.setUrl("/demo/*");
            menuDao.save(menu);
            return "保存成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "获取用户全部菜单")
    @RequestMapping(value = "/findMenus", method = RequestMethod.POST)
    public List<Menu> findMenus(
            @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam(value = "userId", required = true) Integer userId
    ) {
        try {

            return menuDao.findByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }
}
