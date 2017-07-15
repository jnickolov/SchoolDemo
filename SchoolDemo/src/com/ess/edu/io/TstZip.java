package com.ess.edu.io;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class TstZip {

	public static void main(String[] args) throws Exception {
		String zipname = "C:\\Users\\jbn\\Downloads\\db-derby-10.11.1.1-bin.zip";
		
		try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname))) {
		ZipEntry entry;
		while ((entry = zin.getNextEntry()) != null) {
			System.out.print (entry.getName());
			if (entry.isDirectory())
				System.out.println(": directory");
			else
				System.out.println(": " + entry.getSize() + " bytes");
			zin.closeEntry();
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ZipOutputStream zout;
		try {
			RandomAccessFile raf = new RandomAccessFile(zipname, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	ZipOutputStream zos;
}
