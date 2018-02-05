package demo.cwd.jta.controller;

import demo.cwd.jta.service.JTAService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource(name = "demoJdbcTemplate")
    private JdbcTemplate demoJdbcTemplate;

    @Resource(name = "testJdbcTemplate")
    private JdbcTemplate testJdbcTemplate;
    @Autowired
    private JTAService jtaService;

    @Transactional
    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public void test() {
        System.out.println("begin.....");
        demoJdbcTemplate.execute("INSERT INTO `demo`.`demo` (`id`, `name`, `ignore`) VALUES ('1', '123', NULL);");
        testJdbcTemplate.execute("INSERT INTO `test`.`test` (`id`, `name`, `czrq`) VALUES ('test', '测试', '2017-03-28 11:42:12');");
        System.out.println("end.....");
    }

    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public String errorDemo() throws Exception {
        jtaService.save();
        return "";
    }
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    @ApiOperation("jar demo")
    public String demo3() throws Exception {
        jtaService.update();
        return "";
    }
}
