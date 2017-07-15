package com.ess.edu.demoproject;

import com.ess.edu.demoproject.ctrl.KlassListController;
import com.ess.edu.demoproject.ctrl.StudentController;
import com.ess.edu.demoproject.ctrl.StudentListController;
import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.entities.School;
import com.ess.edu.demoproject.ui.SchoolUIFactory;
import com.ess.edu.demoproject.util.Configurator;
import com.ess.edu.demoproject.util.SchoolLoader;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SchoolDemo extends  Application {
	private School school = null;
	private KlassListController klassListController = null;
	private StudentListController studentListController = null;
	private StudentController studentController = null;
	
	public KlassListController getKlassListController() {
		return this.klassListController;
	}

	public void setKlassListController(KlassListController klassListController) {
		this.klassListController = klassListController;
	}

	public StudentListController getStudentListController() {
		return this.studentListController;
	}

	public void setStudentListController(StudentListController studentListController) {
		this.studentListController = studentListController;
	}

	public StudentController getStudentController() {
		return this.studentController;
	}

	public void setStudentController(StudentController studentController) {
		this.studentController = studentController;
	}

	public School getSchool() {
		return this.school;
	}

	public void setSchool (School school) {
		this.school = school;
		if (school != null && this.klassListController != null) {
			this.klassListController.setSchool (school);
			SchoolUIFactory.displaySchoolName(school.getName());
		}
	}
	
	@Override
	public void start (Stage stage) throws Exception {
		SchoolLoader.application = this;
		Pane root = SchoolUIFactory.createUI (this);
		
		klassListController.selectedIdProperty().addListener (new ChangeListener<Number>() {
			@Override
			public void changed (ObservableValue<? extends Number> arg0, Number arg1, Number newKlassId) {
				Klass k = school.getKlassById(newKlassId.intValue());
				studentListController.setKlass (k);
			}
		});
		
		Scene scn = new Scene (root);
		stage.setScene (scn);
		//stage.setFullScreen (true);
		stage.show();
	}

	public static void main (String[] args) {
		System.out.println("Start");
		try {
			Configurator.configureApplication();
			Application.launch (args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
