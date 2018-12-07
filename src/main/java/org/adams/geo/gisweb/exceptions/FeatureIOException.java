package org.adams.geo.gisweb.exceptions;

public class FeatureIOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FeatureIOException() {
		super();
	}

	public FeatureIOException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FeatureIOException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public FeatureIOException(final String message) {
		super(message);
	}

	public FeatureIOException(final Throwable cause) {
		super(cause);
	}

}
