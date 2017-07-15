package com.ess.edu.demoproject.ctrl;

import java.util.Collection;
import java.util.LinkedList;

import com.ess.edu.demoproject.entities.Klass;
import com.ess.edu.demoproject.entities.School;
import com.ess.edu.demoproject.ui.view.editor.DialogEditorFX;
import com.ess.edu.demoproject.ui.view.impl.fx.KlassView;
import com.ess.idname.IDName;
import com.ess.mvc.ctrl.ListController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class KlassListController extends ListController<IDName> {
	private School school = null;
	DialogEditorFX<Klass> editor = null;
	
	public KlassListController () {
		this.editor = new DialogEditorFX<Klass> () {
			@Override
			public void editNew () {
				if (this.getController() == null)
					return;
				
				Klass kls = new Klass ();
				this.edit (kls);
			}
		};
		KlassController kc = new KlassController();
		KlassView kv = new KlassView ();
		kc.addView (kv, false);
		editor.setController (kc);
		editor.setView (kv);
		editor.modelUpdatedProperty().addListener (new ChangeListener<Boolean>() {
			@Override
			public void changed (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					Klass k = (Klass) editor.getController().getModel();
					int selId = k.getId();
					Klass origKlass = school.getKlassById (selId);
					if (origKlass != null) {
						origKlass.setLetter(k.getLetter());
						origKlass.setGrade(k.getGrade());
						Collection <IDName> newModel = createModel();
						setModel (newModel);
						KlassListController.this.elementRenamed (k.getId(), k.getName());
						KlassListController.this.setSelectedId (selId);
					} else {
						school.getKlasses().add (k);
						Collection <IDName> newModel = createModel();
						setModel (newModel);
						updateViews();
						KlassListController.this.setSelectedId (k.getId());
					}
				}
			}
		});
	}

	@Override
	public Collection <IDName> createModel() {
		Collection <IDName> mdl = new LinkedList <IDName> ();
		
		if (this.school != null) {
			Collection <Klass> classes = this.school.getKlasses();
			for (Klass c: classes) {
				mdl.add (new IDName (c.getId(), c.getName()));
			}
		}
		return mdl;
	}

	@Override
	public void addNewElement() {
		editor.editNew();
	}

	@Override
	public void editElement (int id) {
		super.editElement (id);
		Klass kls = this.getSchool().getKlassById (id);
		if (kls != null) {
			editor.edit (kls);
		}
	}

	@Override
	public void deleteElement (int id) {
		// TODO Auto-generated method stub
		// cannot delete a class
	}
	
	// Get / Set
	
	public School getSchool() {
		return this.school;
	}


	public void setSchool (School school) {
		this.school = school;
		this.setModel (this.createModel());
	}


	public Klass getSelectedKlass () {
		return this.school.getKlassById (this.getSelectedId());
	}


	public void setSelectedKlass (Klass selectedKlass) {
		this.setModel (this.createModel());
	}

}
