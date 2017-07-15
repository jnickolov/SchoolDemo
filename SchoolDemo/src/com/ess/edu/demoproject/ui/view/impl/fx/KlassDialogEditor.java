package com.ess.edu.demoproject.ui.view.impl.fx;

import com.ess.edu.demoproject.ctrl.KlassController;
import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.ui.view.editor.DialogEditorFX;

public class KlassDialogEditor extends DialogEditorFX<Klass> {
	public KlassDialogEditor () {
		KlassController kc = new KlassController();
		KlassView kv = new KlassView();
		kc.addView (kv, false);

		this.setController (kc);
		this.setView (kv);
	}

	@Override
	public void editNew () {
		this.edit (new Klass());
	}
}
