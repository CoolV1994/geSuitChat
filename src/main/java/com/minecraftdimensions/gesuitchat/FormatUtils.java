package com.minecraftdimensions.gesuitchat;

import java.util.regex.Pattern;

public class FormatUtils {
	static final transient Pattern ALL_PATTERN = Pattern.compile("(?<!&)&([0-9a-fk-orA-FK-OR])");
	static final transient Pattern COLOR_PATTERN = Pattern.compile("(?<!&)&([0-9a-fA-F])");
	static final transient Pattern MAGIC_PATTERN = Pattern.compile("(?<!&)&([Kk])");
	static final transient Pattern FORMAT_PATTERN = Pattern.compile("(?<!&)&([l-orL-OR])");

	public static String stripAll(final String input) {
		if (input == null) {
			return null;
		}
		return strip(input, ALL_PATTERN);
	}

	public static String stripColor(final String input) {
		if (input == null) {
			return null;
		}
		return strip(input, COLOR_PATTERN);
	}

	public static String stripFormat(final String input) {
		if (input == null) {
			return null;
		}
		return strip(input, FORMAT_PATTERN);
	}

	public static String stripMagic(final String input) {
		if (input == null) {
			return null;
		}
		return strip(input, MAGIC_PATTERN);
	}

	static String strip(final String input, final Pattern pattern) {
		return pattern.matcher(input).replaceAll("");
	}
}