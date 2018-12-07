package org.adams.geo.gisweb.exceptions;

public class InvalidSchemaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidSchemaException() {
		super();
	}

	public InvalidSchemaException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidSchemaException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InvalidSchemaException(final String message) {
		super(message);
	}

	public InvalidSchemaException(final Throwable cause) {
		super(cause);
	}

}
