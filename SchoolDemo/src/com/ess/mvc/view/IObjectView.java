package com.ess.mvc.view;

import com.ess.mvc.ctrl.IObjectController;

public interface IObjectView <T> {
	void displayModel (T model);  // load model values into editor fields
	void updateModel (T model);  // update model with editor fields
	void setController (IObjectController <T> ctrl);
	IObjectController <T> getController ();
}
