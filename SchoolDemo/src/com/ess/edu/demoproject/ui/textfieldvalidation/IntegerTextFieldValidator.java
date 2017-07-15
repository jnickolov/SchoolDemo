package com.ess.edu.demoproject.ui.textfieldvalidation;

import javafx.scene.control.TextField;

public class IntegerTextFieldValidator extends TextFieldValidator {

	public IntegerTextFieldValidator (TextField fld) {
		super(fld);
	}

	@Override
	public boolean isValueValid() {
		String txt = this.getText();
		if (txt == null)
			return false;
		
		try {
			return customCheck (Integer.parseInt (txt.trim()));
		} catch (Exception e) {  // invalid format
			return false;
		}
	}

	public boolean customCheck (Integer val) {
		return true;
	}
}
