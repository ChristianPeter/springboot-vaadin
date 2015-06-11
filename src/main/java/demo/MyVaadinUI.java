package demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.*;
import com.vaadin.spring.annotation.SpringUI;

import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class MyVaadinUI extends UI{
	
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
		newTodo.addClickListener(e-> doNothing("TEST"));
		todofilter.setInputPrompt("Filter Todos...");
	}
	
	private void buildLayout() {
		HorizontalLayout actions = new HorizontalLayout(todofilter, newTodo);
        actions.setWidth("100%");
        todofilter.setWidth("100%");
        actions.setExpandRatio(todofilter, 1);
        
		setContent(actions);
	}
	
	// dummy helpers
	
	private void doNothing(String txt){
		System.out.println("Do Nothing called :  " + txt);
		System.out.println("REP: " + repository);
		repository.save(new Todo("Test"));
		
		System.out.println(repository.findAll());
	}
}
