package com.ess.edu.javafx.gravity;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

class GravityForce extends DoubleBinding {
	public static double G = 1.0;
	
	private Planet p1, p2;
	private boolean switched = false;
	
	public GravityForce (Planet p1, Planet p2) {
		this.p1 = p1;
		this.p2 = p2;
//		p1.xProperty().addListener (new ChangeListener<Number>() {
//			@Override
//			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				computeValue();
//			}
//		});
//		p1.yProperty().addListener (new ChangeListener<Number>() {
//			@Override
//			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				computeValue();
//			}
//		});
//		p2.xProperty().addListener (new ChangeListener<Number>() {
//			@Override
//			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				computeValue();
//			}
//		});
//		p2.yProperty().addListener (new ChangeListener<Number>() {
//			@Override
//			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				computeValue();
//			}
//		});
	}
	
	public void switchForce () {
		this.switched = ! this.switched;
	}
	
	@Override
	protected double computeValue() {
		System.out.println("computed");
		if (this.switched) {
			double r = Math.hypot(p1.getX() - p2.getX(), p1.getY() - p2.getY());
			return -G * p1.getMass() * p2.getMass() / (r * r);
		} else {
			return 0.0;
		}
	}
}
