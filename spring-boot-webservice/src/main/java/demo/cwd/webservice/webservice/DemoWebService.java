package demo.cwd.webservice.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Created by chenweida on 2018/1/18.
 */
@WebService(

        name = "DemoWebService", // 暴露服务名称
        targetNamespace = "http://DemoWebService.webservice.webservice.cwd.demo/"// 命名空间,一般是接口的包名倒序)
)
public interface DemoWebService {
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    String getName(@WebParam(name = "userId") Long userId);
}
