package com.ess.edu.demoproject.ui.textfieldvalidation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public abstract class TextFieldValidator {
	private String validCssStyle = "";
	private String invalidCssStyle = "-fx-border-color: #ff0000;";
	private String validCssClass = "";
	private String invalidCssClass = "";
	private ChangeListener<Boolean> listener = null;
	
	private TextField field = null;
	
	public TextFieldValidator (TextField fld) {
		this.setField (fld);
		addCheckHandler ();
	}

	public abstract boolean isValueValid ();

	private void addCheckHandler () {
		this.listener =	new ChangeListener<Boolean>() {
		    @Override
		    public void changed (ObservableValue<? extends Boolean> prop, Boolean oldValue, Boolean newValue) {
		        if (! newValue) {  // focus lost
		        	validateValue();
		        }
		    }
		};
		
		this.field.focusedProperty().addListener (this.listener);	
	}
	
	public void validateValue () {
		displayValid (isValueValid ()); 
	}
	
	public void displayValid (boolean valid) {
		if (valid) {
			this.field.getStyleClass().remove (invalidCssClass);
			this.field.getStyleClass().add (validCssClass);
			this.field.setStyle (validCssStyle);
		} else {
			this.field.setStyle (invalidCssStyle);
			this.field.getStyleClass().add (invalidCssClass);
			this.field.getStyleClass().remove (validCssClass);
		}
	}
	
	public void setField (TextField fld) {
		this.field = fld;
	}
	
	public void detach () {
		if (this.listener != null && this.field != null) {
			this.field.focusedProperty().removeListener (this.listener);
		}
	}
	
	public String getText () {
		return this.field.getText();
	}
}
