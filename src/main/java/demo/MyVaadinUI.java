package demo;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
public class MyVaadinUI extends UI{
	
	private static final long serialVersionUID = 1L;

	@Autowired
    TodoRepository repository;
	
	TextField todofilter = new TextField();
    Grid todoList = new Grid();
    Button newTodo = new Button("New todo");
    
	@Override
	protected void init(VaadinRequest vaadinRequest){
		configureComponents();
        buildLayout();
	}
	
	private void configureComponents() {
		newTodo.addClickListener(e-> doNothing(todofilter.getValue()));
		todofilter.setInputPrompt("Filter Todos...");
		
		todoList.setContainerDataSource(new BeanItemContainer<>(Todo.class));
		
		
		refreshTodos();
	}
	
	private void buildLayout() {
		HorizontalLayout actions = new HorizontalLayout(todofilter, newTodo);
        actions.setWidth("100%");
        todofilter.setWidth("100%");
        actions.setExpandRatio(todofilter, 1);
        
        VerticalLayout left = new VerticalLayout(actions, todoList);
        left.setSizeFull();
        todoList.setSizeFull();
        left.setExpandRatio(todoList, 1);
        
		setContent(left);
		
		
	}
	
	
	public void refreshTodos(){
		todoList.setContainerDataSource(new BeanItemContainer<>(Todo.class, StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList())));
	}
	
	// dummy helpers
	
	
	private void doNothing(String txt){
		
		Todo todo = new Todo();
		todo.setSubject("test subject");
		Consumer<Todo> postSaveAction = t-> {
			System.out.println("Post save action called: " + t);
			repository.save(t);
			refreshTodos();
		};
		
		TodoDialog dialog = new TodoDialog(todo,postSaveAction);
		addWindow(dialog);
		
		
		
	}
}
