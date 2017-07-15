package com.ess.edu.demoproject.ctrl;

import com.ess.mvc.ctrl.IObjectController;
import com.ess.mvc.view.IObjectView;

public interface IControllerCommand <T> {
	public void execCommand (IObjectController<? extends T> ctrl, IObjectView<? extends T> view);
}
