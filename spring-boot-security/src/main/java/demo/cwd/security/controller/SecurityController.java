package demo.cwd.security.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenweida on 2018/1/25.
 */
@RestController
@RequestMapping("security")
public class SecurityController {

    @ApiOperation(value = "获取security东西")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String extractOneDate() {
        try {
            return "保存成功";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
