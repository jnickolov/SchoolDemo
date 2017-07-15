package com.ess.edu.demoproject.test;

import com.ess.edu.demoproject.ctrl.*;
import com.ess.edu.demoproject.entities.*;
import com.ess.edu.demoproject.ui.view.editor.DialogEditorFX;
import com.ess.edu.demoproject.ui.view.editor.InlineEditor;
import com.ess.edu.demoproject.ui.view.impl.fx.PersonView;
import com.ess.edu.demoproject.ui.view.impl.fx.SubjectView;
import com.ess.edu.demoproject.util.InvalidSubjectNameException;
import com.ess.mvc.view.ListView;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestMVC extends Application {

	PersonView pv,pv1,pv2;
	InlineEditor ied;
	HBox root; 
	
	
	@Override
	public void start (Stage stg) throws Exception {
		BorderPane bp = new BorderPane();
		HBox top = new HBox();
		Button btnGo = new Button ("Go");
		btnGo.setOnAction (e -> { TestMVC.this.doTest(); });
		
		top.getChildren().add (btnGo);
		
		bp.setTop (top);
		root = new HBox();
		bp.setCenter(root);
		
		Scene scn = new Scene (bp, 500, 500);
		stg.setScene (scn);
		stg.show();
	}

	private void doTest() {
		doTestKlass();
	}
	
	private void doTestKlass () {
		School sc = new School ("aaa bbb");
		sc.getKlasses().add (new Klass (11, 'a'));
		sc.getKlasses().add (new Klass (11, 'b'));
		sc.getKlasses().add (new Klass (11, 'c'));
		sc.getKlasses().add (new Klass (11, 'd'));
				
		KlassListController klc = new KlassListController (sc);
		ListView<Klass> klassListView = new ListView<> (klc);
		
		root.getChildren().add (
				klassListView.getNode());
		klc.addView (klassListView, true);
		
		Klass k1 = sc.getKlassById(1);
		k1.addStudent(new Student ("Gogo", "Gogov" ));
		k1.addStudent(new Student ("Jojo", "Jojov" ));
		k1.addStudent(new Student ("Koko", "Kokov" ));
		
		StudentListController slc = new StudentListController();
		ListView<Student> stdListView = new ListView<> (slc);
		root.getChildren().add (stdListView.getNode());
		slc.addView (stdListView, false);
		
		
		klc.selectedIdProperty().addListener (new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int id = newValue != null ? newValue.intValue() : 0;
				slc.setKlass (sc.getKlassById (id));
			}
		});
			
		
	}
	
	private void doTestStudent () {
/***		
		try {
			Subject.addTitle("Math");
			Subject.addTitle("Phys");
			Subject.addTitle("Chem");
			Subject.addTitle("IT");
		} catch (InvalidSubjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Student st = new Student ("Stud", "Studenov");
		try {
			st.setGrade ("Math", 5);
			st.setGrade ("IT", 4);
		} catch (InvalidGradeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StudentController ctrl = new StudentController();
		
		ctrl.setModel(st);
		StudentView sv = new StudentView();
		
		ctrl.addView (sv, true);
//		root.setCenter (new VBox (sv1.getNode()));
		
		ied = new InlineEditor<Student>();
		ied.setView (sv);
		ied.setController (ctrl);

		//  OK 
		ied.addCommand (2, "AddGrade", new IControllerCommand() {
			@Override
			public void execCommand(IObjectController ctrl, IObjectView view) {
				System.out.println("adding grade");
			}
		});
		//
		
		root.setCenter (new VBox (sv.getNode(), ied));

		ied.edit(st);
***/
	}
	
	private void doTestSubject() {
		try {
			Subject.addTitle("Math");
			Subject.addTitle("Phys");
			Subject.addTitle("Chem");
			Subject.addTitle("IT");
		} catch (InvalidSubjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Subject s1 = null;
		try {
			s1 = new Subject ("Math", 5);
		} catch (InvalidSubjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubjectController ctrl = new SubjectController();
		ctrl.setModel (s1);
		
		SubjectView sv;
		sv = new SubjectView(); 
		ctrl.addView (sv, true);

		ied = new InlineEditor<Subject>();
///		ied.setView (sv);
		ied.setController (ctrl);
		/////root.setCenter (new VBox (ied));

		ied.edit(s1);
	}
	
	
	private void doTestPerson() {
		Person p1 = new Person("Gogo", "Gogov");
		Person p2 = new Person("Jojo", "Jojov");
		PersonController ctrl = new PersonController ();
		//ctrl.setModel (p1);

		this.pv = new PersonView();
		this.pv1 = new PersonView();
		this.pv2 = new PersonView();
		
		ctrl.addView (this.pv2, true);
		ctrl.addView (this.pv1, true);
		ctrl.addView (this.pv, true);

		ied = new InlineEditor<Person>();
		ied.setView (this.pv);
		ied.setController (ctrl);

		InlineEditor<Person> ied1 = new InlineEditor<Person>();
		ied1.setView (this.pv1);
		ied1.setController (ctrl);

		////root.setCenter (new VBox (ied, ied1));

		ied.edit (p2);

		addDialogEditor (ctrl, p2);
	}
	
	private void addDialogEditor (PersonController pc, Person p) {
		PersonView pv2 = new PersonView();
		pc.addView (pv2, true);
		DialogEditorFX pe1 = new DialogEditorFX ();
		pe1.setController (pc);
		pe1.setView (pv2);
		pe1.edit (p);
	}

	public static void main(String[] args) {
		System.out.println("Start");
		launch();
		System.out.println("Finish");
	}
}
