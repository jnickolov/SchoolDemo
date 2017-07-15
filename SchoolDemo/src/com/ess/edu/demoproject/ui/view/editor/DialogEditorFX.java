package com.ess.edu.demoproject.ui.view.editor;


import com.ess.mvc.ctrl.IObjectController;
import com.ess.mvc.view.IEditor;
import com.ess.mvc.view.fx.IObjectViewFX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class DialogEditorFX<T> extends Stage implements IEditor<T> {
	private IObjectViewFX<T> view = null;
	private IObjectController<T> ctrl;
	private Pane viewHolder = null;
	private VBox pane;
	private Button btnSave = null, btnUpdate = null, btnCancel = null, btnReload = null;
	
	private BooleanProperty modelUpdated = null;
	
	public DialogEditorFX () {
		modelUpdated = new SimpleBooleanProperty (false);
		this.initModality (Modality.APPLICATION_MODAL);
		this.initScene();
	}

	public void setView (IObjectViewFX<T> view) {
		this.view = view;
		addViewNode();
	}
	
	public void setController (IObjectController<T> ctrl) {
		this.ctrl = ctrl;
	}
	
	public IObjectViewFX<T> getView () {
		return this.view;
	}
	
	public IObjectController<T> getController () {
		return this.ctrl;
	}
	
	@Override
	public abstract void editNew ();

	@Override
	public void edit (T model) {
		if (this.ctrl != null) {
			this.ctrl.setModel (model);
			this.show();
			this.modelUpdatedProperty().setValue (false);
		}
	}

	private void initScene () {
		this.viewHolder = new Pane();
		this.btnSave = new Button ("Save");
		this.btnUpdate = new Button ("Update");
		this.btnReload = new Button ("Reload");
		this.btnCancel = new Button ("Cancel");
		
		pane = new VBox (8.0);
		HBox cmdLine = new HBox (4.0);
		Separator sep = new Separator();
		sep.setMaxWidth(Double.MAX_VALUE);
		sep.setVisible (false);
		HBox.setHgrow (sep, Priority.ALWAYS);
		cmdLine.getChildren().addAll (this.btnReload, sep, this.btnUpdate, this.btnSave, this.btnCancel);
		cmdLine.setAlignment (Pos.CENTER_RIGHT);
		
		pane.setPadding (new Insets (8, 12, 8, 12));
		pane.getChildren().addAll (this.viewHolder, cmdLine);
		this.setScene (new Scene (pane));

		this.viewHolder.addEventFilter (KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
			@Override
			public void handle (KeyEvent e) {  // handle Ctrl-U for Undo
				if (e.getCode() == KeyCode.U && e.isControlDown()) {
					ctrl.reloadModel (view);
					e.consume();
				}
			}
		});

		this.btnReload.setOnAction(e -> { 
			this.ctrl.reloadModel (this.view); 
		});
		
		this.btnUpdate.setOnAction(e -> { 
			this.ctrl.updateModel (this.view); 
			this.modelUpdatedProperty().setValue (true);
		});
		
		this.btnSave.setOnAction(e -> { 
			System.out.println("Saving: ");
			this.ctrl.updateModel (this.view); 
			this.close(); 
			this.modelUpdatedProperty().setValue (true);
		});
		
		this.btnCancel.setOnAction (e -> { 
			this.ctrl.editCanceled (this.view); 
			this.close(); 
			this.modelUpdatedProperty().setValue (false);
		});
	}
	
	private void addViewNode() {
		this.viewHolder.getChildren().clear();
		this.viewHolder.getChildren().add (this.getView().getNode());
	}

	public final BooleanProperty modelUpdatedProperty() {
		return this.modelUpdated;
	}

	public final boolean isModelUpdated() {
		return this.modelUpdatedProperty().get();
	}

	public final void setModelUpdated(final boolean modelUpdated) {
		this.modelUpdatedProperty().set(modelUpdated);
	}
}
