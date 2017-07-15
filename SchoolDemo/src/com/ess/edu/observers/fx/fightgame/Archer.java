package com.ess.edu.observers.fx.fightgame;

import javafx.beans.property.*;
import javafx.scene.transform.Rotate;

public class Archer {
	static private int MAX_BLOOD = 1000;
	
	private IntegerProperty blood = new SimpleIntegerProperty (MAX_BLOOD);
	private BooleanProperty lightlyWounded = new SimpleBooleanProperty (false);
	private BooleanProperty moderateWounded = new SimpleBooleanProperty (false);
	private BooleanProperty criticalWounded = new SimpleBooleanProperty (false);
	private BooleanProperty dead = new SimpleBooleanProperty (false);

	{
		lightlyWounded.bind (this.blood.greaterThan (0.60 * this.MAX_BLOOD));
		moderateWounded.bind (this.blood.lessThan (0.60 * this.MAX_BLOOD).and (this.blood.greaterThan (0.25 * this.MAX_BLOOD)));
		criticalWounded.bind (this.blood.lessThan (0.25 * this.MAX_BLOOD));
		dead.bind (this.blood.lessThanOrEqualTo (0.0));
	}
	
	public void hit (int damage) {
		if (this.blood.getValue() >= damage) {
			this.setBlood (this.getBlood() - damage);
			System.out.println ("Wounded: " + damage + " Heal remaining: " + this.getBlood());
		} else {
			this.blood.setValue (0);
			System.out.println("DEAD!");
		}
	}
	
	public void heal (int healPoints) {
		if (this.blood.getValue() + healPoints <= this.MAX_BLOOD) {
			this.setBlood (this.getBlood() + healPoints);
			System.out.println("Healed: " + healPoints);
		} else {
			this.blood.setValue (this.MAX_BLOOD);
			System.out.println("Healed: MAX");
		}
	}

	public final IntegerProperty bloodProperty() {
		return this.blood;
	}
	

	public final int getBlood() {
		return this.bloodProperty().get();
	}
	

	public final void setBlood(final int blood) {
		this.bloodProperty().set(blood);
	}
	

	public final BooleanProperty criticalWoundedProperty() {
		return this.criticalWounded;
	}

	public final BooleanProperty moderateWoundedProperty() {
		return this.moderateWounded;
	}

	public final BooleanProperty lightlyWoundedProperty() {
		return this.lightlyWounded;
	}
	

	public final boolean isCriticalWounded() {
		return this.criticalWoundedProperty().get();
	}

	public final boolean isModerateWounded() {
		return this.moderateWoundedProperty().get();
	}


	public final BooleanProperty deadProperty() {
		return this.dead;
	}
	

	public final boolean isDead() {
		return this.deadProperty().get();
	}
	
	public int getMaxBlood () {
		return this.MAX_BLOOD;
	}
}
