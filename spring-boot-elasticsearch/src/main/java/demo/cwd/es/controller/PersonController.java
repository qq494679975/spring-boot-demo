package demo.cwd.es.controller;

import demo.cwd.es.dao.PersonDao;
import demo.cwd.es.entity.Eye;
import demo.cwd.es.entity.Person;
import demo.cwd.es.util.ElasticsearchUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenweida on 2018/1/11.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private ElasticsearchUtil elasticsearchUtil;
    @Autowired
    private PersonDao personDao;

    @ApiOperation(value = "保存数据")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String extractOneDate(
            @ApiParam(name = "name", value = "名字", required = true) @RequestParam(value = "name", required = true) String name) {
        try {
            Person person = new Person();
            person.setName(name);
            person.setBirthday(new Date());
            Eye eye1 = new Eye("左眼");
            Eye eye2 = new Eye("右眼");

            List<Eye> list = new ArrayList<>();
            list.add(eye1);
            list.add(eye2);
            person.setEyes(list);

            personDao.save(person);
            return "保存成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "查找全部数据")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll() {
        try {
            Iterable<Person> persons = personDao.findAll();
            persons.forEach(one -> {
                System.out.println(one);
            });
            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @ApiOperation(value = "执行sql")
    @RequestMapping(value = "/excuteSQL", method = RequestMethod.GET)
    public String excuteSQL(
            @ApiParam(name = "sql", value = "sql", required = true) @RequestParam(value = "sql", required = true) String sql) {
        try {
            return elasticsearchUtil.excuteDataModel(sql).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "查找单个数据数据")
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public String findById(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id
    ) {
        try {
            Person person = personDao.findOne(id);
            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "查找单个数据数据")
    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public String findByName(
            @ApiParam(name = "name", value = "name", required = true) @RequestParam(value = "name", required = true) String name
    ) {
        try {
            List<Person> persons = personDao.findByName(name);

            persons.forEach(one -> {
                System.out.println(one);
            });

            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "删除单个数据数据")
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public String deleteById(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id
    ) {
        try {
            personDao.delete(id);
            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "查找单个数据数据")
    @RequestMapping(value = "/updateName", method = RequestMethod.GET)
    public String updateName(
            @ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) String id,
            @ApiParam(name = "name", value = "名字", required = true) @RequestParam(value = "name", required = true) String name
    ) {
        try {
            Person person = personDao.findOne(id);
            person.setName(name);
            List<Eye> eyes = person.getEyes();
            eyes.get(0).setName("修改之后的眼睛");
            personDao.save(person);
            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "分页查询数据")
    @RequestMapping(value = "/pagePerson", method = RequestMethod.GET)
    public String pagePerson(
            @ApiParam(name = "page", value = "page 从0开始", required = true) @RequestParam(value = "page", required = true) Integer page,
            @ApiParam(name = "size", value = "size", required = true) @RequestParam(value = "size", required = true) Integer size
    ) {
        try {
            PageRequest pageable = new PageRequest(page, size);
            Page<Person> personPage = personDao.findAll(pageable);
            System.out.println("TotalPages:" + personPage.getTotalPages());
            System.out.println("TotalElements:" + personPage.getTotalElements());
            System.out.println("Content:" + personPage.getContent());
            System.out.println("ContentSize:" + personPage.getContent().size());

            return "查询成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
