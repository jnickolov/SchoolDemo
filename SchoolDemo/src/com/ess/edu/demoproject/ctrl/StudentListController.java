package com.ess.edu.demoproject.ctrl;

import java.util.Collection;
import java.util.LinkedList;

import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.entities.Student;
import com.ess.edu.demoproject.ui.view.editor.DialogEditorFX;
import com.ess.edu.demoproject.ui.view.impl.fx.StudentView;
import com.ess.idname.IDName;
import com.ess.mvc.ctrl.ListController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class StudentListController extends ListController<IDName> {
	private Klass klass = null;
	private DialogEditorFX <Student> editor = null;

	public StudentListController () {
		this.klass = null;
		this.editor = new DialogEditorFX<Student> () {
			@Override
			public void editNew () {
				if (this.getController() == null)
					return;
				
				Student st = new Student ();
				this.edit (st);
			}
		};
		
		StudentController sc = new StudentController();
		StudentView sv = new StudentView ();
		sc.addView (sv, false);
		editor.setController (sc);
		editor.setView (sv);
		editor.modelUpdatedProperty().addListener (new ChangeListener<Boolean>() {
			@Override
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					Student st = (Student) editor.getController().getModel();
					int selId = st.getId();
					Student origStudent = klass.getStudentById (selId);
					if (origStudent != null) {
						origStudent.setFirstName (st.getFirstName());
						origStudent.setLastName (st.getLastName());
						origStudent.copyMarks (st);
						Collection <IDName> newModel = createModel();
						setModel (newModel);
						StudentListController.this.elementRenamed (st.getId(), st.getName());
						StudentListController.this.setSelectedId (selId);
					} else {
						klass.getStudents().add (st);
						Collection <IDName> newModel = createModel();
						setModel (newModel);
						updateViews();
						StudentListController.this.setSelectedId (st.getId());
					}
				}
			}
		});
		
	}
	
	public StudentListController (Klass c) {
		this();
		this.setKlass (c);
	}
	
	public Klass getKlass() {
		return this.klass;
	}
	public void setKlass (Klass k) {
		this.klass = k;
		this.setModel (this.createModel());
	}
	
	@Override
	public Collection <IDName> createModel() {
		Collection <IDName> mdl = new LinkedList <IDName> ();
		
		if (this.klass != null) {
			Collection <Student> students = this.klass.getStudents();
			for (Student c: students) {
				mdl.add (new IDName (c.getId(), c.getName()));
			}
		}
		return mdl;
	}

	@Override
	public void addNewElement() {
		editor.editNew();
	}

	@Override
	public void editElement (int id) {
		super.editElement (id);
		Student st = this.getKlass().getStudentById (id);
		if (st != null) {
			editor.edit (st);
		}
	}

	@Override
	public void deleteElement(int id) {
		// TODO Auto-generated method stub
		
	}


}
