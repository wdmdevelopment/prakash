package com.wdm.exception;

public class InvalidDataException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private String errorCode;
		private String status;
		
		public InvalidDataException(String errorCode, String status) {
			super(errorCode);
			this.errorCode = errorCode;
			this.status = status;
		}

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		
}
