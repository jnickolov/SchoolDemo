package com.ess.edu.exceptions;

import java.io.*;

public class TstExceptions {

	public void openFile ()  {
		File f = new File ("qqq");
		//FileInputStream fis = null ;
		try (FileInputStream fis = new FileInputStream (f)) {
			fis.read();
		} catch (IOException e) {
			System.out.println("Fanah te");
			e.printStackTrace();
		} 
	}
	
	
	public void a () {
		String s = null;
		try {
			s.toString();
		} catch (Exception e) {
			System.out.println("Fanah te");
			e.printStackTrace();
		}
	}
	
	
	public void b () {
		this.a();
	}
	
	public int c () {
		int i = 0;
		//throw new NullPointerException ("sdfkjskdjf");
		try {
			i = 1;
			return i;
		} finally {
			System.out.println("In finally block");
			i = 2;
		}
	}
	
	public static void main(String[] args) {
		TstExceptions tst = new TstExceptions ();
		System.out.println(tst.c());
	}

}
