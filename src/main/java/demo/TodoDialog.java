package demo;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Window;

public class TodoDialog extends Window {

	private static final long serialVersionUID = 1L;

	Todo todo;

	// Easily bind forms to beans and manage validation and buffering
	BeanFieldGroup<Todo> formFieldBindings;

	public TodoDialog(Todo todo, Consumer<Todo> postSaveAction) {
		super("Todo");
		center();

		setModal(true);
		TodoForm content = new TodoForm();
		formFieldBindings = BeanFieldGroup.bindFieldsBuffered(todo, content);

		setContent(content);

		setClosable(false);

		this.todo = todo;

		content.save.addClickListener(e -> {
			try {
				System.out.println("Committing.");
				formFieldBindings.commit();
				postSaveAction.accept(todo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			close();
		});

	}
	
	public Todo getTodo() {
		return todo;
	}

}
