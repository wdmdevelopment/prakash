package com.example.demo.exception;

public class NotValidFormatException extends Exception{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public NotValidFormatException(String errorMessage) {
			super(errorMessage);
		}

}
