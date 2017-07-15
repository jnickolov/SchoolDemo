package com.ess.edu.io;

import java.io.*;

import com.ess.edu.demoproject.entities.Person;
import com.ess.edu.demoproject.entities.Student;

public class TstObjectStreams {


	public static void main(String[] args) {
		File file = new File ("c:\\temp\\tst_obj.dat");
		try (FileOutputStream fos = new FileOutputStream (file)) {
			ObjectOutputStream oos = new ObjectOutputStream (fos);
			Person p = new Person ("Gogo", "Gogov");
			oos.writeObject(p);
			
			Student t = new Student ("Ivan4o", "Ivanov");
			t.setGrade ("Math", 3);
			t.setGrade ("IT", 4);
			
			oos.writeObject(t);
			
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (FileInputStream fis = new FileInputStream (file)){
			ObjectInputStream ois = new ObjectInputStream (fis);
			System.out.println(ois.available());
			System.out.println("\n----------------------------\n");
			Person p = (Person)ois.readObject();
			Student t = (Student)ois.readObject();
			
			System.out.println(p);
			System.out.println("\n----------------------------\n");
			System.out.println(t);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
