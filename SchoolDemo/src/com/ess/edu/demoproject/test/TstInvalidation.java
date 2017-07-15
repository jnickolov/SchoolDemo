package com.ess.edu.demoproject.test;

import com.ess.edu.demoproject.entities.Person;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

public class TstInvalidation extends Application {
	public IntegerProperty ip = new SimpleIntegerProperty ();

	public static void main(String[] args) {
		
		System.out.println("Start");
		   //launch();
		Person p = new Person ("Gogo","Gogov");
		ObjectProperty <Person> pr = new SimpleObjectProperty <>(p);
		pr.addListener(new InvalidationListener() {
			@Override
			public void invalidated (Observable obs) {
				System.out.println ("Invalidated: ");
				
			}
		});
		
		pr.addListener(new ChangeListener<Person>() {

			@Override
			public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
				System.out.println("Changed: " + oldValue + "  -->> " + newValue);
			}
		});
		pr.set(new Person("AA", "BB "));
		p.setFirstName("Jojo");
		p.setFirstName("Koko");
		System.out.println("Finish");
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		ip.addListener (new ChangeListener(){

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				
			}});
	}

}
