package demo.cwd.jdbc.durid.model;

import javax.persistence.*;

/**
 * Created by chenweida on 2018/1/12.
 */
@Entity
@Table(name = "demo")
public class Demo {
    private Long id;
    private String name;

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
}
