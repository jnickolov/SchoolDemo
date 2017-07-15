package com.ess.edu.demoproject.ui;

import com.ess.edu.demoproject.SchoolDemo;
import com.ess.edu.demoproject.ctrl.KlassListController;
import com.ess.edu.demoproject.ctrl.StudentListController;
import com.ess.edu.demoproject.entities.School;
import com.ess.edu.demoproject.ui.view.impl.fx.KlassListView;
import com.ess.edu.demoproject.ui.view.impl.fx.StudentListView;
import com.ess.edu.demoproject.util.SchoolLoader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;

public class SchoolUIFactory extends Pane {
	static SchoolDemo application = null;
	static Label lblSchoolName = null;

	public static Pane createUI (SchoolDemo app) {
		application = app;
//		String uiLayout = Configurator.getInstance().getProperty (Configurator.LAYOUT_KEY);
//		if (Configurator.LAYOUT_FLAT.equals (uiLayout)) {
//			return createFlatLayot();
//		} else if (Configurator.LAYOUT_TABS.equals (uiLayout)) {
//			return createTabedLayot();
//		} else {
//			return new StackPane (new Label ("Unsupported layout"));
//		}
		
		
		VBox base = new VBox();
		
		Node menu = createMenu();
		HBox views = new HBox(); 
		views.getChildren().addAll (createKlassListView(), createStudentsListView(), createStudentView());
		
		base.getChildren().addAll (menu, views);
		return base;
	}

	public static void displaySchoolName (String nm) {
		lblSchoolName.setText (nm);
	}
	
	private static Node createKlassListView() {
		VBox vb = new VBox();
		//vb.setStyle (" -fx-border: 1px 0px 1px 1px; -fx-border-color: #888;");
		KlassListController klc = new KlassListController();
		application.setKlassListController(klc);
		KlassListView klv = new KlassListView();
		klc.addView (klv, false);

		Button btnNewClass = new Button ("New Class");
		btnNewClass.setOnAction (new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent arg0) {
					application.getKlassListController ().addNewElement();	
			}});

		Separator sep = new Separator(Orientation.VERTICAL);
		sep.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(sep, Priority.ALWAYS);
		HBox cmds = new HBox (10);
		cmds.setPadding (new Insets (1));
		cmds.setStyle ("-fx-background-color: #C6C6C6; -fx-border-width: 0 0 1px 0; -fx-border-color: #888;");
		Label lbl = new Label (" Classes");
		lbl.setStyle("-fx-font-weight: bold;-fx-font-size:120%;");
		cmds.getChildren().addAll (lbl, btnNewClass);
		vb.getChildren().addAll (cmds, klv.getNode());
		vb.setFillWidth(true);
		return vb;
	}

	private static Node createStudentsListView() {
		VBox vb = new VBox ();
		//vb.setStyle (" -fx-border: 1px 1px 1px 1px; -fx-border-color: #888;");
		StudentListController slc = new StudentListController();
		application.setStudentListController(slc);
		StudentListView slv = new StudentListView();
		slc.addView (slv, false);

		Button btnNewStudent = new Button ("New Student");
		btnNewStudent.setOnAction (new EventHandler<ActionEvent>(){
			@Override
			public void handle (ActionEvent arg0) {
					System.out.println("Adding new student");
					application.getStudentListController ().addNewElement();	
			}});

		Separator sep = new Separator(Orientation.VERTICAL);
		sep.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(sep, Priority.ALWAYS);
		HBox cmds = new HBox (10);
		cmds.setPadding (new Insets (1));
		cmds.setStyle ("-fx-background-color: #C6C6C6; -fx-border-width: 0 0 1px 1px; -fx-border-color: #888;");
		
		Label lbl = new Label (" Students");
		lbl.setStyle("-fx-font-weight: bold;-fx-font-size:120%;");
		cmds.getChildren().addAll (lbl, btnNewStudent);
		vb.getChildren().addAll (cmds, slv.getNode());
		vb.setFillWidth (true);
		
		return vb;
	}

	private static Node createStudentView() {
		// TODO Auto-generated method stub
		return new Pane();
	}

	private static Node createMenu() {
		HBox mnu = new HBox(4);
		mnu.setPadding(new Insets (4, 4, 4, 4));
		mnu.setStyle ("-fx-background-color: #E0E0E0; -fx-border: 1px 2px 2px 1px; -fx-border-color: #888; ");
		
		lblSchoolName = new Label (" "); 
		lblSchoolName.setStyle ("-fx-font-size:150%;"); 
		
		Button mbNew = new Button ("New School");
		mbNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				School s = SchoolLoader.loadSchool (null);
				application.setSchool (s);
			}
		});
		Button mbLoad = new Button ("Load School");
		mbLoad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				School s = SchoolLoader.loadSchool (null);
				application.setSchool (s);
			}
		});
		Button mbSave = new Button ("Save School");
		mbSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				// TODO
			}
		});
		Button mbOpt = new Button ("Settings");
		mbSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				// TODO
			}
		});
		
		Separator sep1 = new Separator (Orientation.VERTICAL);
		sep1.setPrefWidth (10.0);
		Separator sep2 = new Separator (Orientation.VERTICAL);
		sep2.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(sep2, Priority.ALWAYS);
		sep1.setVisible(false);
		sep2.setVisible(false);
		mnu.getChildren().addAll (lblSchoolName, sep2, mbNew, mbLoad, mbSave, sep1, mbOpt);
		
		return mnu;
	}

	private static Pane createTabedLayot() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Pane createFlatLayot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
