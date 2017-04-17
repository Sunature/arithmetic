package com.sunx.arithmetic.common;

public class ArithmeticException extends Exception {

	private static final long serialVersionUID = 7100371968060950752L;

	public ArithmeticException(String msg) {
		super(msg);
	}
	
	public ArithmeticException(Throwable threw) {
		super(threw);
	}
	
}
