package demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@SpringComponent
@UIScope
public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findBySubject(String subject);
}
