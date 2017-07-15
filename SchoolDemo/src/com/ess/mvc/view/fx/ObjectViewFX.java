package com.ess.mvc.view.fx;

import com.ess.mvc.view.ObjectView;

import javafx.scene.Node;

public abstract class ObjectViewFX<T> extends ObjectView<T> implements IObjectViewFX<T> {
	protected Node node = null;
	
	@Override
	public Node getNode() {
		return this.node;
	}

}
