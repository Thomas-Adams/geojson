package org.adams.geo.gisweb.web.common;

public class UrlEncodingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UrlEncodingException() {
		super();
	}

	public UrlEncodingException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UrlEncodingException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UrlEncodingException(final String message) {
		super(message);
	}

	public UrlEncodingException(final Throwable cause) {
		super(cause);
	}

}
