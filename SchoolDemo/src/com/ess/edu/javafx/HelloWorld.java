package com.ess.edu.javafx;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

 
public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch (args);
    }
    
    @Override
    public void start (Stage primaryStage) {
    	Pane root = new Pane();
		HBox hb = new HBox();
    	Label lbl = new Label ("Enter text here");
    	Label lbl1 = new Label ("Second label");
    	lbl.setStyle("-fx-border-color: #000; -fx-border-style: solid; -fx-border-width: 1px;");
    	
    	TextField tf = new TextField ("");
    	TextField tf1 = new TextField ("");
    	hb.getChildren().addAll (lbl, tf, lbl1, tf1);
    	root.getChildren().add(hb);
    	hb.setStyle("-fx-border-color: #000; -fx-border-style: solid; -fx-border-width: 1px;");
    	hb.setPadding(new Insets (8, 8, 8, 8));
    	root.setStyle("-fx-border-color: #000; -fx-border-style: solid; -fx-border-width: 1px;");
    	root.setPadding(new Insets (8, 8, 8, 8));
    	
    	//********************************************************//
    	tf.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent> () {
			@Override
			public void handle (KeyEvent e) {
				char ch = e.getCharacter().charAt(0);
				if (ch < '1' || ch > '9')
					e.consume();
			}
    	});
    	
    	
    	EventHandler<MouseEvent> mh = new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				System.out.println("Mouse Entered: " + e.getSource().getClass().getSimpleName() + ", " + e.getTarget().getClass().getSimpleName());
				//e.consume();
			}
    	};
    	EventHandler<MouseEvent> mht = new EventHandler<MouseEvent> () {
			@Override
			public void handle (MouseEvent e) {
				System.out.println("Mouse Entered Target: " +  e.getSource().getClass().getSimpleName() + ", " + e.getTarget().getClass().getSimpleName());
			}
    	};
    	
    	//lbl.addEventHandler(MouseEvent.MOUSE_ENTERED, mh);
    	//lbl.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, mht);
    	//lbl1.addEventHandler(MouseEvent.MOUSE_ENTERED, mh);
    	tf.addEventHandler(MouseEvent.MOUSE_ENTERED, mh);
    	//root.addEventHandler(MouseEvent.MOUSE_ENTERED, mh);
    	//hb.addEventHandler(MouseEvent.MOUSE_ENTERED, mh);
    	hb.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, mht);
    	root.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, mht);
    	
        primaryStage.setTitle ("Hello World!");
        primaryStage.setScene (new Scene (root, 300, 250));
        primaryStage.show();
    }
}