package com.ess.edu.demoproject.ui.undoablefield.text;

import com.ess.edu.demoproject.ui.undoablefield.IUndoableField;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UndoableTextField<T> extends TextField implements IUndoableField {
	T undoValue = null;

	public UndoableTextField (T initValue) {
		this();
		this.setValue (initValue);	
	}
	
	public UndoableTextField () {
		//  Add Ctrl-Z handler to revert to saved value
		this.addEventFilter (KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle (KeyEvent e) {  // handle Ctrl-Z
				if (e.getCode() == KeyCode.Z && e.isControlDown()) {
					undoValue();
					e.consume();
				}
			}
		});
	}
	
	public void setValue (T value) {
		this.setText ((value == null) ? "" : value.toString());
		this.undoValue = value;
	}
	
	@Override
	public void undoValue () {
		this.setValue (this.undoValue);
		this.positionCaret (this.getLength());  // set caret after the text
	}
}
