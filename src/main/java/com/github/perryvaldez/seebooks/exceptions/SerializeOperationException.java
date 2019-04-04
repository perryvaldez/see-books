package com.github.perryvaldez.seebooks.exceptions;

public class SerializeOperationException extends RuntimeException {
	private static final long serialVersionUID = 20190404L;

	public SerializeOperationException() {
		super();
	}

	public SerializeOperationException(String message) {
		super(message);
	}

	public SerializeOperationException(Throwable cause) {
		super(cause);
	}

	public SerializeOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SerializeOperationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
