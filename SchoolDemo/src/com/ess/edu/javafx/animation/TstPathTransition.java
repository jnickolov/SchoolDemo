package com.ess.edu.javafx.animation;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TstPathTransition extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stg) throws Exception {
		Pane pan = new Pane();
		
		Circle c = new Circle (200, 200, 100);
		c.setStroke(Color.RED);
		c.setStrokeWidth(1.0);
		c.setFill(Color.TRANSPARENT);

		Circle c1 = new Circle (200, 200, 90);
		c1.setStroke(Color.BLUE);
		c1.setStrokeWidth(1.0);
		c1.setFill(Color.BLUE);

		Rectangle r = new Rectangle (10, 5);
		r.setStroke(Color.BLUE);
		r.setStrokeWidth(1.0);
		
		PathTransition pt = new PathTransition(Duration.seconds(5), c, r);
		pt.setCycleCount(10);
		pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setInterpolator(Interpolator.LINEAR);
		
		
		pan.getChildren().addAll(c, c1, r);
		
		Scene scn = new Scene (pan, 500, 500);
		stg.setScene (scn);
		stg.show();
		
		pt.play();
	}

}
