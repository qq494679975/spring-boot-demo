package demo.cwd.jpa.h2.controller;

import demo.cwd.jpa.h2.dao.DemoDao;
import demo.cwd.jpa.h2.model.Demo;
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
            demo = demoDao.save(demo);
            System.out.println(demo);
            return "保存成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "查找数据")
    @RequestMapping(value = "/findALL", method = RequestMethod.GET)
    public Demo findALL(
            @ApiParam(name = "id", value = "名字", required = true) @RequestParam(value = "id", required = true) Long id) {
        try {
            Demo demo = demoDao.findOne(id);
            System.out.println(demo);
            return demo;
        } catch (Exception e) {
            return null;
        }
    }
}
