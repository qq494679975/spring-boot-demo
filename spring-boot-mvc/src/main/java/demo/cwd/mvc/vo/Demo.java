package demo.cwd.mvc.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenweida on 2018/1/18.
 */

@ApiModel(description = "测试swagger生成")
public class Demo {

    @ApiModelProperty("姓名")
    private String name="cwd";

    @ApiModelProperty("年龄")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
