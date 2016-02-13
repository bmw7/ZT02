package com.mavict.plugin.ueditor;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

	private static ResourceBundle messages = ResourceBundle.getBundle(Messages.class.getPackage().getName()+".messages", 
			Locale.getDefault());

	public static String get(String key) {
		return messages.getString(key);
	}

	public static String format(String key, Object... args) {
		return MessageFormat.format(get(key), args);
	}

}
