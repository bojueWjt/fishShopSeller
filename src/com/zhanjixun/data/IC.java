package com.zhanjixun.data;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.zhanjixun.net.AsyncBlurImageTask;
import com.zhanjixun.net.AsyncImageTask;
import com.zhanjixun.utils.LogCat;

public class IC {

	private static IC imageCenter;

	private String projectUrl = Constants.HOST + "/" + Constants.PROJECT;

	private IC() {
	}

	public static IC getInstance() {
		return imageCenter == null ? new IC() : imageCenter;
	}

	public void setBlurForegound(Context context, String url, View view) {
		if (url == null) {
			LogCat.w("url=null");
			return;
		}
		String imgURL = projectUrl + "/" + url;
		LogCat.v(imgURL);
		new AsyncBlurImageTask(view, context, imgURL);
	}

	public void setForegound(String url, ImageView view) {
		if (url == null) {
			LogCat.w("url=null");
			return;
		}
		String imgURL = projectUrl + "/" + url;
		LogCat.v(imgURL);
		new AsyncImageTask(imgURL, view, AsyncImageTask.SET_BITMAP_FOREGROUND);
	}

	public void setBackground(String url, View view) {
		if (url == null) {
			LogCat.w("url=null");
			return;
		}
		String imgURL = projectUrl + "/" + url;
		LogCat.v(imgURL);
		new AsyncImageTask(imgURL, view, AsyncImageTask.SET_BITMAP_BACKGROUND);
	}
}
