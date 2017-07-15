package com.ess.mvc.view;

import java.util.Collection;

import com.ess.idname.IDName;

public interface IListView<T extends IDName> extends IObjectView<Collection <T>> {
	void setSelectedElement (int id);
}
