package com.ess.edu;

import java.io.IOException;
import java.io.StringReader;

public class TempFiles {

	public static void main(String[] args) {
//		File f = new File ("D:\\temp\\J51Classic\\Template Extras\\logo.psd");
//		System.out.println("Length: "+ f.length());
//		try {
//			FileInputStream fis = new FileInputStream (f);
//			System.out.println("Available: "+ fis.available());
//			fis.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String s = "1234567890";
		StringReader sr = new StringReader (s);
		
		try {
			int c = sr.read();
			sr.mark(100);
			c = sr.read ();
			System.out.println((char)c);
			sr.mark(100);
			c = sr.read();
			System.out.println((char)c);
			sr.reset();
			c = sr.read();
			c = sr.read();
			c = sr.read();
			c = sr.read();
			c = sr.read();
			System.out.println((char)c);
			sr.reset();
			c = sr.read();
			System.out.println((char)c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
