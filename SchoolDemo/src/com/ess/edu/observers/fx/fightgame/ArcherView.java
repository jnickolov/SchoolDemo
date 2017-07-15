package com.ess.edu.observers.fx.fightgame;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ArcherView extends Rectangle {
	public static double MAX_HEIGHT = 50.0;
	public static double DEFAULT_WIDTH = 10.0;
	private Archer theArcher = null;
//	private Rectangle bar = new Rectangle (5, MAX_HEIGHT);
	private double yFactor; 
	
	public ArcherView (Archer archer) {
		super (DEFAULT_WIDTH, MAX_HEIGHT);
		
	
		this.theArcher = archer;
		this.yFactor = this.MAX_HEIGHT / this.theArcher.getMaxBlood();
		this.setFill (Color.LIGHTGREEN);
		this.setStroke(Color.gray (0.4));
		
		theArcher.lightlyWoundedProperty().addListener (new ChangeListener<Boolean>() {
			@Override
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					ArcherView.this.setFill (Color.LIGHTGREEN);
				}
			}
		});

		theArcher.moderateWoundedProperty().addListener (new ChangeListener<Boolean>() {
			@Override
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					ArcherView.this.setFill (Color.YELLOW);
				}
			}
		});

		theArcher.criticalWoundedProperty().addListener (new ChangeListener<Boolean>() {
			@Override
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					ArcherView.this.setFill (Color.RED);
				}
			}
		});
		
		
		this.heightProperty().bind (this.theArcher.bloodProperty().multiply (this.yFactor).add (1.0));		
		
	}
	
}

