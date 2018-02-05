package demo.cwd.shell.service;

import org.springframework.stereotype.Service;

/**
 * Created by chenweida on 2018/1/29.
 */
@Service
public class ShellService {

    public String shellService(String text) {
        System.out.println("你好");
        return "你好";
    }
}
