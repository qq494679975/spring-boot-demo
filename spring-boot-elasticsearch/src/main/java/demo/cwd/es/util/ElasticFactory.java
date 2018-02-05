package demo.cwd.es.util;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenweida on 2017/6/5.
 */
@Component
public class ElasticFactory {
    private static JestClientFactory factory = null;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Value("${spring.data.elasticsearch.cluster-nodes-jest}")
    private String esHost;//http://59.61.92.90:9065,http://59.61.92.90:9067
//-----------------------------------jestClient----------------------------------------

    /**
     * @param "http://localhost:9200"
     * @return
     */
    public JestClient getJestClient() {
        if (factory == null) {
            //初始化链接
            init();
        }
        return factory.getObject();
    }

    /**
     * 初始化链接
     * 9200
     */
    public synchronized void init() {
        String[] hostArray = esHost.split(",");
        // Construct a new Jest client according to configuration via factory
        factory = new JestClientFactory();
        HttpClientConfig httpClientConfig = new HttpClientConfig
                .Builder(Arrays.asList(hostArray))//http://59.61.92.90:9065,http://59.61.92.90:9067
                .multiThreaded(true)
                .maxTotalConnection(50)// 最大链接
                .maxConnectionIdleTime(120, TimeUnit.SECONDS)//链接等待时间
                .connTimeout(60 * 1000)
                // .discoveryEnabled(true)
                .readTimeout(60 * 1000)//60秒
                .build();


        factory.setHttpClientConfig(httpClientConfig);//得到链接
    }

    //-----------------------------------TransportClient----------------------------------------

    public Client getTransportClient() {
        try {
            return elasticsearchTemplate.getClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
