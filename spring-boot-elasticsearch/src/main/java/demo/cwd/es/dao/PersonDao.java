package demo.cwd.es.dao;

import demo.cwd.es.entity.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by chenweida on 2018/1/11.
 */
public interface PersonDao extends ElasticsearchRepository<Person, String> {
    @Query("{\"bool\":{\"filter\":{\"bool\":{\"must\":{\"match\":{\"name\":{\"query\":\"?0\",\"type\":\"phrase\"}}}}}}}")
    List<Person> findByName(String name);

}
