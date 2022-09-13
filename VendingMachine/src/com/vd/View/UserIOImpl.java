package com.vd.View;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class UserIOImpl implements UserIO{

	public void print(String input) {
		System.out.println(input);
	}
	public int readInteger(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		int integer = myScanner.nextInt();
		return integer;
	}
	
	public String readString(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		String string = myScanner.nextLine();
		return string;
	}
	
	public BigDecimal readBigDecimal(String msg) {
		
		print(msg);
		Scanner myScanner = new Scanner(System.in);
		String string = myScanner.nextLine();
		BigDecimal value = new BigDecimal(string);
		return value.setScale(2, RoundingMode.HALF_UP);
	}
}
