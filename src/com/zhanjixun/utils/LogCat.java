package com.zhanjixun.utils;

import android.util.Log;

public class LogCat {

	public static void info(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.i(tag, msg);
	}

	public static void debug(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.d(tag, msg);
	}

	public static void warn(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.w(tag, msg);
	}

	public static void error(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.e(tag, msg);
	}

	public static void verbose(String msg) {
		String tag = new Throwable().getStackTrace()[1].getClassName() + "."
				+ new Throwable().getStackTrace()[1].getMethodName() + "()";
		Log.v(tag, msg);
	}
}
