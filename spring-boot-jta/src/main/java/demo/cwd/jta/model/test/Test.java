package demo.cwd.jta.model.test;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenweida on 2018/1/30.
 */
@Entity
@Table(name = "test")
public class Test {
    private Long id;

    private String name;

    private Date czrq;

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

    public Date getCzrq() {
        return czrq;
    }

    public void setCzrq(Date czrq) {
        this.czrq = czrq;
    }

    public static void main(String[] args) {
        System.out.println("ae72bca3040249bfa68e26a2f869f2bf".length());
    }
}
