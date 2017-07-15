package com.ess.edu.observers.fx.fightgame;

import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ArcherTest extends Application {


	@Override
	public void start (Stage stage) throws Exception {
		Pane root = new Pane();
		
		Archer a1 = new Archer ();
		ArcherView av1 = new ArcherView (a1);
		av1.setLayoutX (200);
		av1.setLayoutY (200);
		
		Circle tgt = new Circle (200, 200, 4);
		root.getChildren().addAll (av1, tgt);
		av1.getTransforms().add (new Rotate(180));
		
		Scene scn = new Scene (root, 500, 500);
		scn.focusOwnerProperty().addListener (new ChangeListener<Node> () {
			@Override
			public void changed (ObservableValue<? extends Node> observable, Node oldValue, Node newValue) {
				// TODO Auto-generated method stub
			}
		});
		stage.setScene (scn);
		stage.show();
		
		Random rnd = new Random ();
		
		Thread tKill = new Thread () {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (a1.isDead())
						break;
					int dmg = rnd.nextInt (100);
					a1.hit (dmg);
					try {
						Thread.sleep (500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		Thread tKill2 = new Thread () {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					if (a1.isDead())
						break;
					int dmg = rnd.nextInt (100);
					a1.hit (dmg);
					try {
						Thread.sleep (500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		Thread tHeal = new Thread () {
			@Override
			public void run() {
				for (;;) {
					if (a1.isDead())
						break;
					int hp = rnd.nextInt (400);
					a1.heal(hp);
					try {
						Thread.sleep (1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		tKill.start();
		tKill2.start();
		tHeal.start();
		
		
	}

	public static void main(String[] args) {
		launch (args);
	}
}
