package demo;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class TodoForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	TextField subject = new TextField();
	Button save = new Button("Save");
	Button cancel = new Button("Cancel");

	Todo todo;

	public TodoForm() {
		configureComponents();
		buildLayout();
	}

	private void buildLayout() {
		// TODO Auto-generated method stub

	}

	private void configureComponents() {
		setSizeUndefined();
		setMargin(true);

		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		actions.setSpacing(true);

		addComponents(actions, subject);

	}

}
