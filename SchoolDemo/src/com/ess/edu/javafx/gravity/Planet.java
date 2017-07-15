package com.ess.edu.javafx.gravity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Planet {
	private DoubleProperty mass = new SimpleDoubleProperty (1);
	private DoubleProperty radius = new SimpleDoubleProperty (1);
	private DoubleProperty x = new SimpleDoubleProperty (0);
	private DoubleProperty y = new SimpleDoubleProperty (0);
	private DoubleProperty vx = new SimpleDoubleProperty (0);
	private DoubleProperty vy = new SimpleDoubleProperty (0);
	private DoubleProperty ax = new SimpleDoubleProperty (0);
	private DoubleProperty ay = new SimpleDoubleProperty (0);
	
	private String name;
	
	public Planet (String name) {
		this.name = name;
		
	}
	
	public void bindMoving () {
		// Bind velocity with acceleration
		ax.addListener(new ChangeListener<Number>() {
			@Override
			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				vx.setValue (vx.getValue() + (Double)newValue);
			}
		});
		
		ay.addListener(new ChangeListener<Number>() {
			@Override
			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				vy.setValue (vy.getValue() + (Double)newValue);
			}
		});

		vx.addListener(new ChangeListener<Number>() {
			@Override
			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				x.setValue (x.getValue() + (Double)newValue);
			}
		});
		vy.addListener(new ChangeListener<Number>() {
			@Override
			public void changed (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				y.setValue (y.getValue() + (Double)newValue);
			}
		});
		
	}

	//  Getters/Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public final DoubleProperty massProperty() {
		return this.mass;
	}
	

	public final double getMass() {
		return this.massProperty().get();
	}
	

	public final void setMass(final double mass) {
		this.massProperty().set(mass);
	}

	public final DoubleProperty radiusProperty() {
		return this.radius;
	}
	

	public final double getRadius() {
		return this.radiusProperty().get();
	}
	

	public final void setRadius(final double radius) {
		this.radiusProperty().set(radius);
	}
	

	public final DoubleProperty xProperty() {
		return this.x;
	}
	

	public final double getX() {
		return this.xProperty().get();
	}
	

	public final void setX(final double x) {
		this.xProperty().set(x);
	}
	

	public final DoubleProperty yProperty() {
		return this.y;
	}
	

	public final double getY() {
		return this.yProperty().get();
	}
	

	public final void setY(final double y) {
		this.yProperty().set(y);
	}

	public final DoubleProperty vxProperty() {
		return this.vx;
	}
	

	public final double getVx() {
		return this.vxProperty().get();
	}
	

	public final void setVx(final double vx) {
		this.vxProperty().set(vx);
	}
	

	public final DoubleProperty vyProperty() {
		return this.vy;
	}
	

	public final double getVy() {
		return this.vyProperty().get();
	}
	

	public final void setVy(final double vy) {
		this.vyProperty().set(vy);
	}
	

	public final DoubleProperty axProperty() {
		return this.ax;
	}
	

	public final double getAx() {
		return this.axProperty().get();
	}
	

	public final void setAx(final double ax) {
		this.axProperty().set(ax);
	}
	

	public final DoubleProperty ayProperty() {
		return this.ay;
	}
	

	public final double getAy() {
		return this.ayProperty().get();
	}
	

	public final void setAy(final double ay) {
		this.ayProperty().set(ay);
	}
	
}
