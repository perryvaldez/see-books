package com.github.perryvaldez.seebooks.exceptions;

public class InvokeReadMethodException extends RuntimeException {
	private static final long serialVersionUID = 20190404L;

	public InvokeReadMethodException() {
		super();
	}

	public InvokeReadMethodException(String message) {
		super(message);
	}

	public InvokeReadMethodException(Throwable cause) {
		super(cause);
	}

	public InvokeReadMethodException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvokeReadMethodException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
