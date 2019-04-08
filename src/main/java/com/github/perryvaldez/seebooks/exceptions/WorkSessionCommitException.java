package com.github.perryvaldez.seebooks.exceptions;

public class WorkSessionCommitException extends RuntimeException {
	private static final long serialVersionUID = 20190408L;

	public WorkSessionCommitException() {
		super();
	}

	public WorkSessionCommitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkSessionCommitException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkSessionCommitException(String message) {
		super(message);
	}

	public WorkSessionCommitException(Throwable cause) {
		super(cause);
	}

}
