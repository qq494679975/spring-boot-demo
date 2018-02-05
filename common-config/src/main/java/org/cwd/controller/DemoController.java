package org.cwd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenweida on 2017/8/5.
 */
@RestController
@RequestMapping(value = "/demo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "demo")
public class DemoController {

    @ApiOperation("例子")
    @GetMapping(value = "/getdemo")
    public String getdemo() {

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
