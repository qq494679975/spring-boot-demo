package demo.cwd.swagger.exception;

import demo.cwd.swagger.vo.SimpleResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenweida on 2018/1/18.
 */
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public SimpleResult handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        System.out.println("进入异常界面处理");
        return new SimpleResult("发生异常了");
    }
}
