package it.tristana.spacewars.arena.helper;

public class Utility {

	private Utility() {}
	
	public static int parseIntOrGetDefault(String value, int defaultValue) {
		int result;
		try {
			result = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}
	
	public static double parseDoubleOrGetDefault(String value, double defaultValue) {
		double result;
		try {
			result = Double.parseDouble(value);
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}
}
