package com.ess.edu.javafx.animation;


import java.util.LinkedList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Spark extends Polygon {
	protected static double[] orig_pts = { 50, 0, 30, -10, 80, -50, 20, -20,30, -40, 10, -30, 10, -50, 0, -30, -40, -70, -20, -20, -40,-20, -20, 0, -40, 10, -20, 10, -30, 30, -10, 20, -10, 30, 0, 20, 10, 50, 20, 20, 40, 20, 30, 5};

	public static Spark ignitionSpark () {
		Spark res = new Spark (0.15);
		res.setStrokeWidth (1);
		return res;
	}
	
	public Spark () {
		this (1.0);
	}
	public Spark (double factor) {
		List<Double> pts = new LinkedList<>();
		for (double d: orig_pts) {
			pts.add (factor * d);
		}
		this.getPoints().addAll(pts);
		this.setFill (Color.YELLOW);
		this.setStroke (Color.RED);
	}
	
	public void playAnimation() {
		
	}
}
