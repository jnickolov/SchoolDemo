package com.ess.mvc.view;

import com.ess.mvc.ctrl.IObjectController;

public abstract class ObjectView<T> implements IObjectView<T> {
	IObjectController<T> ctrl = null;

	public ObjectView() {
		this.ctrl = null;
	}
	
	@Override
	public void setController (IObjectController <T> ctrl) {
		this.ctrl = ctrl;
	}

	@Override
	public IObjectController <T> getController () {
		return this.ctrl;
	}

	@Override
	public abstract void displayModel(T model);

	@Override
	public abstract void updateModel(T model);
}