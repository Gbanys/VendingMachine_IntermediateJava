package com.vd.View;

import java.math.BigDecimal;

public interface UserIO {

	void print(String input);
	int readInteger(String msg);
	String readString(String msg);
	BigDecimal readBigDecimal(String msg);
}
