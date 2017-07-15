package com.ess.edu.demoproject.test;

import com.ess.edu.demoproject.ui.textfieldvalidation.IntegerTextFieldValidator;
import com.ess.edu.demoproject.ui.undoablefield.text.IntegerUndoableField;
import com.ess.edu.demoproject.ui.undoablefield.text.UndoableTextField;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TestFields extends Application {
	@Override
	public void start (Stage stg) throws Exception {
		
		IntegerUndoableField itxt = new IntegerUndoableField (123); 
		UndoableTextField<Double> dtxt = new UndoableTextField<> (); dtxt.setValue (3.14);
		UndoableTextField<String> stxt = new UndoableTextField<> (); stxt.setValue("Initial text"); 
		new IntegerTextFieldValidator(itxt);
		
		Button btnSave = new Button ("Save");
		Button btnCancel = new Button ("Cancel");
		
		GridPane root = new GridPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setHgap(8);
		root.setVgap(6);
		root.add (new Label ("Integer"), 0, 0);
		root.add (itxt, 1, 0);
		root.add (new Label ("Double"), 0, 1);
		root.add (dtxt, 1, 1);
		root.add (new Label ("String"), 0, 2);
		root.add (stxt, 1, 2);
		//root.add (btnSave, 0, 3);
		//root.add (btnCancel, 1, 3);
		Scene scn = new Scene(root);
		stg.setScene(scn);
		stg.show();
	}

	public static void main(String[] args) {
		System.out.println("Start");
		launch();
		System.out.println("Finish");
	}
}
