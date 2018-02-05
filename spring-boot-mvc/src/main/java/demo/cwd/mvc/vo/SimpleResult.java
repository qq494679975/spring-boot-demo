package demo.cwd.mvc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


@ApiModel(description = "获取单个实体信息返回")
public class SimpleResult<T> {

    @ApiModelProperty("错误代码")
    private int status;
    @ApiModelProperty("错误消息")
    private String msg;
    @ApiModelProperty(value = "返回时间戳")
    private long timestamp;
    @ApiModelProperty(value = "内容")
    private T data;

    public T getData() {
        return data;
    }

    public SimpleResult(T data) {
        this.data = data;
        timestamp = new Date().getTime();
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


}

