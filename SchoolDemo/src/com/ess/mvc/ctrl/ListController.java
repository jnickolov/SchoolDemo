package com.ess.mvc.ctrl;

import java.util.Collection;

import com.ess.idname.IDName;
import com.ess.mvc.view.IListView;
import com.ess.mvc.view.IObjectView;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class ListController<T extends IDName> extends ObjectController<Collection <T>> implements IListController<T> {
	private IntegerProperty selectedId = new SimpleIntegerProperty (0);
	
	public ListController () {
		// empty
	}
	
	// Abstract methods
	@Override
	public abstract Collection<T> createModel();

	
	// Commands
	@Override
	public void editElement (int id) {
		this.setSelectedId (id);
	}

	@Override
	public void elementSelected (int id) {
		this.selectedIdProperty().set (id);
	}

	@Override
	public void elementAdded (T px) {
		this.updateViews();
	}

	@Override
	public void elementRenamed (int id, String newName) {
		this.updateViews();
	}

	@Override
	public void elementRemoved (int id) {
		this.updateViews();
	}

	//  Get / Set (fx style)
	public final IntegerProperty selectedIdProperty() {
		return this.selectedId;
	}
	
	public final int getSelectedId() {
		return this.selectedIdProperty().get();
	}
	
	public final void setSelectedId (final int selectedID) {
		this.selectedIdProperty().set (selectedID);
		for (IObjectView<Collection<T>> v: this.getViews()) {
			IListView<T> v1 = (IListView<T>)v;
			v1.setSelectedElement(selectedID);
		}
	}
}
