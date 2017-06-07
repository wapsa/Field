package com.jersey.util.common;

public final class Constants {

	/**
	 * Private Constructor to avoid other classes from instantiating this class.
	 */
	private Constants() {
		throw new UnsupportedOperationException();
	}

	// Constants-----------------------------------------------------------------------------
	/**
	 * Useful for {@link String} operations, which return an index of
	 * <tt>-1</tt> when an item is not found.
	 */
	public static final int NOT_FOUND = -1;

	/**
	 * System property - <tt>line.separator</tt>
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * System property - <tt>file.separator</tt>
	 */
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * System property - <tt>path.separator</tt>
	 */
	public static final String PATH_SEPARATOR = System.getProperty("path.separator");

	/**
	 * Scheme separator used in links http://
	 */
	public static final String SCHEME_SEPARATOR = "://";

	/**
	 * Colon
	 */
	public static final String COLON = ":";

	/**
	 * Dot
	 */
	public static final String DOT = ".";

	/**
	 * Comma
	 */
	public static final String COMMA = ",";

	/**
	 * Question Mark
	 */
	public static final String QUESTION_MARK = "?";

	/**
	 * An Empty String
	 */
	public static final String EMPTY_STRING = "";

	/**
	 * Insert a single space at this point.
	 */
	public static final String WHITESPACE = " ";

	/**
	 * Insert a period/dot ( . ) at this point.
	 */
	public static final String PERIOD = ".";

	/**
	 * Sign used to join words.
	 */
	public static final String HYPHEN = "-";

	// Escape
	// Sequences-----------------------------------------------------------------------------
	/**
	 * Insert a tab in the text at this point.
	 */
	public static final String TAB = "\t";

	/**
	 * Insert a backspace in the text at this point.
	 */
	public static final String BACKSPACE = "\t";

	/**
	 * Insert a newline in the text at this point.
	 */
	public static final String NEW_LINE = "\n";

	/**
	 * Insert a single quote at this point.
	 */
	public static final String SINGLE_QUOTE = "\'";

	/**
	 * Insert a double quote at this point.
	 */
	public static final String DOUBLE_QUOTE = "\"";

	/**
	 * Insert a backslash character in the text at this point.
	 */
	public static final String BACKSLASH = "\\";

	/**
	 * Insert a forward slash character in the text at this point.
	 */
	public static final String FORWARDSLASH = "/";

	/**
	 * Flag representing success.
	 */
	public static final int SUCCESS = 1;

	/**
	 * Flag representing failure.
	 */
	public static final int FAILURE = 0;

	/**
	 * Start of Heading.
	 */
	public static final char SOH = '\u0001';

}