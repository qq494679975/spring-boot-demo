package demo.cwd.async.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2018/1/18.
 */
@Service
public class AsyncService {

    @Autowired
    private AsyncJob asyncJob;

    /**
     * 加在方法上面无效
     *
     * @throws Exception
     */
    public void errorDemo() throws Exception {
        errorAsyncJob();
    }

    @Async
    public void errorAsyncJob() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    @Async("dbExtractExecutor")
    public void successDemo() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(1000);
        long currentTimeMillis1 = System.currentTimeMillis();
        System.out.println("task任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
    }

    public void successDemo2() throws Exception {

        asyncJob.task1();
    }
}
