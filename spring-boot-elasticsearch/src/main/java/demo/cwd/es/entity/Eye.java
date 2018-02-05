package demo.cwd.es.entity;

/**
 * Created by chenweida on 2018/1/11.
 */
public class Eye {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Eye() {
    }

    public Eye(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Eye{" +
                "name='" + name + '\'' +
                '}';
    }
}
