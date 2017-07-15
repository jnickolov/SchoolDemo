package com.ess.edu.javafx._3d;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Simple3D extends Application {
	private double msyOld = 0;
	private double msxOld = 0;

	
	@Override
	public void start (Stage primaryStage) throws Exception {

		Group root = new Group();
		
		PerspectiveCamera cmr = new PerspectiveCamera (true);
		cmr.setTranslateZ (-1000);
		cmr.setNearClip(0.1);
		cmr.setFarClip(5000);
		
		PhongMaterial mat1 = new PhongMaterial();
		mat1.setDiffuseColor(new Color (0.50, 0.50, .25 ,1));
		mat1.setSpecularColor(new Color (0.99, 0.99, .50,1));

		PhongMaterial mat2 = new PhongMaterial();
		mat2.setDiffuseColor(new Color (0.250, 0.50, .5, 1));
		mat2.setSpecularColor(new Color (0.5, 0.99, .99, 1));

		Cylinder c1 = new Cylinder (3, 100);
		c1.setMaterial(mat1);

		Cylinder c2 = new Cylinder (3, 100);
		c2.setMaterial(mat1);
//		c1.setBlendMode(BlendMode.SRC_ATOP);
//		c2.setBlendMode(BlendMode.SRC_ATOP);
//		c1.setCullFace(CullFace.BACK);
//		c2.setCullFace(CullFace.BACK);

		c1.setRotationAxis (Rotate.Z_AXIS);
		c2.setRotationAxis (Rotate.Z_AXIS);
		c1.setRotate(-54);
		c2.setRotate(54);
		double dx = Math.sin(Math.toRadians(54));
		double dy = Math.cos(Math.toRadians(54));
		double x1 = c1.getHeight() / 2 * dx;
		double x2 = -c2.getHeight() / 2 * dx;
		double y1 = c1.getHeight() / 2 * dy;
		double y2 = c2.getHeight() / 2 * dy;
		c1.setTranslateX(x1);
		c2.setTranslateX(x2);
		c1.setTranslateY(y1);
		c2.setTranslateY(y2);
		
		Sphere sh1 = new Sphere (10);
		Sphere sh2 = new Sphere (10);
		Sphere so = new Sphere (20);
		sh1.setMaterial(mat2);
		sh2.setMaterial(mat2);
		so.setMaterial(mat2);
		
		sh1.setTranslateX(2 * x1);
		sh1.setTranslateY(2 * y1);
		sh2.setTranslateX(2 * x2);
		sh2.setTranslateY(2 * y2);
		
		TriangleMesh msh = new TriangleMesh();
		float [] points = {
				0, 0, 0,
				100, 0, 0,
				50, 100, 0,
				50, 50, -100
		};
		float tex[] = { 0, 0 };
		int fcs[] = {
				0, 0, 1,0, 2,0,
				0,0, 1,0, 3,0,
				1,0, 2,0, 3,0,
				2,0, 0,0, 3,0
		};
		msh.getPoints().addAll(points);
		msh.getTexCoords().addAll(tex);
		msh.getFaces().addAll(fcs);
		MeshView mv = new MeshView (msh);
		mv.setDrawMode(DrawMode.LINE);
		mv.setCullFace(CullFace.NONE);

		PointLight pl = new PointLight(Color.RED);
		pl.setTranslateX(700);
		pl.setTranslateZ(-700);
		PointLight pl1 = new PointLight(Color.CYAN);
		pl.setTranslateX(-700);
		pl.setTranslateY(-700);
		pl.setTranslateZ(-1700);
		AmbientLight al = new AmbientLight (Color.GREEN);
		
		Box b= new Box (100 ,100, 100);
		b.setMaterial(mat1);
//		root.getChildren().addAll (c1, c2, so, sh1, sh2, pl, pl1, al);
		root.getChildren().addAll (mv, pl1);
		
	     
//		root.setRotationAxis(Rotate.Y_AXIS);
//		root.setRotate(-45);
		
		Scene scn = new Scene (root, 1100, 900, true);
		scn.setFill(Color.GRAY);
		scn.setCamera (cmr);
		primaryStage.setScene (scn);
		primaryStage.show();

		addMovement (scn);
	}


	public void addMovement (Scene scn) {
		Rotate xRot = new Rotate (0,0,0,0,Rotate.X_AXIS);
		Rotate yRot = new Rotate (0,0,0,0,Rotate.Y_AXIS);
		Camera c = scn.getCamera();
		Node root = scn.getRoot();
		root.getTransforms().addAll (xRot, yRot);
		
		scn.setOnKeyPressed (new EventHandler<KeyEvent>() {
			@Override
			public void handle (KeyEvent e) {
				double delta = (e.isShiftDown() ? 50 : 10);
				KeyCode code = e.getCode();
				if (code == KeyCode.W) c.setTranslateZ (c.getTranslateZ() + delta);
				if (code == KeyCode.S) c.setTranslateZ (c.getTranslateZ() - delta);
				if (code == KeyCode.A) c.setTranslateX (c.getTranslateX() + delta);
				if (code == KeyCode.D) c.setTranslateX (c.getTranslateX() - delta);
			}
		});
		
		scn.addEventHandler (MouseEvent.ANY, new EventHandler<MouseEvent>() {
			@Override
			public void handle (MouseEvent e) {
				if (e.getEventType() == MouseEvent.MOUSE_PRESSED ||
						e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
					double msx = e.getSceneX();
					double msy = e.getSceneY();
					double maxAngle = 15;
					double factor = 25;
					if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
						double xrot = (msy - msyOld) / factor;
						//System.out.println(msy + " " + msyOld + " "+ xrot);
						xrot = xrot > maxAngle ? maxAngle : xrot;
						xrot = xrot < -maxAngle ? -maxAngle : xrot;
						xRot.setAngle (xRot.getAngle() + xrot);
						double yrot = (msx - msxOld) / factor;
						yrot = yrot > maxAngle ? maxAngle : yrot;
						yrot = yrot < -maxAngle ? -maxAngle : yrot;
						yRot.setAngle (yRot.getAngle() - yrot);
						//System.out.println (xrot + ", " + yrot);
						System.out.println (xRot.getAngle() + ", " + yRot.getAngle());
					}
					msxOld = msx;
					msyOld = msy;
				}
			}
		});
	}
	
	public static void main(String[] args) {
		boolean sup = Platform.isSupported(ConditionalFeature.SCENE3D);
		System.out.println(sup);
		launch();
	}

}
