package demo.cwd.data.rest.controller;

import demo.cwd.data.rest.dao.DemoDao;
import demo.cwd.data.rest.model.Demo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenweida on 2018/1/25.
 */
@RepositoryRestController
@RequestMapping("demo")
@Api(tags = "demo")
public class DemoRepositoryRestController {
    @Autowired
    private DemoDao demoDao;

    @RequestMapping(method = RequestMethod.GET, value = "/demoRepositoryRestController")
    @ResponseBody
    public ResponseEntity<Demo> getProducers() {
        return ResponseEntity.ok(demoDao.findByName("123"));
    }
}
