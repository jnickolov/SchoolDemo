package com.ess.mvc.ctrl;

import java.util.Collection;
import java.util.LinkedList;

import com.ess.mvc.view.IObjectView;

public class ObjectController<T> implements IObjectController<T> {
	private Collection <IObjectView <T>> views = null;
	private T model;
	
	public ObjectController() {
		this.views = new LinkedList<>();
	}

	@Override
	public void addView (IObjectView <T> view, boolean mustUpdate) {
		this.views.add (view);
		view.setController(this);
		if (mustUpdate)
			view.displayModel (this.model);
	}

	@Override
	public void removeView (IObjectView <T> view) {
		this.views.remove (view);
	}

	@Override
	public T getModel () {
		return this.model;
	}

	@Override
	public void setModel (T obj) {
		this.model = obj;
		this.updateViews();
	}

	@Override
	public void reloadModel (IObjectView<T> srcView) {
		this.updateViews ();
	}

	@Override
	public void updateModel (IObjectView<T> srcView) {
		srcView.updateModel (model);  //no check if model == null
		this.updateViews (srcView);
		
	}

	@Override
	public void editCanceled (IObjectView<T> srcView) {
		srcView.displayModel (this.model);
	}

	@Override
	public void updateViews () {
		for (IObjectView<T> view: this.views) {
			view.displayModel (model);
		}
	}

	@Override
	public void updateViews (IObjectView<T> srcView) {
		for (IObjectView<T> view: this.views) {
			if (view != srcView)
				view.displayModel (model);
		}
	}
	
	protected Collection<IObjectView <T>> getViews() {
		return this.views;
	}
}
