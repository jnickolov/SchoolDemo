package com.ess.edu.javafx.gravity;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlanetView extends Circle {
	public static double DRAW_FACTOR = 10.0;
	private Property<Planet> planet;
	private Color color;
	
	public PlanetView (Planet p, Color c) {
		super();
		planet = new SimpleObjectProperty<> (p);
		this.layoutXProperty().bind (this.planet.getValue().xProperty());
		this.layoutYProperty().bind (this.planet.getValue().yProperty());
		this.color = c;
		this.radiusProperty().bind (this.planet.getValue().radiusProperty().multiply (DRAW_FACTOR));
		this.setFill (this.color);
		this.setStroke (Color.gray (0.3));
	}
}
