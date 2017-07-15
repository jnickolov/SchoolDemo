package com.ess.edu.demoproject.ui.view.editor;

import com.ess.edu.demoproject.ctrl.IControllerCommand;
import com.ess.mvc.ctrl.IObjectController;
import com.ess.mvc.view.IObjectView;
import com.ess.mvc.view.fx.IObjectViewFX;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class InlineEditor<T> extends VBox {
	private IObjectViewFX<T> view = null;
	private IObjectController<T> ctrl;
	private Pane viewHolder = null;
	private Button btnSave = null, btnUpdate = null, btnReload = null;
	private HBox cmdLine = null;
	
	public InlineEditor () {
		super (8.0);
		this.initView();
	}

	public void setView (IObjectViewFX<T> view) {
		this.view = view;
		addViewNode();
	}
	
	public void setController (IObjectController<T> ctrl) {
		this.ctrl = ctrl;
		if (this.ctrl != null) {
			this.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
				@Override
				public void handle (KeyEvent e) {  // handle Ctrl-U for Undo
					if (e.getCode() == KeyCode.U && e.isControlDown()) {
						ctrl.reloadModel (view);
						e.consume();
					}
				}
			});
			
			this.btnUpdate.setOnAction(e -> { this.ctrl.updateModel (this.view); });
			this.btnSave.setOnAction(e -> { this.ctrl.updateModel (this.view); });
			this.btnReload.setOnAction(e -> { this.ctrl.reloadModel (this.view); });
		}
	}
	
	public IObjectViewFX<T> getView () {
		return this.view;
	}

	public void edit (T model) {
		if (this.ctrl != null) {
			this.ctrl.setModel (model);
		}
	}
	
	public void addCommand (int pos, String title, IControllerCommand<T> cmd) {
		Button btn = new Button (title);
		btn.setOnAction(e -> { cmd.execCommand (this.ctrl, this.view); });
		this.cmdLine.getChildren().add (pos, btn);
	}

	private void initView () {
		this.viewHolder = new Pane();
		this.btnSave = new Button ("Save");
		this.btnUpdate = new Button ("Update");
		this.btnReload = new Button ("Reload");
		
		cmdLine = new HBox (4.0);
		Separator sep = new Separator();
		sep.setMaxWidth(Double.MAX_VALUE);
		sep.setVisible (false);
		HBox.setHgrow (sep, Priority.ALWAYS);
		cmdLine.getChildren().addAll (this.btnReload, sep, this.btnUpdate, this.btnSave);
		cmdLine.setAlignment (Pos.CENTER_RIGHT);
		
		this.setPadding (new Insets (8, 12, 8, 12));
		this.getChildren().addAll (this.viewHolder, cmdLine);
	}
	
	private void addViewNode() {
		this.viewHolder.getChildren().clear();
		this.viewHolder.getChildren().add (this.getView().getNode());
	}
	
}
