package com.ess.edu.io;

import java.io.*;

public class TstDataStreams {

	public static void main(String[] args) {
		File file = new File ("c:\\temp\\tst.dat");
		try (FileOutputStream fos = new FileOutputStream (file)) {
			DataOutputStream dos = new DataOutputStream (fos);
			
			dos.writeDouble(3.14159265);
			dos.writeInt(123456);
			dos.writeUTF("Gogo e magare");
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try (FileInputStream fis = new FileInputStream (file)){
			DataInputStream dis = new DataInputStream (fis);
			Double dd = dis.readDouble();
			Integer ii = dis.readInt();
			String ss = dis.readUTF();
			System.out.println(dd + ", " +ii + ", " + ss);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
