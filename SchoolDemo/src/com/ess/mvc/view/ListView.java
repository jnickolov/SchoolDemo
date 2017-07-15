package com.ess.mvc.view;

import java.util.Collection;

import com.ess.idname.IDName;

public abstract class ListView<T extends IDName> extends ObjectView <Collection <T>> implements IListView<T>  {
	
	public ListView () {
		// empty
	}
	
	@Override
	public abstract void displayModel (Collection <T> model);

	@Override
	public abstract void updateModel (Collection <T> model);

}
