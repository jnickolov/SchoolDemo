package com.ess.edu.javafx.gravity;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TstGravity extends Application {
	@Override
	public void start (Stage stg) throws Exception {
		Planet p1 = new Planet ("P1");
		p1.setVx (1);
		p1.setX (50); p1.setY (50);
		
		Planet p2 = new Planet ("P2");
		p2.setVx (-1);
		p2.setX (350); p2.setY (250);
		
		Pane pan = new Pane();
		pan.getChildren().addAll (new PlanetView (p1, Color.BLUEVIOLET), new PlanetView (p2, Color.CORAL));
		Scene scn = new Scene (pan, 500, 500);
		stg.setScene (scn);
		stg.show();
		System.out.println("SHOW ON!");
		p1.bindMoving();
		p2.bindMoving();
		
		GravityForce gf = new GravityForce(p1, p2);
		p1.axProperty().bind (p1.axProperty().add(gf.divide(p1.getMass())));
		p1.ayProperty().bind (p1.ayProperty().add(gf.divide(p1.getMass())));
		p2.axProperty().bind (p2.axProperty().add(gf.divide(p2.getMass())));
		p2.ayProperty().bind (p2.ayProperty().add(gf.divide(p2.getMass())));
		gf.switchForce();
	}


	public static void main(String[] args) {
		launch();
	}

}
