package demo.cwd.webservice.webservice.impl;

import demo.cwd.webservice.webservice.DemoWebService;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Created by chenweida on 2018/1/18.
 */
@WebService(serviceName = "DemoWebService", // 与接口中指定的name一致
        targetNamespace = "http://DemoWebService.webservice.webservice.cwd.demo/",// 命名空间,一般是接口的包名倒序)
        endpointInterface = "demo.cwd.webservice.webservice.DemoWebService"// 接口地址
)
@Component
public class DemoWebServiceImpl implements DemoWebService {
    @Override
    public String getName(Long userId) {
        return "测试1";
    }
}
