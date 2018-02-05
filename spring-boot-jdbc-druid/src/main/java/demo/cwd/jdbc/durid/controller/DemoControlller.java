package demo.cwd.jdbc.durid.controller;

import demo.cwd.jdbc.durid.dao.DemoDao;
import demo.cwd.jdbc.durid.model.Demo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2018/1/12.
 */
@RestController
@RequestMapping("demo")
public class DemoControlller {
    @Autowired
    private DemoDao demoDao;

    @ApiOperation(value = "保存数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String extractOneDate(
            @ApiParam(name = "name", value = "名字", required = true) @RequestParam(value = "name", required = true) String name) {
        try {
            Demo demo = new Demo();
            demo.setName(name);
            demoDao.save(demo);
            return "保存成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
