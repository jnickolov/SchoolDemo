package com.ess.edu.javafx.animation;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CoyoteWillie extends Application {
	final double groundLevel = 50;
	Dynamite dynamite = null;
	Node willieOk = null;
	Node willieBad = null;
	Group movie = null;

	@Override
	public void start (Stage stg) throws Exception {
		BorderPane root = new BorderPane();
		
		root.setTop (getCmdLine());
		this.movie = getMovie();
		root.setCenter (movie);
		
		Scene scn = new Scene (root, 750, 750);
		stg.setScene (scn);
		stg.show();
	}

	
	private Group getMovie() {
		Group res = new Group();
		
		//res.setTranslateX (350);
		//res.setTranslateY (300);
		//res.setRotate(180.0);
		
		SVGPath coords = new SVGPath ();
		coords.setContent("M 0 0, H100 0 -100 0, V 100 -100");
		coords.setStroke (Color.BLACK);
		coords.setStrokeWidth (1);

		SVGPath ground = new SVGPath ();
		ground.setContent("M 0 0, H300 C 200 100 -100 50 -300 0 z");
		ground.setStroke (Color.DARKGREEN);
		ground.setFill(Color.LIGHTGREEN);
		ground.setStrokeWidth (0);
		ground.setTranslateY(groundLevel + 250);
		
		
		willieOk = getWillie (false);
		willieOk.setTranslateY (groundLevel);

		
		Dynamite dyna = new Dynamite();
		dyna.setTranslateY(groundLevel + 100);
		
		//dyna.ignite();
		dyna.setRotationAxis(Rotate.Z_AXIS);
		dyna.setRotate(-20);

		this.dynamite = dyna;
		
		res.getChildren().addAll (coords, ground, dyna, willieOk);
		return res;
	}

	
	private Node getWillie (boolean isbad) {
		final String fileOk = "/com/ess/edu/javafx/animation/images/coyote_willie_200px.jpg";
		final String fileBad = "/com/ess/edu/javafx/animation/images/coyote_willie_bad_200px.jpg";
		
		String str = isbad ? fileBad : fileOk;
		ImageView willie = new ImageView (new Image (str));
		willie.setTranslateX(-200);
		
		return willie;
	}
	
	private Dynamite getDynamite() {
		return this.dynamite;
	}
	
	private Node getCmdLine() {
		HBox cmds = new HBox();
		
		cmds.setStyle ("-fx-background-color: #c0c0c0; -fx-padding: 4px; -fx-border-width: 1px; -fx-border-color: #000;");
		
		Button btnStart = new Button ("Start");
		btnStart.setOnAction ((e) -> {
			gogogo();
		});
		
		cmds.getChildren().addAll (btnStart);
		return cmds;
	}

	public void gogogo () {
		dynamite.finishedProperty().addListener(new ChangeListener<Boolean> () {
			{ System.out.println(12);}
			@Override
			public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue) {
				if (newValue == true)
					BOOM();
			}
		});
		dynamite.ignite();
	}
	
	public void BOOM() {
		Spark blow = new Spark();
		blow.setLayoutY (groundLevel + 100);
		this.movie.getChildren().add (blow);
		this.movie.getChildren().remove (this.dynamite);
		
		ScaleTransition boomUp = new ScaleTransition (Duration.seconds(0.1), blow);
		boomUp.setByX(3.0);
		boomUp.setByY(3.0);
		boomUp.play();
		
		willieBad = getWillie (true);
		willieBad.setTranslateY (groundLevel);
	}
	
	public static void main(String[] args) {
		Application.launch (args);
	}
}
