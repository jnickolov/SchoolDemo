package com.ess.mvc.view.fx;

import java.util.Collection;

import com.ess.idname.IDName;
import com.ess.mvc.ctrl.IListController;
import com.ess.mvc.view.ListView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ListViewFX <T extends IDName> extends ListView <T> implements IFXView {
	private Pane pane = null;
	private javafx.scene.control.ListView<T> listView = null;

	public ListViewFX () {
		super();
		this.listView = new javafx.scene.control.ListView<>();
		this.pane = new Pane();
		
		this.listView.getSelectionModel().selectedItemProperty().addListener (
	        new ChangeListener<T>() {
	            public void changed (ObservableValue<? extends T> ov, T oldVal, T newVal) {
	            	IListController<T> ctrl = (IListController<T>) getController();
	            	if (ctrl != null) {
		            	try {
		            		ctrl.elementSelected (newVal != null ? newVal.getID() : 0);
		            	} catch (Exception e) {
		            		// no element selected
		            	}
	            	}
	        }
	    });
		
		this.listView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle (MouseEvent evt) {
				if (evt.getClickCount() > 1) {
	            	IListController<T> ctrl = (IListController<T>)getController();
					int id = listView.getSelectionModel().selectedItemProperty().get().getID();
					ctrl.editElement (id);
				}
			}
		});
	
		this.pane.getChildren().add (this.listView);
	}

	@Override
	public void displayModel (Collection<T> mdl) {
		ObservableList<T> items = FXCollections.observableArrayList ();
		for (T itm: mdl)
			items.add (itm);
		
		this.listView.setItems (items);
	}

	@Override
	public void updateModel (Collection<T> model) {
		model.clear();
		for (T itm: this.listView.getItems()) {
			model.add (itm);
		}
	}

	@Override
	public Node getNode() {
		return pane;
	}

	@Override
	public void setSelectedElement (int id) {
		ObservableList<T> items = listView.getItems();
		for (T itm: items) {
			if (itm.getID() == id) {
				listView.getSelectionModel().select (itm);
			}
		}
	}
}
