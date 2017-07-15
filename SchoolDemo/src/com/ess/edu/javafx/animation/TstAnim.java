package com.ess.edu.javafx.animation;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TstAnim extends Application {

	@Override
	public void start (Stage stg) throws Exception {
		
		Pane pan = new Pane();
		Circle ball = new Circle (100, 100, 10);
		pan.getChildren().addAll (ball);
		
		Scene scn = new Scene (pan, 500, 500);
		stg.setScene (scn);
		stg.show();
		
		PathTransition pt = new PathTransition (Duration.seconds(3.0), new SVGPath () {
				{this.setContent("m0 0 100 200"); };
			}, ball
		);
		
		EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Finished");
				Animation a = (Animation)event.getSource();
				System.out.println("Duration: " + a.getTotalDuration().toString());
				
			}
		};
		
		pt.setOnFinished (e);
		pt.play();
	}

	public static void main(String[] args) {
		launch();
	}

}
