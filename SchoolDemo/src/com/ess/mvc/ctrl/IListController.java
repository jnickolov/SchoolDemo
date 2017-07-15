package com.ess.mvc.ctrl;

import java.util.Collection;

import com.ess.idname.IDName;

public interface IListController<T extends IDName> extends IObjectController <Collection<T>> {
	int getSelectedId();
	Collection<T> createModel();
	
	void elementSelected (int id);
	void elementAdded (T px);
	void elementRenamed (int id, String newName);
	void elementRemoved (int id);
	
	void addNewElement ();
	void editElement (int id);
	void deleteElement (int id);
}
