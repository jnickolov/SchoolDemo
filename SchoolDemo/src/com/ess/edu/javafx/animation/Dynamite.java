package com.ess.edu.javafx.animation;

import javafx.animation.PathTransition;
import javafx.animation.Transition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Dynamite extends Group {
	
	private BooleanProperty finished = new SimpleBooleanProperty (false);
		private double wid = 50.0;
		private double hig = 10.0;
		private double cordeLen = 30.0;

		private Line corde = null;
		private Spark ignition = null;
		
	public Dynamite () {
		Rectangle block = new Rectangle (wid, hig);
		block.setFill (Color.BURLYWOOD);
		block.setStroke (Color.DARKGREY);
		block.setStrokeWidth (1);
		
		corde = new Line (wid, hig/2, wid+cordeLen, hig/2);
		corde.setStrokeWidth (3);

//		SVGPath path = new SVGPath();
//		path.setContent("M0 0 H100 -100 0 V 100 -100");
//		path.setStroke(Color.RED);
//		path.setStrokeWidth(1);
		
		this.getChildren().addAll (corde, block);
	}
	
	public void ignite () {
		ignition = Spark.ignitionSpark();
		ignition.setTranslateX (corde.getEndX ());
		ignition.setTranslateY (corde.getEndY ());
		this.getChildren().add (ignition);
		corde.endXProperty().bind (ignition.translateXProperty());
		corde.endYProperty().bind (ignition.translateYProperty());
		
		this.playAnimation();
	}
	
	public final BooleanProperty finishedProperty() {
		return this.finished;
	}
	

	public final boolean isFinished() {
		return this.finishedProperty().get();
	}
	

	public final void setFinished (final boolean finished) {
		this.finishedProperty().set(finished);
	}
	
	
	void playAnimation () {
		Path crd = new Path (new MoveTo (corde.getEndX(), corde.getEndY()), new LineTo (corde.getStartX()+5, corde.getStartY()));
		Transition burn = new PathTransition (Duration.seconds(3.0), crd, ignition);
		burn.setOnFinished (new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Dynamite.this.finishedProperty().setValue (true);
			}});
		
		burn.play();
	}
	
	
}
