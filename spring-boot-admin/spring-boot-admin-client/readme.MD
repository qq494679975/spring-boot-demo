具体的描述：


|ID	        | 描述	| 敏感（Sensitive）|
|--|--|
|autoconfig	| 显示一个auto-configuration的报告，该报告展示所有auto-configuration候选者及它们被应用或未被应用的原因	| true|
|beans	   | 显示一个应用中所有Spring Beans的完整列表	| true|
|configprops	| 显示一个所有@ConfigurationProperties的整理列表	| true|
|dump	| 执行一个线程转储	| true|
|env	| 暴露来自Spring　ConfigurableEnvironment的属性	| true|
|health	| 展示应用的健康信息（当使用一个未认证连接访问时显示一个简单的’status’，使用认证连接访问则显示全部信息详情）| 	false|
|info	| 显示任意的应用信息| 	false|
|metrics| 	展示当前应用的’指标’信息	| true|
|mappings| 	显示一个所有@RequestMapping路径的整理列表| 	true|
|shutdown| 	允许应用以优雅的方式关闭（默认情况下不启用）| 	true|
|trace	| 显示trace信息（默认为最新的一些HTTP请求）| 	true|

**health**

比如：http://localhost:7231/health
你可以得到结果

{
    status: "UP",
}

在应用配置加上

endpoints.health.sensitive=false
1
在次访问http://localhost:7231/health

{
    status: "UP",
    diskSpace: {
        status: "UP",
        free: 32516145152,
        threshold: 10485760
    },
    db: {
        status: "UP",
        database: "Microsoft SQL Server",
        hello: 1440729256277
    }
}

可以检查的其他一些情况的健康信息。下面的HealthIndicators会被Spring Boot自动配置（在合适的时候）：

|名称|	描述|
|--|--|
|DiskSpaceHealthIndicator	|低磁盘空间检测|
|DataSourceHealthIndicator|	检查是否能从DataSource获取连接|
|MongoHealthIndicator	|检查一个Mongo数据库是否可用（up）|
|RabbitHealthIndicator	|检查一个Rabbit服务器是否可用（up）|
|RedisHealthIndicator	|检查一个Redis服务器是否可用（up）|
|SolrHealthIndicator	|检查一个Solr服务器是否可用（up）|
自定义当然也可以，你可以注册实现了HealthIndicator接口的Spring beans，Health响应需要包含一个status和可选的用于展示的详情。

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealth implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
        return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }


**trace**

访问http://localhost:7231/trace可以看到结果，默认为最新的一些HTTP请求

[
    {
        timestamp: 1440728799269,
        info: {
            method: "GET",
            path: "/health",
                headers: {
                request: {
                    host: "localhost:7231",
                    connection: "keep-alive",
                    accept: "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
                    user-agent: "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36",
                    accept-encoding: "gzip,deflate,sdch",
                    accept-language: "zh-CN,zh;q=0.8,en;q=0.6",
                    ra-ver: "3.0.7",
                    ra-sid: "74E754D8-20141117-085628-93e7a4-1dd60b"
                },
                response: {
                    X-Application-Context: "executecount:integration:7231",
                    Content-Type: "application/json;charset=UTF-8",
                    Transfer-Encoding: "chunked",
                    Content-Encoding: "gzip",
                    Vary: "Accept-Encoding",
                    Date: "Fri, 28 Aug 2015 02:26:39 GMT",
                    status: "200"
                }
            }
        }
    }
]

看看 InMemoryTraceRepository，默认是100个事件，如果需要可以定义自己的 InMemoryTraceRepository 实例。如果需要，你可以创建自己的替代 TraceRepository 实现。

/**
 * In-memory implementation of {@link TraceRepository}.
 *
 * @author Dave Syer
 * @author Olivier Bourgain
 */
public class InMemoryTraceRepository implements TraceRepository {

    private int capacity = 100;

