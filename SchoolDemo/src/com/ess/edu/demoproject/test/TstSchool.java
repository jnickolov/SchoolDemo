package com.ess.edu.demoproject.test;

import com.ess.edu.demoproject.ctrl.KlassListController;
import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.entities.School;
import com.ess.mvc.ctrl.IListController;
import com.ess.mvc.view.ListView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TstSchool  extends Application {
	School school;
	
	public static void main(String[] args) {
		
		launch();

	}

	@Override
	public void start (Stage primaryStage) throws Exception {
		school = new School ("Geo Milev");
		school.getKlasses().add(new Klass (11,'a'));
		school.getKlasses().add(new Klass (11,'b'));
		school.getKlasses().add(new Klass (11,'c'));
		school.getKlasses().add(new Klass (11,'d'));
		
		Pane root = new Pane();
		
		IListController ctrl = new KlassListController (school);
		ListView view = new ListView (ctrl);
		
		root.getChildren().add(view.getNode());
		
		Scene scn = new Scene (root, 400, 400);
		primaryStage.setScene (scn);
		primaryStage.show();

		ctrl.addView (view, true);
	}

}
