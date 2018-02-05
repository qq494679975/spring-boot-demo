package demo.cwd.jta.service;

import demo.cwd.jta.dao.demo.DemoDao;
import demo.cwd.jta.dao.test.TestDao;
import demo.cwd.jta.model.demo.Demo;
import demo.cwd.jta.model.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by chenweida on 2018/1/30.
 */
@Service
public class JTAService {
    @Autowired
    private DemoDao demoDao;
    @Autowired
    private TestDao testDao;

    @Transactional
    public void save() {
        Demo demo = new Demo();
        demo.setName("测试1");

        demoDao.save(demo);


        Test test = new Test();
        test.setName("测试1");
        test.setCzrq(new Date());

        testDao.save(test);
    }

    @Transactional
    public void update() {
        demoDao.updateDemoName(1L, "测试2");
        //testDao.updateTestName(1L, "测试2");
    }
}
