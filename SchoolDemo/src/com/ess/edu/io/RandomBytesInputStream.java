package com.ess.edu.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class RandomBytesInputStream extends InputStream {
	@Override
	public int available() throws IOException {
		return Integer.MAX_VALUE;
	}

	private Random rnd;
	
	public RandomBytesInputStream () {
		rnd = new Random ();
	}
	
	@Override
	public int read() throws IOException {
		return rnd.nextInt(256);
	}

	public static void main(String[] args) {
		RandomBytesInputStream ris = new RandomBytesInputStream();
		try {
			System.out.println(ris.available());
			System.out.println(ris.markSupported());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(ris.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
