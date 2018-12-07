package org.adams.geo.gisweb.categories;

public enum LandUtilisation {

	DK("DK", "Dauerkultur"), SO("SO", "Sondernutzung"), AAF("AAF", "Aufforstung Ackerfläche"), MB("MB",
			"Mischblock"), GL("GL", "Grünland"), AL("AL", "Ackerland"), KA("kA", "k.A.");

	private String code;
	private String text;

	private LandUtilisation(final String code, final String text) {
		this.code = code;
		this.text = text;
	}

	public String code() {
		return code;
	}

	public String text() {
		return text;
	}
}
