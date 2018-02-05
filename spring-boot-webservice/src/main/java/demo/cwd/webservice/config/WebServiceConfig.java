//package demo.cwd.webservice.config;
//
//import demo.cwd.webservice.webservice.DemoWebService;
//import org.apache.cxf.Bus;
//import org.apache.cxf.endpoint.Endpoint;
//import org.apache.cxf.endpoint.EndpointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by chenweida on 2018/1/18.
// */
//@Configuration
//public class WebServiceConfig {
//    @Autowired
//    private Bus bus;
//
//    @Autowired
//    DemoWebService demoWebService;
//
//    /**
//     * JAX-WS
//     **/
//    @Bean
//    public Endpoint endpoint() {
//        EndpointImpl endpoint = new EndpointImpl(bus, demoWebService);
//        endpoint.publish("/DemoWebService");
//        return endpoint;
//    }
//
//}
