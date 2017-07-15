package com.ess.edu.observers.fx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class FXPropertiesDemo extends Application {
	DoubleProperty msx = new SimpleDoubleProperty(0);
	DoubleProperty msy = new SimpleDoubleProperty(0);
	Circle target = new Circle (10); 
	Polygon  arrow = new Polygon (-15, 5, 0, -40, 15, 5);
	Polygon  arrow2 = new Polygon (-15, 5, 0, -40, 15, 5);
	
	public static void main(String[] args) {
		launch (args);
	}

	@Override
	public void start (Stage stg) throws Exception {
		Pane root = new Pane();
		root.setStyle ("-fx-background-color: #7896cc;");
		target.setFill (new Color (1, 1, 0.5, 0.90));
		target.setStroke(Color.gray (0.4));
		arrow.setFill (new Color (1, 0.5, 0.5, 1));
		arrow.setStroke(Color.gray (0.4));
		
		root.setOnMouseMoved (new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent e) {
				double x = e.getSceneX();
				double y = e.getSceneY();
				
				msx.set (x);
				msy.set (y);
			}
		});

		
		ChangeListener cl = new ChangeListener() {
			@Override
			public void changed (ObservableValue observable, Object oldValue, Object newValue) {
				target.setCenterX (msx.get());
				target.setCenterY (msy.get());
				
				double dx = msx.get() - arrow.layoutXProperty().get(); 
				double dy = msy.get() - arrow.layoutYProperty().get();
				
				double fi = Math.atan2 (-dy,  dx);
				arrow.setRotate (90 - Math.toDegrees (fi));
			}
		};

		msx.addListener(cl);
		msy.addListener(cl);
		
		
		arrow.rotateProperty().setValue (
				Math.atan2(
					Bindings.subtract(msy, arrow.layoutYProperty()).doubleValue(),
					Bindings.subtract(msx, arrow.layoutXProperty()).doubleValue()));
		arrow.layoutXProperty().bind (root.widthProperty().divide (2.0));
		arrow.layoutYProperty().bind (root.heightProperty().subtract (10.0));
		Circle cc = new Circle (5);
		cc.layoutXProperty().bind (root.widthProperty().divide (2.0));
		cc.layoutYProperty().bind (root.heightProperty().subtract (10.0));
		root.getChildren().addAll (target, arrow,cc);
		
		Scene scn = new Scene (root, 500, 500);
		stg.setScene (scn);
		stg.show();
		
	}

}
