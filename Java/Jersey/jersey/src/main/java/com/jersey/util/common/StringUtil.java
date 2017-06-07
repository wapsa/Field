package com.jersey.util.common;

import java.nio.charset.Charset;

/**
 * Useful string utilities
 */
public final class StringUtil {

	/**
	 * Private Constructor to avoid other classes from instantiating this class.
	 */
	private StringUtil() {
		throw new UnsupportedOperationException();
	}

	private static final char[] HEX_NUMERALS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	// Methods-----------------------------------------------------------------------------

	/**
	 * Determine if an input string is null or empty.
	 *
	 * @param string
	 *            Input string that might contain null, empty string or
	 *            non-empty string
	 * @return True if the input string is null or empty; false, the otherwise
	 */
	public static boolean isNullOrEmpty(String string) {
		return (string == null || string.isEmpty());
	}

	/**
	 * Converts the input string to hex.
	 * 
	 */
	public static String toHex(String arg) {
		final byte[] stringBytes = arg.getBytes(Charset.forName("UTF-8"));
		final int numOfBytes = stringBytes.length;
		char[] result = new char[2 * numOfBytes];
		int j = 0;
		for (int i = 0; i < numOfBytes; i++) {
			result[j++] = HEX_NUMERALS[(stringBytes[i] & 0xF0) >>> 4];
			result[j++] = HEX_NUMERALS[(stringBytes[i] & 0x0F)];
		}
		return String.valueOf(result);
	}

}