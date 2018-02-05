package demo.cwd.statemachine.controller;

import demo.cwd.statemachine.config.Events;
import demo.cwd.statemachine.config.States;
import demo.cwd.statemachine.vo.SimpleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestController
@Api(tags = "测试模块", description = "测试")
@RequestMapping(value = "/statemachineDemo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StatemachineDemoController {
    @Autowired
    private StateMachine<States, Events> stateMachine;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("statemachine demo")
    public SimpleResult<Boolean> errorDemo(
            @ApiParam(name = "username", value = "测试", required = false) @RequestParam(value = "username", required = false) String username) throws Exception {


        stateMachine.sendEvent(Events.E1);

        stateMachine.sendEvent(Events.E2);

        return new SimpleResult(false);
    }


}