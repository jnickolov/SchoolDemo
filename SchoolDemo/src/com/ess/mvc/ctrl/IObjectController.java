package com.ess.mvc.ctrl;

import com.ess.mvc.view.IObjectView;

public interface IObjectController <T> {
	public T getModel ();
	public void setModel (T model);
	
	void addView (IObjectView <T> view, boolean mustUpdate);
	void removeView (IObjectView <T> view);
	
	void updateViews ();
	void updateViews (IObjectView<T> srcView);

	void reloadModel (IObjectView <T> destView);  // destView request to reload the model
	void updateModel (IObjectView <T> srcView);  // srcView updates the model 
	void editCanceled (IObjectView <T> srcView);  // srcView declares that the edit was canceled
}
