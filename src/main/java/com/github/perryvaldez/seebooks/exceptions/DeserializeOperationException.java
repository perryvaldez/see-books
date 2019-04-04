package com.github.perryvaldez.seebooks.exceptions;

public class DeserializeOperationException extends RuntimeException {
	private static final long serialVersionUID = 20190404L;

	public DeserializeOperationException() {
		super();
	}

	public DeserializeOperationException(String message) {
		super(message);
	}

	public DeserializeOperationException(Throwable cause) {
		super(cause);
	}

	public DeserializeOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeserializeOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
