package demo.cwd.logback.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2018/1/16.
 */
@RestController
@RequestMapping("/log")
public class LogController {

    private Logger logger = LoggerFactory.getLogger("demo_logger");

    @ApiOperation(value = "输出日志")
    @RequestMapping(value = "/loginfo", method = RequestMethod.POST)
    public String loginfo() {

        logger.info("info 日志输出");


        return "";
    }
}
