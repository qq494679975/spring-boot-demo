package demo.cwd.data.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * Created by chenweida on 2018/1/12.
 */
@Entity
@Table(name = "demo")
@ApiModel("demo模型")
public class Demo {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;
    @JsonIgnore
    @ApiModelProperty("查询不返回")
    private String ignore;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Transient
    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }
}
