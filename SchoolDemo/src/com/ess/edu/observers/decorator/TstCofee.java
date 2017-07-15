package com.ess.edu.observers.decorator;

import java.util.Scanner;

public class TstCofee {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner ("11 Я");
		try {
			int n = sc.nextInt();
			String c = sc.next().trim();
			char ch= c.charAt(0);
			System.out.println("Klas: " + n + " " + c);
			if (ch < 'А' || ch > 'Е')
				System.out.println("Invalid!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("invalid");
		}
		
	}

}