    private boolean reverse = true;

如果需要追踪其他的事件，你可以将一个TraceRepository注入到你的Spring Beans中。

**info**

当执行 http://localhost:7231/info 的时候，结果什么没有
但是，单加入加入一些配置

info.app.name=ecs
info.app.version=1.0.0

info.build.artifactId=@project.artifactId@
info.build.version=@project.version@

执行/info

{
    app: {
        version: "1.0.0",
        name: "ecs"
    },
    build: {
        artifactId: "execute-count-server",
        version: "0.0.3"
    }

/info 是用来在构建的时候，自动扩展属性的。对于Maven项目，可以通过 @..@ 占位符引用Maven的’project properties’。

**env**

通过/env可以访问环境信息

{
profiles: [
"integration"
],
servletContextInitParams: { },
    systemProperties: {
        java.runtime.name: "Java(TM) SE Runtime Environment",
        java.vm.version: "25.25-b02",
        java.vm.vendor: "Oracle Corporation",
        java.vendor.url: "http://java.oracle.com/",
        path.separator: ";",
        idea.launcher.port: "7532",
        java.vm.name: "Java HotSpot(TM) 64-Bit Server VM",
        file.encoding.pkg: "sun.io",
        user.country: "CN",
        user.script: "",
        sun.java.launcher: "SUN_STANDARD",
        sun.os.patch.level: "Service Pack 1",
        PID: "7416",
        java.vm.specification.name: "Java Virtual Machine Specification",

        ...

通过/env/{name:.*}可以访问特定的环境属性

比如：

http://localhost:7231/env/java.vm.name
1
Java HotSpot(TM) 64-Bit Server VM
1

**metrics**

/metrics显示了应用当前的指标信息

{
    mem: 348672,
    mem.free: 264831,
    processors: 4,
    instance.uptime: 1859584,
    uptime: 1870818,
    systemload.average: -1,
    heap.committed: 348672,
    heap.init: 124928,
    heap.used: 83840,
    heap: 1773568,
    threads.peak: 23,
    threads.daemon: 21,
    threads: 23,
    classes: 7269,
    classes.loaded: 7272,
    classes.unloaded: 3,
    gc.ps_scavenge.count: 7,
    gc.ps_scavenge.time: 379,
    gc.ps_marksweep.count: 2,
    gc.ps_marksweep.time: 283,
    httpsessions.max: -1,
    httpsessions.active: 0,
    datasource.primary.active: 0,
    datasource.primary.usage: 0,
    counter.status.200.autoconfig: 1,
    counter.status.200.dump.root: 1,
    counter.status.200.env: 2,
    counter.status.200.env.name:.-star-: 4,
    counter.status.200.env.root: 3,
    counter.status.200.health: 2,
    counter.status.200.info: 1,
    counter.status.200.mappings: 1,
    counter.status.200.trace: 1,
    counter.status.404.env.name:.-star-: 1,
    gauge.response.autoconfig: 43,
    gauge.response.dump.root: 89,
    gauge.response.env: 21,
    gauge.response.env.name:.-star-: 14,
    gauge.response.env.root: 17,
    gauge.response.health: 19,
    gauge.response.info: 5,
    gauge.response.mappings: 13,
    gauge.response.trace: 52
}


此处我们可以看到基本的 memory ， heap ， class loading ， processor 和 thread pool 信息，连同一些HTTP指标。在该实例
中，可以使用 /metrics/{name:.*} 访问单个属性。

系统内存总量（mem），单位:Kb
空闲内存数量（mem.free），单位:Kb
处理器数量（processors）
系统正常运行时间（uptime），单位:毫秒
应用上下文（就是一个应用实例）正常运行时间（instance.uptime），单位:毫秒
系统平均负载（systemload.average）
堆信息（heap，heap.committed，heap.init，heap.used），单位:Kb
线程信息（threads，thread.peak，thead.daemon）
类加载信息（classes，classes.loaded，classes.unloaded）
垃圾收集信息（gc.xxx.count, gc.xxx.time）
最大连接数（datasource.xxx.max）
最小连接数（datasource.xxx.min）
活动连接数（datasource.xxx.active）
连接池的使用情况（datasource.xxx.usage）
**dump**

/dump执行一个线程转储，这是一个例子。

[
    {
        threadName: "http-nio-7231-exec-10",
        threadId: 32,
        blockedTime: -1,
        blockedCount: 0,
        waitedTime: -1,
        waitedCount: 3,
        lockName: "java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@7cf8ed03",
        lockOwnerId: -1,
        lockOwnerName: null,
        inNative: false,
        suspended: false,
        threadState: "WAITING",
        stackTrace: [
            {
                methodName: "park",
                fileName: null,
                lineNumber: -2,
                className: "sun.misc.Unsafe",
                nativeMethod: true
            },

            ...

**mappings**

/mappings显示一个所有@RequestMapping路径的整理列表,一切尽在眼中。

{
    /webjars/**: {
        bean: "resourceHandlerMapping"
    },
    /**: {
        bean: "resourceHandlerMapping"
    },
    /**/favicon.ico: {
        bean: "faviconHandlerMapping"
    },
    {[/mappings],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}: {
        bean: "endpointHandlerMapping",
        method: "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    {[/info],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}: {
        bean: "endpointHandlerMapping",
        method: "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },
    {[/env/{name:.*}],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}: {
        bean: "endpointHandlerMapping",
        method: "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EnvironmentMvcEndpoint.value(java.lang.String)"
    },
    {[/env],methods=[GET],params=[],headers=[],consumes=[],produces=[],custom=[]}: {
        bean: "endpointHandlerMapping",
        method: "public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()"
    },

    ...

**autoconfig**

autoconfig显示一个auto-configuration的报告，该报告展示所有auto-configuration候选者及它们被应用或未被应用的原因

{
    positiveMatches: {
        AuditAutoConfiguration.AuditEventRepositoryConfiguration: [
        {
            condition: "OnBeanCondition",
            message: "@ConditionalOnMissingBean (types: org.springframework.boot.actuate.audit.AuditEventRepository; SearchStrategy: all) found no beans"
        }
        ],
        EndpointAutoConfiguration#autoConfigurationAuditEndpoint: [
        {
            condition: "OnBeanCondition",
            message: "@ConditionalOnBean (types: org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport; SearchStrategy: all) found the following [autoConfigurationReport] @ConditionalOnMissingBean (types: org.springframework.boot.actuate.endpoint.AutoConfigurationReportEndpoint; SearchStrategy: current) found no beans"
        }
        ],

    ...

**configprops**

/configprops显示一个所有@ConfigurationProperties的整理列表.

{
    management.health.status.CONFIGURATION_PROPERTIES: {
        prefix: "management.health.status",
        properties: {
        order: null
        }
    },
    spring.datasource.CONFIGURATION_PROPERTIES: {
        prefix: "spring.datasource",
        properties: {
            schema: null,
            data: null,
            xa: {
                dataSourceClassName: null,
                properties: { }
            },
            separator: ";",
            url: "jdbc:sqlserver://192.168.x.x:xxxx;DatabaseName=xxxx;sendStringParametersAsUnicode=false",
            platform: "all",
            continueOnError: false,
            jndiName: null,
            sqlScriptEncoding: null,
            password: "******",
            driverClassName: "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            initialize: false,
            username: "Monitor"
        }
    },

 ...